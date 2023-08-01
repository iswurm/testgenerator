package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

public class TestGatosChromium {
    public static void main(String[] args) {
        
    }

    public String ejecutar(){
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
            Instant start = Instant.now();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(true));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
            // context.tracing().start(new Tracing.StartOptions()
            //         // .setScreenshots(true)
            //         // .setSnapshots(true)
            //         .setSources(true));
            context.setDefaultNavigationTimeout(5000);
            context.setDefaultTimeout(10000);
            Page page = context.newPage();
            page.navigate("https://20.67.52.193/sisnet/");
            page.getByLabel("Usuario:").fill("Autoqa");
            page.getByLabel("Clave:").fill("netijam");
            page.getByLabel("Clave:").press("Enter");
            page.frameLocator("iframe[name=\"cuerpo\"]")
                    .getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Suscribir")).click();
            page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("* Mediador").click();
            page.frameLocator("iframe[name=\"cuerpo\"]")
                    .getByRole(AriaRole.MENUITEM,
                            new FrameLocator.GetByRoleOptions()
                                    .setName("TERR01 - TERRANEA 2000 CORREDURIA DE SEGUROS SL"))
                    .getByText("TERR01 - TERRANEA 2000 CORREDURIA DE SEGUROS SL").click();
            page.frameLocator("iframe[name=\"cuerpo\"]")
                    .getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Grabar")).click();
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de animal"))
                    .selectOption("2: TA02");
            page.getByLabel("Raza").click();
            page.getByLabel("Raza").fill(raza);
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                    .selectOption("1: T010");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.getByLabel("Nombre").click();
            page.getByLabel("Nombre").fill(nombreMascota);
            page.getByLabel("Fecha de nacimiento").click();
            page.getByLabel("Fecha de nacimiento").fill("24/02/2000");
            page.getByLabel("Fecha de nacimiento").press("Tab");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                    .selectOption("1: S001");
            page.getByLabel("Nº Microchip").click();
            page.getByLabel("Nº Microchip").fill("234234");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de documento"))
                    .selectOption("4: NIF");
            page.getByLabel("NIF/NIE").click();
            page.getByLabel("NIF/NIE").fill(dni);
            page.getByLabel("Nombre").click();
            page.getByLabel("Nombre").fill(nombre);
            page.getByLabel("Primer apellido").click();
            page.getByLabel("Primer apellido").fill(apel1);
            page.getByLabel("Segundo apellido").click();
            page.getByLabel("Segundo apellido").fill(apel2);
            page.getByLabel("Fecha de nacimiento").fill("24/03/2000");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                    .selectOption("1: CA");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo")).selectOption("1: H");
            page.getByLabel("Email").click();
            page.getByLabel("Email").fill(mail);
            page.getByLabel("Teléfono Móvil").click();
            page.getByLabel("Teléfono Móvil").fill(telefono1);
            page.getByLabel("Teléfono Fijo").fill(telefono2);
            page.getByTitle("Añadir/modificar la dirección").click();
            page.getByLabel("Código postal").click();
            page.getByLabel("Código postal").fill("28971");
            page.getByLabel("Población").click();
            page.getByLabel("Población").fill("Madrid");
            page.getByLabel("Población").press("Tab");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de vía")).selectOption("ARRBL");
            page.getByLabel("Nombre de vía").click();
            page.getByLabel("Nombre de vía").fill("Madrid");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Aceptar")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.locator("label").filter(
                    new Locator.FilterOptions().setHasText("¿El propietario del animal es el mismo que el tomador?"))
                    .click();
            page.waitForTimeout(200);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.locator("label").filter(
                    new Locator.FilterOptions().setHasText("¿El asegurado es el mismo que el propietario/tomador?"))
                    .click();
            page.waitForTimeout(200);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.locator("div")
                    .filter(new Locator.FilterOptions().setHasText(Pattern.compile("^SeleccionarAnual renovable$")))
                    .first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.waitForTimeout(200);
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Medios de pago"))
                    .selectOption("1: EFEC");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Emitir")).click();
            page.locator("#stateDependantBackgroundCIERRE00 > div").click();
            assertThat(page.locator("#stateDependantBackgroundCIERRE00 > div"))
                    .containsText("correctamente");
            Instant end = Instant.now();
            long elapsedTimeInMilliseconds = end.toEpochMilli() - start.toEpochMilli();
            context.close();
            browser.close();
            playwright.close();
            return "Tiempo de ejecución CHROMIUM: " + elapsedTimeInMilliseconds + " milisegundos";
            
        }catch(PlaywrightException e){
                return e.getMessage().toString();
        }
    }
}