package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.*;
import com.testgenerator.DniGenerator;

import io.github.uchagani.*;
import io.github.uchagani.jp.UseBrowserConfig;
import com.testgenerator.TraceBrowserConfig;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.time.Instant;

public class TestCaballosAleatorio {
        Browser browser;
        Page page;

        String[] sexoTomador = { "1: H", "2: M" };
        String[] estadoCivil = { "1: CA", "2: OT", "3: PA", "4: SE", "5: SO", "6: VI" };
        String[] arrayEdadesCaballo = { "1: T110", "2: T120", "3: T130", "4: T140", "5: T150",
                        "6: T160" };
        String[] arrayFuncionCaballo = { "1: U001", "2: U002", "3: U003" };
        String[] arrayAlojamientoCaballo = { "1: A001", "2: A002" };
        String[] arrayPrecioCaballo = {"[DEPRECADO] RC Básico 39,00 €",  "[DEPRECADO] RC + Vida 39,18 €",
                        "[DEPRECADO] RC + Retirada Cadáver 39,05 €"};

        public static void main(String[] args) {
        }

        @Test(retryAnalyzer = Retry.class)
        public Boolean ejecutar(String ip, int timeoutAccion, int timeoutNavegacion, boolean modo, int nTestActual) {
                try (Playwright playwright = Playwright.create()) {

                        String microchip = MicrochipGenerator.generateMicrochip();
                        Random random = new Random();
                        int numeroPrecio = random.nextInt(3);
                        int numeroCaballo = random.nextInt((6 - 0));
                        String tramoEdadCaballo = arrayEdadesCaballo[numeroCaballo];
                        int edadCaballo = 0;
                        if (numeroCaballo == 0) {
                                edadCaballo = random.nextInt((5 - 1)) + 1;
                        }
                        if (numeroCaballo == 1) {
                                edadCaballo = random.nextInt((10 - 5)) + 5;
                        }
                        if (numeroCaballo == 2) {
                                edadCaballo = random.nextInt((15 - 10)) + 10;
                        }
                        if (numeroCaballo == 3) {
                                edadCaballo = random.nextInt((17 - 15)) + 15;
                        }
                        if (numeroCaballo == 4) {
                                edadCaballo = random.nextInt((19 - 17)) + 17;
                        }
                        if (numeroCaballo == 5) {
                                edadCaballo = random.nextInt((30 - 20)) + 20;
                        }
                        String edadCaballoCadena = ((Integer) edadCaballo).toString();
                        String dni = DniGenerator.generateValidDNI();
                        String nombre = NameGenerator.generarNombreAleatorio();
                        String Apellido1 = NameGenerator.generarNombreAleatorio();
                        String Apellido2 = NameGenerator.generarNombreAleatorio();
                        String nombreMascota = NameGenerator.generarNombreAleatorio();
                        String email = EmailGenerator.generateRandomEmail();
                        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                                        .setHeadless(modo));
                        String telefonoFijo = PhoneGenerator.generarNumeroTelefonoFijo();
                        String telefonoMovil = PhoneGenerator.generarNumeroTelefonoMovil();
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
                        page.getByLabel("Usuario:").fill("autoqa");
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
                                        .selectOption("3: TA03");
                        page.waitForSelector("#inputDATOSRIE04");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                                        .selectOption(tramoEdadCaballo);
                        page.getByLabel("Valor").click();
                        page.getByLabel("Valor").fill(edadCaballoCadena);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad")).click();
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Función"))
                                        .selectOption(arrayFuncionCaballo[random.nextInt((3 - 0))]);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Alojamiento"))
                                        .selectOption(arrayAlojamientoCaballo[random.nextInt((2 - 0))]);
                        page.getByLabel("Teléfono de contacto").click();
                        page.getByLabel("Teléfono de contacto").fill("678985453");
                        page.getByLabel("Teléfono de contacto").press("Tab");
                        page.getByLabel("Email de contacto").fill(email);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#bloque2");
                        if(numeroPrecio != 0){
                                page.getByRole(AriaRole.ROW,new Page.GetByRoleOptions().setName(arrayPrecioCaballo[numeroPrecio])).locator("label").click();
                                page.waitForTimeout(2000);
                        }
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByLabel("Nombre").fill("Mascota " + nombreMascota);
                        page.getByLabel("Fecha de nacimiento").fill("02/03/2010");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                                        .selectOption("1: S003");
                        page.getByLabel("Nº Microchip").fill(microchip);
                        page.getByLabel("Raza").fill("Pura Sangre");
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
                        page.getByLabel("NIF/NIE").click();
                        page.getByLabel("NIF/NIE").fill(dni);
                        page.getByLabel("Nombre").fill(nombre);
                        page.getByLabel("Primer apellido").fill(Apellido1);
                        page.locator("span").filter(new Locator.FilterOptions().setHasText("Segundo apellido"))
                                        .locator("div")
                                        .nth(2).click();
                        page.getByLabel("Segundo apellido").fill(Apellido2);
                        page.getByLabel("Fecha de nacimiento").fill("02/02/1990");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                                        .selectOption(estadoCivil);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo"))
                                        .selectOption(sexoTomador);
                        page.getByLabel("Email").fill(email);
                        page.getByLabel("Teléfono Móvil").fill(telefonoMovil);
                        page.getByLabel("Teléfono Fijo").fill(telefonoFijo);
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
                        // page.waitForSelector(
                        //         "body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div");
                        // assertThat(page.locator(
                        //         "body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div"))
                        //         .containsText("Asegurados");   
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForSelector("#dateDATSEGUR01");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        // page.waitForSelector("body > app-root > app-layout-sisc > div > main > div > app-page-form-basic > div > div > form > app-ad-elem > app-panel-group:nth-child(1) > div > div > div.scs-fill-flex-75 > app-ad-elem > app-table-action-request-submdl > div.scs-contentBox.scs-wideBox.scs-table-title-cont.ng-star-inserted > div");
                        page.waitForTimeout(200);
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
