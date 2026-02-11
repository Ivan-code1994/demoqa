package pages.bussines;

import org.openqa.selenium.WebElement;

import pages.BasePage;

public class ValidarDatosRegistro extends BasePage{

    public ValidarDatosRegistro(){
        super(driver);
    }

    public boolean validarTexto(String locator, String texto){
        WebElement element = Selector(locator);
        String guardarTexto = element.getText();
        return guardarTexto.contains(texto);
    }
    
}
