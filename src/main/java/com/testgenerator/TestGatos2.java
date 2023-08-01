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
        String[] sexoTomador = { "1: H", "2: M" };
        String[] estadoCivil = { "1: CA", "2: OT", "3: PA", "4: SE", "5: SO", "6: VI" };
        String[] sexoAnimal = { "1: S001", "2: S002" };
        String microchip = MicrochipGenerator.generateMicrochip();
        Random random = new Random();

        public static void main(String[] args) {

        }

        public String ejecutar() {
                try (Playwright playwright = Playwright.create()) {
                        String fechaNacimiento = DateGenerator.generateDate();
                        String codigoPostal = CPGenerator.generarCodigoPostal();
                        String fechaNacimientoMascota = DateGenerator.generatePetDate();
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
                        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                                        .setHeadless(false));
                        BrowserContext context = browser
                                        .newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
                        // context.tracing().start(new Tracing.StartOptions()
                        // // .setScreenshots(true)
                        // // .setSnapshots(true)
                        // .setSources(true));
                        context.setDefaultNavigationTimeout(5000);
                        context.setDefaultTimeout(10000);
                        Page page = context.newPage();
                        page.navigate("https://20.67.52.193/sisnet/");
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
                        page.getByLabel("Raza").click();
                        page.getByLabel("Raza").fill(raza);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                                        .selectOption(arrayEdadesGato[random.nextInt((3 - 0) + 1)]);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.getByLabel("Nombre").click();
                        page.getByLabel("Nombre").fill(nombreMascota);
                        page.getByLabel("Fecha de nacimiento").click();
                        page.getByLabel("Fecha de nacimiento").fill(fechaNacimientoMascota);
                        page.getByLabel("Fecha de nacimiento").press("Tab");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                                        .selectOption(sexoAnimal[random.nextInt((1 - 0) + 1)]);
                        page.getByLabel("Nº Microchip").click();
                        page.getByLabel("Nº Microchip").fill(microchip);
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
                        page.getByLabel("Fecha de nacimiento").fill(fechaNacimiento);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                                        .selectOption(estadoCivil[random.nextInt((5 - 0) + 1)]);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo"))
                                        .selectOption(sexoTomador[random.nextInt((1 - 0) + 1)]);
                        page.getByLabel("Email").click();
                        page.getByLabel("Email").fill(mail);
                        page.getByLabel("Teléfono Móvil").click();
                        page.getByLabel("Teléfono Móvil").fill(telefono1);
                        page.getByLabel("Teléfono Fijo").fill(telefono2);
                        page.getByTitle("Añadir/modificar la dirección").click();
                        page.getByLabel("Código postal").click();
                        page.getByLabel("Código postal").fill(codigoPostal);
                        page.getByLabel("Población").click();
                        page.getByLabel("Población").fill("Madrid");
                        page.getByLabel("Población").press("Tab");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de vía"))
                                        .selectOption("ARRBL");
                        page.getByLabel("Nombre de vía").click();
                        page.getByLabel("Nombre de vía").fill("Madrid");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Aceptar")).click();
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El propietario del animal es el mismo que el tomador?"))
                                        .click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El asegurado es el mismo que el propietario/tomador?"))
                                        .click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.locator("div")
                                        .filter(new Locator.FilterOptions()
                                                        .setHasText(Pattern.compile("^SeleccionarAnual renovable$")))
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
                        return "Tiempo de ejecución WEBKIT: " + elapsedTimeInMilliseconds + " milisegundos";

                }
        }
}