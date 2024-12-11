package logica;

import java.util.LinkedList;

import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Libros {
	private LinkedList<Libro> libros = new LinkedList<Libro>();
	public Libros(LinkedList<Libro> libros) {
		this.libros = libros;
	}
	public Libros() {
	}
	public LinkedList<Libro> getLibros() {
		return libros;
	}
	public void setLibros(LinkedList<Libro> libros) {
		this.libros = libros;
	}
	@Override
	public String toString() {
		return "Libros [libros=" + libros + "]";
	}
}
