package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Funcionalidades {
	
	public static int getNota(String nombre) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		int nota = 0;
		Document doc = leerFicheroXML();
		NodeList nodosHijosDelDocumento = doc.getChildNodes();
		Node nodoRaiz = nodosHijosDelDocumento.item(0);
		nodoRaiz = doc.getFirstChild();
		NodeList listaNodosAlumnos = nodoRaiz.getChildNodes();
		for (int i = 0; i < listaNodosAlumnos.getLength(); i++) {
			Node alumno = listaNodosAlumnos.item(i);
			if(alumno.getNodeType() == Node.ELEMENT_NODE) {
				if (getNombreNodoAlumno(alumno).equals(nombre)) {
					nota = getNotaNodoAlumno(alumno);
				}
			}
		}
		return nota;
	}

	private static int getNotaNodoAlumno(Node alumno) {
		int nota = 0;
		NodeList datosAlumno = alumno.getChildNodes();
		for (int i = 0; i < datosAlumno.getLength(); i++) {
			Node dato = datosAlumno.item(i);
			if(dato.getNodeName().equals("nota")) {
				nota = Integer.parseInt(dato.getFirstChild().getNodeValue());
			}
		}
		return nota;
	}
	public static void insertarAlumno(String nombre, int nota) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Document doc = leerFicheroXML();
		Element nuevoAlumno = doc.createElement("alumno");
		Node raiz = doc.getFirstChild();
		raiz.appendChild(nuevoAlumno);
		Element nodoNombre = doc.createElement("nombre");
		nodoNombre.appendChild(doc.createTextNode(nombre));
		nuevoAlumno.appendChild(nodoNombre);
		Element nodoNota = doc.createElement("nota");
		nodoNota.appendChild(doc.createTextNode(nota+""));
		nuevoAlumno.appendChild(nodoNota);
		escribirFicheroXML(doc);
	}
	private static String getNombreNodoAlumno(Node alumno) {
		String nombre = "";
		NodeList datosAlumno = alumno.getChildNodes();
		for (int i = 0; i < datosAlumno.getLength(); i++) {
			Node dato = datosAlumno.item(i);
			if(dato.getNodeName().equals("nombre")) {
				nombre = dato.getFirstChild().getNodeValue();
			}
		}
		return nombre;
	}

	private static File getFichero() throws FileNotFoundException, IOException {
		Properties configuracion = new Properties();
		configuracion.load(new FileInputStream("conf.props"));
		return new File(configuracion.getProperty("file"));
	}
	public static void modNota(String nombre, int nota) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Document doc = leerFicheroXML();
		Node raiz = doc.getFirstChild();
		NodeList alumnos = raiz.getChildNodes();
		for (int i = 0; i < alumnos.getLength(); i++) {
			Node alumno = alumnos.item(i);
			if (alumno.getNodeType() == Node.ELEMENT_NODE) {
				if (getNombreNodoAlumno(alumno).equals(nombre)) {
					modificarNotaAlumno(doc, alumno, nota);
				}
			}
		}
		escribirFicheroXML(doc);
	}
	private static void modificarNotaAlumno(Document doc, Node alumno, int nota) {
		NodeList datosAlumno = alumno.getChildNodes();
		for (int i = 0; i < datosAlumno.getLength(); i++) {
			if(datosAlumno.item(i).getNodeName().equals("nota")) {
				Node notaNodo = datosAlumno.item(i);
				notaNodo.removeChild(notaNodo.getFirstChild());
				Text newNode = doc.createTextNode(nota+"");
				notaNodo.appendChild(newNode);
			}
		}
	}

	private static Document leerFicheroXML() throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(getFichero());
		return doc;
	}
	private static void escribirFicheroXML(Document doc) throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Source source = new DOMSource(doc);
		Result result = new StreamResult(getFichero());
		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
	}
	public static List<String> listaAprobados() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		List<String> aprobados = new LinkedList<String>();
		Document doc = leerFicheroXML();
		for (int i = 0; i < doc.getFirstChild().getChildNodes().getLength(); i++) {
			Node dato = doc.getFirstChild().getChildNodes().item(i);
			if (dato.getNodeType() == Node.ELEMENT_NODE) {
//				if(getNotaNodoAlumno(dato)>=5) {
//					aprobados.add(getNombreNodoAlumno(dato));
//				}
				Element alum = (Element) dato;
				int nota = Integer.parseInt(alum.getElementsByTagName("nota").item(0).getTextContent());
				if (nota>=5) {
				aprobados.add(alum.getElementsByTagName("nombre").item(0).getTextContent());
				}
			}
		}
		return aprobados;
	}
}
