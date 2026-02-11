package steps.hooks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.cucumber.java.BeforeAll;

public class AllureMetadataHook {
    
    @BeforeAll
    public static void crearMetadataAllure() throws Exception {

        File carpeta = new File("allure-results");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        crearEnvironment();
        crearExecutor();
        crearCategories();
    }

    private static void crearEnvironment() throws IOException {
        File f = new File("allure-results/environment.properties");
        try (FileWriter w = new FileWriter(f)) {
            w.write("Browser=Chrome\n");
            w.write("OS=" + System.getProperty("os.name") + "\n");
            w.write("Tester=Ivan\n");
            w.write("Env=QA\n");
        }
    }

    private static void crearExecutor() throws IOException {
        String json = """
                {
                  "name": "Local Execution",
                  "type": "local",
                  "buildOrder": 1,
                  "buildName": "Run Local"
                }
                """;

        File f = new File("allure-results/executor.json");
        try (FileWriter w = new FileWriter(f)) {
            w.write(json);
        }
    }

    private static void crearCategories() throws IOException {
        String json = """
                [
                  {
                    "name": "Errores del servidor / Ambiente caído",
                    "messageRegex": ".*(500|501|502|503|504|505).*|.*Connection refused.*|.*Service Unavailable.*|.*Connection timed out.*"
                  },
                  {
                    "name": "Errores de API / Backend",
                    "messageRegex": ".*HTTP.*400.*|.*HTTP.*401.*|.*HTTP.*403.*|.*HTTP.*404.*|.*HTTP.*409.*|.*HTTP.*422.*"
                  },
                  {
                    "name": "Timeouts",
                    "messageRegex": ".*Timeout.*|.*timed out.*|.*Wait timed out.*"
                  },
                  {
                    "name": "Elemento no encontrado",
                    "messageRegex": ".*NoSuchElementException.*|.*ElementNotInteractableException.*|.*ElementClickInterceptedException.*"
                  },
                  {
                    "name": "Problemas de sincronización",
                    "messageRegex": ".*StaleElementReferenceException.*|.*ElementNotVisibleException.*|.*ElementNotSelectableException.*"
                  },
                  {
                    "name": "Errores técnicos de Java",
                    "messageRegex": ".*NullPointerException.*|.*IllegalStateException.*|.*IndexOutOfBoundsException.*|.*ClassCastException.*"
                  },
                  {
                    "name": "Fallas funcionales (Reglas de negocio)",
                    "messageRegex": ".*Monto inválido.*|.*Datos no válidos.*|.*Operación no permitida.*"
                  },
                  {
                    "name": "Fallas conocidas / Flaky tests",
                    "messageRegex": ".*flaky.*|.*intermitente.*"
                  }
                ]
                """;

        File f = new File("allure-results/categories.json");
        try (FileWriter w = new FileWriter(f)) {
            w.write(json);
        }
    }
}
