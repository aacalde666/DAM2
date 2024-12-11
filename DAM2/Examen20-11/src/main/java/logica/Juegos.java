package logica;

import jakarta.xml.bind.annotation.XmlElement;

public class Juegos {
	private int id_juego;
	private String titulo,consola,estado;
	public Juegos(int id_juego, String titulo, String consola, String estado) {
		this.id_juego = id_juego;
		this.titulo = titulo;
		this.consola = consola;
		this.estado = estado;
	}
	public Juegos() {
	}
	@XmlElement(name = "ID_Juego")
	public int getId_juego() {
		return id_juego;
	}
	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}
	@XmlElement(name = "titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@XmlElement(name = "consola")
	public String getConsola() {
		return consola;
	}
	public void setConsola(String consola) {
		this.consola = consola;
	}
	@XmlElement(name = "estado")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
