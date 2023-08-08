package com.testgenerator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.time.Instant;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;

// import static org.assertj.core.api.Assumptions.assumeThat;
// import static org.junit.jupiter.api.Assertions.fail;

public class TestPerros extends Thread{
        String[] arrayRazasPerro = { "CRUCE CON RAZAS P. PELIGROSAS", "CRUCE CON RAZAS NO PELIGROSAS", "AFFENPINSCHER",
                        "AIREDALE TERRIER", "AKITA AMERICANO", "AKITA INU JAPONÉS", "ALANO ESPAÑOL", "ALASKAN MALAMUTE",
                        "AMERICAN BULLY", "AMERICAN HAIRLESS TERRIER", "AMERICAN HAIRLESS TERRIER",
                        "AMERICAN STAFFORDSHIRE TERRIER", "AMERICAN WATER SPANIEL", "ANGLO FRANCAIS DE PETITE VENERIE",
                        "AUSTRALIAN SHEPHERD", "BEARDED COLLIE", "BRIQUET GRIFFON VENDEEN",
                        "CA DE BOU", "CAN DE PALLEIRO" };
        String[] arrayEdadesPerro = { "1: T010", "2: T020", "3: T030", "4: T040" };
        String[] sexoTomador = { "1: H", "2: M" };
        String[] estadoCivil = { "1: CA", "2: OT", "3: PA", "4: SE", "5: SO", "6: VI" };
        String[] sexoAnimal = { "1: S001", "2: S002" };
        String[] arrayPreciosPerro = { "Responsabilidad Civil 300.000 Franq. 200 24,00 €",
                        "Responsabilidad Civil 300.000 Sin Franq. 32,00 €",
                        "Responsabilidad Civil 350.000 Franq. 200 24,00 €",
                        "Responsabilidad Civil 350.000 Franq. 100 29,00 €",
                        "Responsabilidad Civil 350.000 Sin Franq. 36,00 €",
                        "Responsabilidad Civil 600.000 Franq. 200 25,00 €",
                        "Responsabilidad Civil 600.000 Sin Franq. 37,00 €",
                        "Responsabilidad Civil 300.000 Franq. 200 + Asist. Vet. 33,00 €",
                        "Responsabilidad Civil 300.000 Sin Franq. + Asist. Vet. 41,00 €",
                        "Responsabilidad Civil 350.000 Franq. 200 + Asist. Vet. 33,00 €",
                        "Responsabilidad Civil 350.000 Franq. 100 + Asist. Vet. 38,00 €",
                        "Responsabilidad Civil 350.000 Sin Franq. + Asist. Vet. 45,00 €",
                        "Responsabilidad Civil 600.000 Franq. 200 + Asist. Vet. 34,00 €",
                        "Responsabilidad Civil 600.000 Sin Franq. + Asist. Vet. 37,00 €" };
        String microchip = MicrochipGenerator.generateMicrochip();
        Random random = new Random();

        Browser browser;
        Page page;

        public static void main(String[] args) {
        }

