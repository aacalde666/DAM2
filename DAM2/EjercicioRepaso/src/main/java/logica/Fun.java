package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Fun {
	public static File getFichero() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("conf.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
		}
		return new File(conf.getProperty("fichXML"));
	}
	public static Document leerFicheroXML() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = db.parse(getFichero());
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	public static void mostrarTodoElContenido(Document doc, String nodoName, String value) {
	    Node raiz = doc.getDocumentElement();
	    mostrarTodosLosNodos(raiz, nodoName, value);
	}
	private static void mostrarTodosLosNodos(Node nodo, String nodoName, String value) {
		mostrarTexto(nodo, nodoName, value);
	    NodeList hijos = nodo.getChildNodes();
	    for (int i = 0; i < hijos.getLength(); i++) {
	        mostrarTodosLosNodos(hijos.item(i), nodoName, value);
	    }
	}
	private static void mostrarTexto(Node nodo, String nodoName, String value) {
		if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	    	if (nodo.getNodeName().equals(nodoName)) {
				System.out.println(nodo.getTextContent());
			}else if (nodo.getTextContent().equals(value)) {
				System.out.println(nodo.getTextContent());
			}
	    }
	}
	/* -	Dado el título de un libro, indicar si hay ejemplares disponibles del mismo (2 pt.)
	 * -	Mostrar el número de usuarios que tienen libros reservados (2 pt.)
	 */
	public static void ejemplaresDisponibles(String value) {
		mostrarTodoElContenido(leerFicheroXML(),"ejemplares_disponibles", value);
	}
}
