package steps.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataManager {

       private static final String FILE_PATH = "src/test/resources/data/JSON/registro.json";

    // Guardar un dato dentro de una sección
    public static void guardarDato(String seccion, String clave, String valor) {

        JSONObject json = leerJSONCompleto(); // Leer JSON existente

        // Revisar si la sección existe, si no crearla
        JSONObject seccionObj = (JSONObject) json.get(seccion);
        if (seccionObj == null) {
            seccionObj = new JSONObject();
        }

        // Agregar el dato dentro de la sección
        seccionObj.put(clave, valor);

        // Guardar la sección de vuelta al JSON principal
        json.put(seccion, seccionObj);

        // Guardar archivo
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(json.toJSONString());
            file.flush();
            System.out.println("Guardado: [" + seccion + "] " + clave + " = " + valor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer un dato desde una sección
    public static String leerDato(String seccion, String clave) {
        JSONObject json = leerJSONCompleto();

        JSONObject seccionObj = (JSONObject) json.get(seccion);
        if (seccionObj == null) {
            return null;
        }

        return (String) seccionObj.get(clave);
    }

    // Leer el JSON completo
    private static JSONObject leerJSONCompleto() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            return new JSONObject(); // si no existe, retorna JSON vacío
        }
    }
    
}
