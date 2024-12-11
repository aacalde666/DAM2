package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import logica.Fun;

public class Main {
	public static void main(String[] args) {
		Fun.mostrarTodoElContenido(Fun.leerFicheroXML(), "titulo", "");
		Fun.ejemplaresDisponibles("Cien años de soledad");
	}
}
