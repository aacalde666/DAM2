package entidadesLibros;

import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "librosCasa")
public class Libros {
	private List<Libro> libro;

	public List<Libro> getLibro() {
		return libro;
	}
	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	public Libros(List<Libro> libro) {
		super();
		this.libro = libro;
	}
	public Libros() {
		super();
	}
	
}
