package com.testgenerator;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class Test3 {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(true));
      BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
      Page page = context.newPage();
      page.navigate("https://20.67.52.193/sisnet/");
      page.getByLabel("Usuario:").click();
      page.getByLabel("Usuario:").fill("autoqa");
      page.getByLabel("Usuario:").press("Tab");
      page.getByLabel("Clave:").fill("netijam");
      page.getByLabel("Clave:").press("Enter");
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Suscribir")).click();
      Page page1 = page.waitForPopup(() -> {
        page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("")).click();
      });
      page1.getByText("* Nombre: * N.I.F.: Ej: 23444332R / B43553495").click();
      page1.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("NIF").setExact(true)).getByRole(AriaRole.RADIO).check();
      page1.close();
    //   page1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cerrar ventana")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("CRM")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Responsabilidad Civil Animales")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Red comercial")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Gestión de proyectos")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Gestión de pólizas")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Pólizas nuevas producción")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Periodos de póliza")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("Efecto periodo").check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("1. Selección")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName(" Menú")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Responsabilidad Civil Animales")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("* Mediador").click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.MENUITEM, new FrameLocator.GetByRoleOptions().setName("AUTOQATEST1 - TERRANEA 2000 CORREDURIA DE SEGUROS SL")).getByText("AUTOQATEST1 - TERRANEA 2000 CORREDURIA DE SEGUROS SL").click();
      page.frameLocator("iframe[name=\"cab\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Logo")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName(" Novedades (0)")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Gestión de pólizas")).click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("CRM")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("Personas póliza")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.CHECKBOX, new FrameLocator.GetByRoleOptions().setName("Identificador de póliza")).check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("Año póliza").check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.CHECKBOX, new FrameLocator.GetByRoleOptions().setName("Código de origen")).check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("Usuario contratación").check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("Usuario de alta").check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByLabel("Tipo de póliza").check();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("4 MEDI AUTOQA GENERAL 253654 2023.0 AUTOQA")).getByText("MEDI").click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByText("Ir a póliza").click();
      page.frameLocator("iframe[name=\"toc\"]").getByText("4. Ejecución").click();
      page.frameLocator("iframe[name=\"toc\"]").getByText("4. Ejecución").click();
      page.frameLocator("iframe[name=\"toc\"]").getByRole(AriaRole.LINK, new FrameLocator.GetByRoleOptions().setName("3. Procedimientos")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Continuar")).click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("2 MEDI AUTOQA GENERAL 253654 2023.0 AUTOQA")).getByText("AUTOQA").first().click();
      page.frameLocator("iframe[name=\"cuerpo\"]").getByText("Ir a póliza").click();
    }
  }
}
