package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import main.Main;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Funciones {
	public static File getFichero(String nomfichero) {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("conf.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el ficher "+conf.toString());
		}
		return new File(conf.getProperty(nomfichero));
	}
	public static void generarInformes(List<Libro> libros) throws JRException {
		String ficheroJasper = "C:\\Users\\aacal\\JaspersoftWorkspace\\MyReports\\Libros.jasper";
		String informePdf = "reports\\Libros.pdf";
		JRBeanCollectionDataSource camposInformes = new JRBeanCollectionDataSource(libros);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
		Map<String, Object> params = new HashMap<>();
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInformes);
		JasperExportManager.exportReportToPdfFile(informe, informePdf);
		
	}
	public static JSONArray leerFicheroJSON(File f) throws IOException {
		String cadena = "";
		InputStream  inputStream = Main.class.getClassLoader().getResourceAsStream(f.getName());
		BufferedReader ent = new BufferedReader(new InputStreamReader(inputStream));
		String linea;
		while ((linea = ent.readLine()) != null) {
			cadena+=linea;
		}
		ent.close();
		JSONArray datos = new JSONArray(cadena);
		return datos;
	}
	
	public static Document leerFicheroXML() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(getFichero("fichxml"));
		return doc;
	}
	private static Libros cargarContenidoJSON(){
		JSONArray lib = null;
		try {
			lib = leerFicheroJSON(getFichero("fichjson"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Libros libros = new Libros();
		for (int i = 0; i < lib.length(); i++) {
			List<String> autor = new LinkedList<>();
			JSONObject libObject = lib.getJSONObject(i);
			String titulo = libObject.getString("titulo");
			JSONArray ar = (JSONArray) libObject.get("autores");
			for (int j = 0; j < ar.length(); j++) {
				autor.add(ar.getString(j));
			}
			int stock = libObject.getInt("stock");
			libros.getLibros().add(new Libro(titulo, autor, stock));
		}
		return libros;
	}
	public static void verTodosLosAutores() {
		HashSet<String> autor = new HashSet<String>();
		for (Libro libro : cargarContenidoJSON().getLibros()) {
			for (String autores : libro.getNombre()) {
				autor.add(autores);
			}
		}
		for (String autores : autor) {
			System.out.println(autores);
		}
	}
	public static void mostrarLibrosPorUnAutor(String aut) {
		Libros libros = new Libros();
		for (Libro libro : cargarContenidoJSON().getLibros()) {
			for (String autor : libro.getNombre()) {
				if (autor.equals(aut)) {
					System.out.println(libro.getTitulo());
					libros.getLibros().add(libro);
				}
			}
		}
		try {
			escribirFicheroXMLJAXB(libros, new File("libros.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	private static void escribirFicheroXMLJAXB(Libros libros, File fichero) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(libros, fichero);
	}
	public static List<Libro> generarPDF(String autor) {
		List<Libro> libros = new LinkedList<Libro>();
		try {
			Document doc = leerFicheroXML();
			NodeList listaLibros = doc.getElementsByTagName("libros");
	        for (int i = 0; i < listaLibros.getLength(); i++) {
	            Node nodoLibro = listaLibros.item(i);
	            if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
	                Element elementoLibro = (Element) nodoLibro;
	                String titulo = elementoLibro.getElementsByTagName("titulo").item(0).getTextContent();
	                List<String> autores = new LinkedList<>();
	                NodeList listaAutores = elementoLibro.getElementsByTagName("nombre");
	                for (int j = 0; j < listaAutores.getLength(); j++) {
	                	if (listaAutores.item(j).getTextContent().equals(autor)) {
	                		autores.add(listaAutores.item(j).getTextContent());
						}
	                }
	                int stock = Integer.parseInt(elementoLibro.getElementsByTagName("stock").item(0).getTextContent());
	                libros.add(new Libro(titulo, autores, stock));
	            }
	        }
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return libros;
	}
}
