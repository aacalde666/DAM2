package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.json.JSONArray;
//import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import jakarta.xml.bind.JAXBContext;
//import jakarta.xml.bind.JAXBException;
//import jakarta.xml.bind.Marshaller;
//import jakarta.xml.bind.Unmarshaller;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.util.JRLoader;

public class FuncionalidadesXML {
	/**
	 * Necesitas proporcionar el "fichero".props
	 * 
	 * @param doc
	 * @param ficheroProp 
	 * @param ficheroProps
	 */
	public static void escribirFicheroXML(Document doc, String ficheroProp) {
		eliminarNodosVacios(doc);
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(getFichero(ficheroProp));
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			System.err.println("No se a podido escribir en el fichero");
			e.printStackTrace();
		}
	}
	/**
	 * Recoje el nombre del fichero props
	 * @param ficheroProp 
	 * @param ficheroProps "fichero".props <- ejemplo
	 * @return
	 */
	public static File getFichero(String ficheroProp) {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("configuracion.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
		}
		return new File(conf.getProperty("nomfichero"));
	}
	private static void eliminarNodosVacios(Node node) {
	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node child = nodeList.item(i);
	        if (child.getNodeType() == Node.TEXT_NODE && child.getNodeValue().trim().isEmpty()) {
	            node.removeChild(child);
	            i--;
	        } else if (child.hasChildNodes()) {
	            eliminarNodosVacios(child);
	        }
	    }
	}
	/**
	 * Necesitas proporcionar el "fichero".props
	 * 
	 * @param ficheroProps
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document leerFicheroXML(String ficheroProps) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(getFichero(ficheroProps));
		return doc;
	}
	//////////////////////////////////////////JAXB
	/**
	 * Donde dice Libros ahi va la clase necesaria para leer el fichero con JAXB
	 * @param fichero
	 * @return
	 * @throws JAXBException
	 */
//	private static Libros leerFicheroXMLJAXB(File fichero) throws JAXBException {
//		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);
//		Unmarshaller u = jaxbContext.createUnmarshaller();
//		Libros libros = (Libros) u.unmarshal(fichero);
//		return libros;
//	}
	
//	private static void escribirFicheroXMLJAXB(Pedidos pedido, File fichero) throws JAXBException {
//		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
//		Marshaller marshaller = jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marshaller.marshal(pedido, fichero);
//	}
	///////////////////////SAX
	public void saxParse() throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		//Se crea este metodo
		//HandlerPedidos handlerPedidos = new HandlerPedidos();
		//saxParser.parse("pedidos.xml", handlerPedidos);
	}
	///////////////////////JSON
//	private static JSONArray leerFicheroJSON(File f) throws IOException {
//		String cadena = "";
//		//Nos ayuda a que encuentre el fichero dentro de las carpetas, 
//		//ej: en la carpeta resources del main
//		InputStream  inputStream = Main.class.getClassLoader().getResourceAsStream(f.getName());
//		BufferedReader ent = new BufferedReader(new InputStreamReader(inputStream));
//		String linea;
//		while ((linea = ent.readLine()) != null) {
//			cadena+=linea;
//		}
//		ent.close();
//		JSONArray notas = new JSONArray(cadena);
//		return notas;
//	}
//	public void mostrarContenido() throws IOException {
//		JSONArray notas = leerFicheroJSON(new File("algo.json"));
//		for (int i = 0; i < notas.length(); i++) {
//			JSONObject alumno = notas.getJSONObject(i);
//			String nombre = alumno.getString("nombre");
//			System.out.println("Alumno: "+nombre);
//			JSONArray notasAlumno = alumno.getJSONArray("notas");
//			for (int j = 0; j < notasAlumno.length(); j++) {
//				JSONObject nota = notasAlumno.getJSONObject(j);
//				System.out.println(nota.getString("materia")+":"+nota.getInt("nota"));
//			}
//		}
//	}
	////////////////////////////////JASPER_REPORTS
//	public static void generarInformes(List<DatosAlumno> datos) throws JRException {
//		String ficheroJasper = "C:\\Users\\aacal\\JaspersoftWorkspace\\MyReports\\listadoAlumnos.jasper";
//		String informePdf = "reports\\listadoAlumnos.pdf";
//		JRBeanCollectionDataSource camposInformes = new JRBeanCollectionDataSource(datos);
//		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
//		Map<String, Object> params = new HashMap<>();
//		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInformes);
//		JasperExportManager.exportReportToPdfFile(informe, informePdf);
//		
//	}
}
