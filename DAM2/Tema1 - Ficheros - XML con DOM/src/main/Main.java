package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import logica.Funcionalidades;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
//		System.out.println(Funcionalidades.getNota("Eva"));
//		Funcionalidades.modNota("Eva", 5);
//		System.out.println(Funcionalidades.getNota("Eva"));
//		Funcionalidades.insertarAlumno("Manolo", 1);
//		System.out.println(Funcionalidades.getNota("Manolo"));
		System.out.println(Funcionalidades.listaAprobados());
	}

}
