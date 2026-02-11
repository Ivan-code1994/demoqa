package steps.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.BasePage;

public class Hooks {

    @Before
    public void upWebNavegador(){

        System.out.println("Iniciando navegador");
        BasePage.iniciarNavegador();
    }

    @After
    public void downWebNavegador(){
        
        System.out.println("Cerrando el navegador");
        BasePage.salirNavegacion();
    }
    
}
