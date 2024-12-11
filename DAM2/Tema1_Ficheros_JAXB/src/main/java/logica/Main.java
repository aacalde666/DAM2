package logica;

import java.io.File;

import entidadesLibros.Libro;
import entidadesLibros.Libros;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Main {
	public static void main(String[] args) throws JAXBException {
		//leer el documento xml: pasar el contenido del fichero xml a un objeto libros
		Libros libros = leerFicheroXML(new File("libros.xml"));
		System.out.println(getTituloAutor(libros, "Peppe"));
	}
	private static Libros leerFicheroXML(File fichero) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		Libros libros = (Libros) u.unmarshal(fichero);
		return libros;
	}
	private static String getTituloAutor(Libros libros, String autor) {
		String result = null;
		for (Libro l : libros.getLibro()) {
			if (l.getAutor().equals(autor)) {
				result = l.getTitulo();
			}
		}
		return result;
	}
}
