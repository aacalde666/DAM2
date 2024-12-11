package logica;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class AlumnosNotas {

    public static void main(String[] args) {
        try {
            // Ruta del archivo XML
            File xmlFile = new File("alumnos.xml");
            
            // Crear el DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            // Parsear el archivo XML
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            // Llamada al método para obtener la nota de un alumno
            String nombreAlumno = "Javier"; // Cambia el nombre del alumno aquí
            String nota = obtenerNotaPorNombre(doc, nombreAlumno);
            
            if (nota != null) {
                System.out.println("La nota de " + nombreAlumno + " es: " + nota);
            } else {
                System.out.println("El alumno " + nombreAlumno + " no fue encontrado.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la nota de un alumno por su nombre
    public static String obtenerNotaPorNombre(Document doc, String nombreBuscado) {
        NodeList listaAlumnos = doc.getElementsByTagName("alumno");

        // Iterar sobre cada alumno
        for (int i = 0; i < listaAlumnos.getLength(); i++) {
            Node nodoAlumno = listaAlumnos.item(i);

            if (nodoAlumno.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoAlumno = (Element) nodoAlumno;
                
                // Obtener el nombre del alumno
                String nombre = elementoAlumno.getElementsByTagName("nombre").item(0).getTextContent();
                
                // Comparar con el nombre buscado
                if (nombre.equalsIgnoreCase(nombreBuscado)) {
                    // Retornar la nota si se encuentra el alumno
                    return elementoAlumno.getElementsByTagName("nota").item(0).getTextContent();
                }
            }
        }
        // Retornar null si no se encuentra el alumno
        return null;
    }
}
