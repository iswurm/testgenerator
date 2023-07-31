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

// import static org.assertj.core.api.Assumptions.assumeThat;
// import static org.junit.jupiter.api.Assertions.fail;

public class Example {

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
                        String nombreMascota = NameGenerator.generarNombreAleatorio();
                        String apel1 = NameGenerator.generarNombreAleatorio();
                        String apel2 = NameGenerator.generarNombreAleatorio();
                        Instant start = Instant.now();
                        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
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
                        page.navigate("https://20.67.52.193/sisnet/");
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
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                                        .selectOption("1: T010");
                        page.getByLabel("C.P.").click();
                        page.getByLabel("C.P.").fill("28971");
                        page.getByLabel("Raza").click();
                        page.getByText("CRUCE CON RAZAS P. PELIGROSAS").click();
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
                        // page.getByLabel("Nombre").click();
                        page.getByLabel("Nombre").fill(nombreMascota);
                        // page.getByLabel("Nombre").press("Tab");
                        page.getByLabel("Fecha de nacimiento").fill("22/02/1990");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                                        .selectOption("1: S001");
                        page.locator("span").filter(new Locator.FilterOptions().setHasText("Nº Microchip"))
                                        .locator("div").nth(3)
                                        .click();
                        page.getByLabel("Nº Microchip").fill("45621");
                        page.getByLabel("Peso").click();
                        page.getByLabel("Peso").fill("67");
                        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
                        page.waitForTimeout(200);
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
                        page.getByLabel("NIF/NIE").press("Tab");
                        page.getByLabel("Nombre").fill(nombre);
                        page.getByLabel("Primer apellido").click();
                        page.getByLabel("Primer apellido").fill(apel1);
                        page.locator("span").filter(new Locator.FilterOptions().setHasText("Segundo apellido"))
                                        .locator("div")
                                        .nth(2).click();
                        page.getByLabel("Segundo apellido").click();
                        page.getByLabel("Segundo apellido").fill(apel2);
                        page.getByLabel("Fecha de nacimiento").click();
                        page.getByLabel("Fecha de nacimiento").fill("04/04/1998_");
                        page.getByLabel("Fecha de nacimiento").press("Tab");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                                        .selectOption("1: CA");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo"))
                                        .selectOption("1: H");
                        page.getByLabel("Email").click();
                        page.getByLabel("Email").fill("playwright@email.com");
                        page.getByLabel("Teléfono Móvil").click();
                        page.getByLabel("Teléfono Móvil").fill("678987452");
                        page.getByLabel("Teléfono Fijo").click();
                        page.getByLabel("Teléfono Fijo").fill("919159159");
                        page.getByTitle("Añadir/modificar la dirección").click();
                        page.getByLabel("Código postal").click();
                        page.getByLabel("Código postal").fill("28015");
                        page.getByLabel("Población").click();
                        page.getByLabel("Población").fill("asd");
                        page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de vía"))
                                        .selectOption("BXDA");
                        page.getByLabel("Nombre de vía").click();
                        page.getByLabel("Nombre de vía").fill("Princesa");
                        page.getByLabel("Número").click();
                        page.getByLabel("Número").fill("85");
                        page.getByLabel("Portal").click();
                        page.getByLabel("Portal").fill("c");
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