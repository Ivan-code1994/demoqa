package pages.demoQA;

import org.openqa.selenium.Keys;

import pages.BasePage;
import pages.bussines.ValidarDatosRegistro;
import steps.Utils.DataManager;

public class DemoQA extends BasePage{

    public DemoQA(){
        super(driver);
    }

    ValidarDatosRegistro registro = new ValidarDatosRegistro();

    /* Captura de datos */
    String firstName = "//input[@placeholder='First Name']";
    String lastName = "//input[@placeholder='Last Name']";
    String mail = "//input[@placeholder='name@example.com']";
    String gener = "//label[@for='gender-radio-1']";
    String movil = "//input[contains(@placeholder,'Mobile Number')]";
    String calendar = "(//input[contains(@type,'text')])[5]";
    String subject = "(//input[contains(@type,'text')])[6]";
    String hobbie = "//label[@for='hobbies-checkbox-1']";
    String adress = "//textarea[@placeholder='Current Address']";
    String buttonSubmit = "//button[normalize-space()='Submit']";
    
    /* Celdas a validar */

    String nombreCompleto = "//td[contains(normalize-space(string(.)), 'Ivan Aquino')]";

    public void navegarDemoQA(){
        
        webUrl("https://demoqa.com/automation-practice-form");
    }

    public void capturarDatosFormulario(){

        String nombre = capturarTexto(firstName, "Ivan");
        DataManager.guardarDato("student", "Nombre", nombre);

        String apellido = capturarTexto(lastName, "Aquino");
        DataManager.guardarDato("student", "Apellido", apellido);

        String correo = capturarTexto(mail, "prueba@ejemplo.com");
        DataManager.guardarDato("student", "Correo", correo);

        presionarBoton(gener);
        DataManager.guardarDato("student", "Genero", "Male");
        
        String telefono = capturarTexto(movil, "5522334455");
        DataManager.guardarDato("student", "Telefono", telefono);

        String fecha = capturarTexto(calendar, "24 July 1994" + Keys.ENTER);
        DataManager.guardarDato("student", "Fecha de nacimiento", fecha);

        String objeto = capturarTexto(subject, "pruebas");
        DataManager.guardarDato("student", "Objeto", objeto);

        presionarBoton(hobbie);
        DataManager.guardarDato("student", "Pasatiempo", "Sports");

        String direccion = capturarTexto(adress, "Captura de direccion");
        DataManager.guardarDato("student", "Direccion", direccion);
        presionarBoton(buttonSubmit);

    }

    public boolean validarFormularioCapturado(){

        String nombre = DataManager.leerDato("student", "Nombre");
        String apellido = DataManager.leerDato("student", "Apellido");

        return registro.validarTexto(nombreCompleto, nombre + " " + apellido);
    }
    
}
