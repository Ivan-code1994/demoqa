package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void iniciarNavegador(){

        WebDriverManager.chromedriver().clearDriverCache().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        
        // 🔴 DETECTAR SI ESTÁ EN GITHUB ACTIONS
        String isCI = System.getenv("CI");
        
        if (isCI != null && isCI.equals("true")) {
            // ✅ MODO HEADLESS PARA GITHUB ACTIONS
            System.out.println("🚀 Ejecutando en GitHub Actions - Modo Headless");
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--force-device-scale-factor=0.9");
            chromeOptions.addArguments("--remote-allow-origins=*");
        } else {
            // ✅ MODO NORMAL PARA LOCAL
            System.out.println("🚀 Ejecutando en Local - Modo Normal");
            chromeOptions.addArguments("--start-maximized");  // 🔴 CORREGIDO: 2 guiones
            chromeOptions.addArguments("--force-device-scale-factor=0.9");
        }

        driver = new ChromeDriver(chromeOptions);
    }

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    public static void salirNavegacion(){
        if (driver != null) {
            driver.quit();
        }
    }

    /* Interaccion con la UI*/

    public void webUrl(String url){
        driver.get(url);
    }

    public WebElement Selector(String locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void presionarBoton(String locator){
        Selector(locator).click();
    }

    public String capturarTexto(String locator, String texto){
        Selector(locator).clear();
        Selector(locator).sendKeys(texto.trim());
        return texto.trim();
    }
    
}
