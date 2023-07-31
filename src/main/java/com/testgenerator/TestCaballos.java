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

public class TestCaballos {
        Browser browser;
        Page page;

        // @UseBrowserConfig(TraceBrowserConfig.class)
        @Test(retryAnalyzer = Retry.class)
        public static void main(String[] args) {
        }

        @BeforeTest
        private void setUp() throws InterruptedException {
                try (Playwright playwright = Playwright.create()) {
                        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                        page = browser.newPage();
                        page.navigate("https://20.67.52.193/sisnet/");
                }
        }

        @Test
        @UseBrowserConfig(TraceBrowserConfig.class)
        @AfterTest
        private void tearDown() {
                browser.close();
        }

        @Test(retryAnalyzer = Retry.class)
        public void ejecutar() {
                try (Playwright playwright = Playwright.create()) {
                        String dni = DniGenerator.generateValidDNI();
                        String nombre = NameGenerator.generarNombreAleatorio();
                        String Apellido1 = NameGenerator.generarNombreAleatorio();
                        String Apellido2 = NameGenerator.generarNombreAleatorio();
                        String nombreMascota = NameGenerator.generarNombreAleatorio();
                        String email = EmailGenerator.generateRandomEmail();
                        Instant start = Instant.now();
                        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                                        .setHeadless(true));

                        BrowserContext context = browser
                                        .newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
                        context.tracing().start(new Tracing.StartOptions()
                                        // .setScreenshots(true)
                                        // .setSnapshots(true)
                                        .setSources(true));
                        context.setDefaultNavigationTimeout(5000);
                        context.setDefaultTimeout(10000);
                        Page page = context.newPage();
                        page.navigate("https://20.67.52.193/sisnet/login.jsp");
                        page.getByLabel("Usuario:").click();
                        page.getByLabel("Usuario:").fill("autoqa");
                        page.getByLabel("Clave:").click();
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
                        page.waitForTimeout(300);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                                        .selectOption("1: T110");
                        page.getByLabel("Valor").click();
                        page.getByLabel("Valor").fill("2");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad")).click();
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Función"))
                                        .selectOption("1: U001");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Alojamiento"))
                                        .selectOption("1: A001");
                        page.getByLabel("Teléfono de contacto").click();
                        page.getByLabel("Teléfono de contacto").fill("678985453");
                        page.getByLabel("Teléfono de contacto").press("Tab");
                        page.getByLabel("Email de contacto").fill(email);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByRole(AriaRole.ROW,
                                        new Page.GetByRoleOptions().setName("[DEPRECADO] RC + Vida 39,18 €"))
                                        .locator("label").click();
                        page.waitForTimeout(300);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByLabel("Nombre").click();
                        page.getByLabel("Nombre").fill("Mascota " + nombreMascota);
                        page.getByLabel("Nombre").press("Tab");
                        page.getByLabel("Fecha de nacimiento").fill("02/03/2010");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                                        .selectOption("1: S003");
                        page.getByLabel("Nº Microchip").click();
                        page.getByLabel("Nº Microchip").fill("874521");
                        page.getByLabel("Raza").click();
                        page.getByLabel("Raza").fill("Pura Sangre");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de documento"))
                                        .selectOption("4: NIF");
                        page.getByLabel("NIF/NIE").click();
                        page.getByLabel("NIF/NIE").fill(dni);
                        page.getByLabel("Nombre").click();
                        page.getByLabel("Nombre").fill(nombre);
                        page.getByLabel("Primer apellido").click();
                        page.getByLabel("Primer apellido").fill(Apellido1);
                        page.getByLabel("Segundo apellido").click();
                        page.getByLabel("Segundo apellido").fill(Apellido2);
                        page.getByLabel("Fecha de nacimiento").click();
                        page.getByLabel("Fecha de nacimiento").fill("02/01/1990");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                                        .selectOption("1: CA");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo"))
                                        .selectOption("1: H");
                        page.getByLabel("Email").click();
                        page.getByLabel("Email").fill(email);
                        page.getByLabel("Teléfono Móvil").click();
                        page.getByLabel("Teléfono Móvil").fill("687451231");
                        page.getByLabel("Teléfono Fijo").click();
                        page.getByLabel("Teléfono Fijo").fill("911222333");
                        page.getByText("No se ha asignado ninguna dirección").click();
                        page.getByTitle("Añadir/modificar la dirección").click();
                        page.getByLabel("Código postal").click();
                        page.getByLabel("Código postal").fill("28015");
                        page.getByLabel("Población").click();
                        page.getByLabel("Población").fill("Poblasao");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de vía"))
                                        .selectOption("BRNCO");
                        page.getByLabel("Nombre de vía").click();
                        page.getByLabel("Nombre de vía").fill("Norte");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Aceptar")).click();
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El propietario del animal es el mismo que el tomador?"))
                                        .click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.locator("label").filter(
                                        new Locator.FilterOptions().setHasText(
                                                        "¿El asegurado es el mismo que el propietario/tomador?"))
                                        .click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Medios de pago"))
                                        .selectOption("1: EFEC");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Emitir")).click();
                        assertThat(page.locator("#stateDependantBackgroundCIERRE00 > div"))
                                        .containsText("correctamente");
                        Instant end = Instant.now();
                        long elapsedTimeInMilliseconds = end.toEpochMilli() - start.toEpochMilli();
                        context.tracing().stop(new Tracing.StopOptions()
                                        .setPath(Paths.get("trace.zip")));
                        context.close();
                        browser.close();

                        System.out.println("Tiempo de ejecución: " + elapsedTimeInMilliseconds + " milisegundos");
                        playwright.close();
                }
        }
}