        @Test(retryAnalyzer = Retry.class)
        public Boolean ejecutar(String ip, int timeoutAccion, int timeoutNavegacion, boolean modo, int nTestActual) {
                try (Playwright playwright = Playwright.create()) {
                        String dni = DniGenerator.generateValidDNI();
                        String nombre = NameGenerator.generarNombreAleatorio();
                        String apel1 = NameGenerator.generarNombreAleatorio();
                        String apel2 = NameGenerator.generarNombreAleatorio();
                        String telefono1 = PhoneGenerator.generarNumeroTelefonoMovil();
                        String telefono2 = PhoneGenerator.generarNumeroTelefonoFijo();
                        String razaPerro = arrayRazasPerro[random.nextInt(arrayRazasPerro.length)];
                        int numeroAleatorio = random.nextInt(59) + 2;
                        String pesoPerro = String.valueOf(numeroAleatorio);
                        String mail = EmailGenerator.generateRandomEmail();
                        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                                        .setHeadless(modo));

                        BrowserContext context = browser
                                        .newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
                        context.tracing().start(new Tracing.StartOptions()
                                        // .setScreenshots(true)
                                        // .setSnapshots(true)
                                        .setSources(true));
                        context.setDefaultNavigationTimeout(timeoutNavegacion);
                        context.setDefaultTimeout(timeoutAccion);
                        Page page = context.newPage();
                        page.navigate(ip);
                        page.getByLabel("Usuario:").click();
                        page.getByLabel("Usuario:").fill("Autoqa");
                        page.getByLabel("Clave:").fill("netijam");
                        page.getByLabel("Clave:").press("Enter");
                        page.frameLocator("iframe[name=\"cuerpo\"]")
                                        .getByRole(AriaRole.LINK,
                                                        new FrameLocator.GetByRoleOptions().setName("Suscribir"))
                                        .click();
                        page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("* Mediador").click();
                        page.frameLocator("iframe[name=\"cuerpo\"]")
                                        .getByRole(AriaRole.MENUITEM,
                                                        new FrameLocator.GetByRoleOptions()
                                                                        .setName("TERR01 - TERRANEA 2000 CORREDURIA DE SEGUROS SL"))
                                        .getByText("TERR01 - TERRANEA 2000 CORREDURIA DE SEGUROS SL").click();
                        page.frameLocator("iframe[name=\"cuerpo\"]")
                                        .getByRole(AriaRole.BUTTON,
                                                        new FrameLocator.GetByRoleOptions().setName("Grabar"))
                                        .click();
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de animal"))
                                        .selectOption("1: TA01");
                        page.waitForSelector("#inputDATOSRIE03");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                                        .selectOption(arrayEdadesPerro[random
                                                        .nextInt((arrayEdadesPerro.length - 1) + 1)]);
                        page.getByLabel("C.P.").click();
                        page.getByLabel("C.P.").fill("28971");
                        page.getByLabel("Raza").click();
                        page.getByText(razaPerro).click();
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#bloque2");
                        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions()
                                        .setName(arrayPreciosPerro[random.nextInt((arrayPreciosPerro.length - 1) + 1)]))
                                        .locator("label").click();
                        page.waitForTimeout(2000);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#inputDATOSADI00");
                        // page.getByLabel("Nombre").click();
                        page.getByLabel("Nombre").fill("Mascota");
                        // page.getByLabel("Nombre").press("Tab");
                        page.getByLabel("Fecha de nacimiento").fill("14/03/2005");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                                        .selectOption("1: S001");
                        page.locator("span").filter(new Locator.FilterOptions().setHasText("Nº Microchip"))
                                        .locator("div").nth(3)
                                        .click();
                        page.getByLabel("Nº Microchip").fill(microchip);
                        page.getByLabel("Peso").click();
                        page.getByLabel("Peso").fill(pesoPerro);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#inputDATTOMAD01");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de documento"))
                                        .selectOption("4: NIF");
                        page.getByLabel("NIF/NIE").click();
                        page.getByLabel("NIF/NIE").fill(dni);
                        page.locator("app-form-select")
                                        .filter(new Locator.FilterOptions().setHasText(
                                                        "Tipo de documentoSeleccionarCIF Persona JurídicaMenoresNIF ExtranjerosNIF Person"))
                                        .click(new Locator.ClickOptions()
                                                        .setClickCount(3));
                        page.getByLabel("NIF/NIE").fill(dni);
                        page.getByLabel("Nombre").fill(nombre);
                        page.getByLabel("Primer apellido").fill(apel1);
                        page.locator("span").filter(new Locator.FilterOptions().setHasText("Segundo apellido"))
                                        .locator("div")
                                        .nth(2).click();
                        page.getByLabel("Segundo apellido").fill(apel2);
                        page.getByLabel("Fecha de nacimiento").fill("24/03/2000");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                                        .selectOption(estadoCivil);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo"))
                                        .selectOption(sexoTomador);
                        page.getByLabel("Email").fill(mail);
                        page.getByLabel("Teléfono Móvil").fill(telefono1);
                        page.getByLabel("Teléfono Fijo").fill(telefono2);
                        page.getByTitle("Añadir/modificar la dirección").click();
                        page.getByLabel("Código postal").fill("28971");
                        page.getByLabel("Población").fill("Madrid");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de vía"))
                                        .selectOption("AVDA");
                        page.getByLabel("Nombre de vía").fill("Princesa");
                        page.getByLabel("Número").fill("85");
                        page.getByLabel("Portal").fill("c");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Aceptar")).click();
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El propietario del animal es el mismo que el tomador?"))
                                        .click();
                        page.waitForSelector("#inputDATTPROP05");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El asegurado es el mismo que el propietario/tomador?"))
                                        .click();
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El asegurado es el mismo que el propietario/tomador?"))
                                        .click();
                        page.waitForTimeout(200);
                        //page.waitForSelector("body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div");
                        // assertThat(page.locator("body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div"))
                        //         .containsText("Asegurados");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#dateDATSEGUR01");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        //page.waitForSelector("body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group:nth-child(1) > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div");
                        // assertThat(page.locator(
                        //                 "body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group:nth-child(1) > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div"))
                        //                 .containsText("Cl\u00E1usulas");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Medios de pago"))
                                        .selectOption("1: EFEC");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Emitir")).click();
                        page.locator("#stateDependantBackgroundCIERRE00 > div").click();
                        assertThat(page.locator("#stateDependantBackgroundCIERRE00 > div"))
                                        .containsText("correctamente");
                        context.close();
                        browser.close();
                        playwright.close();
                        return true;
                } catch (PlaywrightException e) {
                        String cabecera =
                        "###############################################################################################################\n" +
                        "#                                                                                                             #\n" +
                        "       Traza de error en Test número "+nTestActual+"   \n" +
                        "#                                                                                                             #\n" +
                        "###############################################################################################################\n";
                        Main.escribirEnArchivo("logErrores.txt", cabecera);
                        Main.escribirEnArchivo("logErrores.txt", e.getMessage());
                        return false;
                }
        }
}
