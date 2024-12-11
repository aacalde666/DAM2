package logica;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
@XmlType(propOrder = {"titulo","nombre","stock"})
public class Libro {
	private String titulo;
	private List<String> nombre = new LinkedList<>();
	private int stock;
	public Libro(String titulo, List<String> autores, int stock) {
		this.titulo = titulo;
		this.nombre = autores;
		this.stock = stock;
	}
	public Libro() {
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@XmlElementWrapper(name = "autores")
	@XmlElement(name = "nombre")
	public List<String> getNombre() {
		return nombre;
	}
	public void setNombre(LinkedList<String> autores) {
		this.nombre = autores;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "titulo=" + titulo + ", autores=" + nombre + ", stock=" + stock;
	}
}
