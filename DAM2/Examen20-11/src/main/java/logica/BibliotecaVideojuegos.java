package logica;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlRootElement(name = "BibliotecaVideojuegos")
@XmlType(propOrder = {"usuarios","juegos","intercambios"})
public class BibliotecaVideojuegos {
	private List<Usuarios> usuarios = new LinkedList<>();
	private List<Juegos> juegos = new LinkedList<>();
	private List<Intercambios> intercambios = new LinkedList<>();
	public BibliotecaVideojuegos(List<Usuarios> usuarios, List<Juegos> juegos, List<Intercambios> intercambios) {
		this.usuarios = usuarios;
		this.juegos = juegos;
		this.intercambios = intercambios;
	}
	public BibliotecaVideojuegos() {
	}
	@XmlElementWrapper(name = "usuarios")
	@XmlElement(name = "usuario")
	public List<Usuarios> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
	@XmlElementWrapper(name = "juegos")
	@XmlElement(name = "juego")
	public List<Juegos> getJuegos() {
		return juegos;
	}
	public void setJuegos(List<Juegos> juegos) {
		this.juegos = juegos;
	}
	@XmlElementWrapper(name = "intercambios")
	@XmlElement(name = "intercambio")
	public List<Intercambios> getIntercambios() {
		return intercambios;
	}
	public void setIntercambios(List<Intercambios> intercambios) {
		this.intercambios = intercambios;
	}
	
}
