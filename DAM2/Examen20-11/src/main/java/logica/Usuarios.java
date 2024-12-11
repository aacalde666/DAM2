package logica;

import jakarta.xml.bind.annotation.XmlElement;

public class Usuarios {
	private int id_usuario;
	private String nombre;
	public Usuarios(int id_usuario, String nombre) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
	}
	public Usuarios() {
	}
	@XmlElement(name = "ID_Usuario")
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	@XmlElement()
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
