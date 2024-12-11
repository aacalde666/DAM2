package funcionalidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import entidadesMatricula.Matriculas;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Funcionalidades {
	public static Matriculas leerFicheroXMLJAXB(File fichero) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Matriculas.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		Matriculas libros = (Matriculas) u.unmarshal(fichero);
		return libros;
	}
	
	public static void escribirFicheroXML(Matriculas matricula) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Matriculas.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(matricula, getFichero());
	}
	public static File getFichero() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("configuracion.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
		}
		return new File(conf.getProperty("nomfichero"));
	}
}
