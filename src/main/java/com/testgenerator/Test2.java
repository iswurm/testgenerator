package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class Test2 {
    public static void main(String[] args) {
    }

    public void ejecutar(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
            Page page = context.newPage();
            page.setDefaultTimeout(6000);
            page.navigate("https://20.67.52.193/sisnet/");
            page.getByLabel("Usuario:").click();
            page.getByLabel("Usuario:").fill("Autoqa");
            page.getByLabel("Usuario:").press("Tab");
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
                    .selectOption("3: TA03");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tramo edad"))
                    .selectOption("1: T110");
            page.getByLabel("Valor").click();
            page.getByLabel("Valor").fill("28791");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Función")).selectOption("1: U001");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Alojamiento"))
                    .selectOption("1: A001");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.navigate(
                    "https://20.67.52.193/tarificacion-rc01?dagennot=202609&idSession=BB18521009F25D5C88591DA8B42BB333&INITDAT=true&lob=RC01");
            page.navigate(
                    "https://20.67.52.193/tarificacion-rc01/precio?dagennot=202609&idSession=BB18521009F25D5C88591DA8B42BB333&INITDAT=true&lob=RC01");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.getByLabel("Nombre").click();
            page.getByLabel("Nombre").fill("test");
            page.getByLabel("Fecha de nacimiento").click();
            page.getByLabel("Fecha de nacimiento").fill("24/03/2000");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo animal"))
                    .selectOption("2: S004");
            page.getByLabel("Nº Microchip").click();
            page.getByLabel("Nº Microchip").fill("666");
            page.getByLabel("Raza").click();
            page.getByLabel("Raza").fill("6666");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de documento"))
                    .selectOption("4: NIF");
            page.getByLabel("NIF/NIE").click();
            page.getByLabel("NIF/NIE").fill("99102987N");
            page.getByLabel("Nombre").click();
            page.getByLabel("Nombre").fill("test");
            page.getByLabel("Primer apellido").click();
            page.getByLabel("Primer apellido").fill("test");
            page.getByLabel("Segundo apellido").fill("t");
            page.getByLabel("Segundo apellido").click();
            page.getByLabel("Segundo apellido").fill("test");
            page.getByLabel("Fecha de nacimiento").click();
            page.getByLabel("Fecha de nacimiento").fill("24/03/2000");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Estado civil"))
                    .selectOption("1: CA");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sexo")).selectOption("1: H");
            page.getByLabel("Email").click();
            page.getByLabel("Email").fill("m@mail.com");
            page.getByLabel("Teléfono Móvil").click();
            page.getByLabel("Teléfono Móvil").fill("645645645");
            page.getByLabel("Teléfono Móvil").press("Tab");
            page.getByLabel("Teléfono Fijo").fill("911911911");
            page.getByTitle("Añadir/modificar la dirección").click();
            page.getByLabel("Código postal").click();
            page.getByLabel("Código postal").fill("28971");
            page.getByLabel("Población").click();
            page.getByLabel("Población").fill("Madrid");
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Tipo de vía")).selectOption("ALDEA");
            page.getByLabel("Nombre de vía").click();
            page.getByLabel("Nombre de vía").fill("Madrid");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Aceptar")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.locator("label").filter(
                    new Locator.FilterOptions().setHasText("¿El propietario del animal es el mismo que el tomador?"))
                    .click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.locator("label").filter(
                    new Locator.FilterOptions().setHasText("¿El asegurado es el mismo que el propietario/tomador?"))
                    .click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuar")).click();
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Medios de pago"))
                    .selectOption("1: EFEC");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Emitir")).click();
            assertThat(page.locator("#stateDependantBackgroundCIERRE00 > div")).containsText("correctamente");
        }
    }
}