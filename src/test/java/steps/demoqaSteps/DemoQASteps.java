package steps.demoqaSteps;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.demoQA.DemoQA;

public class DemoQASteps {

    DemoQA demo = new DemoQA();

    @Given("^Abrir pagina DemoQa")
    public void abrirDemoQAStep(){
        demo.navegarDemoQA();
    }

    @Then("^Capturar los datos del formulario")
    public void capturarDatosFormulario(){
        demo.capturarDatosFormulario();
        assertTrue("Formulario validado", demo.validarFormularioCapturado());
    }
    
}
