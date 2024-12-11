package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import UtilidadesTeclado.Teclado;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Funciones {
	public static File getFichero() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("conf.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
		}
		return new File(conf.getProperty("fichXML"));
	}
	public static Document leerFicheroXML(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
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
	
	static BibliotecaVideojuegos leerFicheroXMLJAXB() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(BibliotecaVideojuegos.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		BibliotecaVideojuegos libros = (BibliotecaVideojuegos) u.unmarshal(getFichero());
		return libros;
	}
	
	static void escribirFicheroXMLJAXB(BibliotecaVideojuegos bv) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(BibliotecaVideojuegos.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(bv, getFichero());
	}
	
	public static void generarInformes(List<BibliotecaVideojuegos> bib) throws JRException {
		String ficheroJasper = "C:\\Users\\aacal\\JaspersoftWorkspace\\MyReports\\listadoAlumnos.jasper";
		String informePdf = "reports\\listadoAlumnos.pdf";
		JRBeanCollectionDataSource camposInformes = new JRBeanCollectionDataSource(bib);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
		Map<String, Object> params = new HashMap<>();
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInformes);
		JasperExportManager.exportReportToPdfFile(informe, informePdf);
		
	}
}
