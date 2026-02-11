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
        chromeOptions.addArguments("----start-maximized");
        chromeOptions.addArguments("--force-device-scale-factor=0.9");

        driver = new ChromeDriver(chromeOptions);
    }

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    public static void salirNavegacion(){
        driver.quit();
    }

    /** Interaccion con la UI**/

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
