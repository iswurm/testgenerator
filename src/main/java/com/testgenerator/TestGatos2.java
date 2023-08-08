package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.Random;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

public class TestGatos2 {

        String[] arrayEdadesGato = { "1: T010", "2: T020", "3: T030", "4: T040" };
        String[] arrayPrecioGato = {"Responsabilidad Civil 300.000 Franq. 200 24,00 €", 
        "Responsabilidad Civil 300.000 Sin Franq. 32,00 €", 
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
        "Responsabilidad Civil 600.000 Sin Franq. + Asist. Vet. 37,00 €"};
        String[] sexoTomador = { "1: H", "2: M" };
        String[] estadoCivil = { "1: CA", "2: OT", "3: PA", "4: SE", "5: SO", "6: VI" };
        String[] sexoAnimal = { "1: S001", "2: S002" };
        String microchip = MicrochipGenerator.generateMicrochip();
        Random random = new Random();

        public static void main(String[] args) {

        }

        public Boolean ejecutar(String ip, int timeoutAccion, int timeoutNavegacion, boolean modo, int nTestActual) {
                try (Playwright playwright = Playwright.create()) {
                        String dni = DniGenerator.generateValidDNI();
                        String nombre = NameGenerator.generarNombreAleatorio();
                        String nombreMascota = NameGenerator.generarNombreAleatorio();
                        String apel1 = NameGenerator.generarNombreAleatorio();
                        String apel2 = NameGenerator.generarNombreAleatorio();
                        String telefono1 = PhoneGenerator.generarNumeroTelefonoMovil();
                        String telefono2 = PhoneGenerator.generarNumeroTelefonoFijo();
                        String raza = RazaGatoGenerator.obtenerRazaGatoAleatoria();
                        String mail = EmailGenerator.generateRandomEmail();
                        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                                        .setHeadless(modo));
                        BrowserContext context = browser
                                        .newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
                        // context.tracing().start(new Tracing.StartOptions()
                        // // .setScreenshots(true)
                        // // .setSnapshots(true)
                        // .setSources(true));
                        context.setDefaultNavigationTimeout(timeoutNavegacion);
                        context.setDefaultTimeout(timeoutAccion);
                        Page page = context.newPage();
                        page.navigate(ip);
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
                                        .selectOption("2: TA02");
                        page.waitForSelector("#inputDATOSRIE01");
                        page.getByLabel("Raza").click();
                        page.getByLabel("Raza").fill(raza);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                                        .selectOption(arrayEdadesGato[random.nextInt((arrayEdadesGato.length - 1) + 1)]);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#bloque2");
                        page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(arrayPrecioGato[random.nextInt((arrayPrecioGato.length - 1) + 1)])).locator("label").click();
                        page.waitForTimeout(2000);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByLabel("Nombre").fill(nombreMascota);
                        page.getByLabel("Fecha de nacimiento").fill("25/01/2008");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                                        .selectOption(sexoAnimal[random.nextInt((1 - 0) + 1)]);
                        page.getByLabel("Nº Microchip").fill(microchip);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#inputDATTOMAD01");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de documento"))
                                        .selectOption("4: NIF");
                        page.getByLabel("NIF/NIE").fill(dni);
                        page.getByLabel("Nombre").fill(nombre);
                        page.getByLabel("Primer apellido").fill(apel1);
                        page.locator("span").filter(new Locator.FilterOptions().setHasText("Segundo apellido"))
                                        .locator("div")
                                        .nth(2).click();
                        page.getByLabel("Segundo apellido").fill(apel2);
                        page.getByLabel("Fecha de nacimiento").fill("24/05/1998");
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
                        // page.waitForSelector(
                        //         "body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div");
                        page.waitForTimeout(200);
                        // assertThat(page.locator(
                        //         "body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div"))
                        //                 .containsText("Asegurados");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#dateDATSEGUR01");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        //page.waitForSelector("body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group:nth-child(1) > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div");
                        // assertThat(page.locator("body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group:nth-child(1) > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div"))
                        //                 .containsText("Cl\u00E1usulas");                        
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Medios de pago"))
                                        .selectOption("1: EFEC");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Emitir")).click();
                        // page.waitForTimeout(200);
                        page.locator("#stateDependantBackgroundCIERRE00 > div").click();
                        assertThat(page.locator("#stateDependantBackgroundCIERRE00 > div"))
                                        .containsText("correctamente");
                        context.close();
                        browser.close();
                        playwright.close();
                        return true;

                }catch(PlaywrightException e){
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
