package beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class Equipo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombre;
	HashSet<Jugador> jugadors = new HashSet<Jugador>();
	public Equipo(String nombre, HashSet<Jugador> jugadors) {
		super();
		this.nombre = nombre;
		this.jugadors = jugadors;
	}
	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}
	public Equipo() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public HashSet<Jugador> getJugadors() {
		return jugadors;
	}
	public HashSet<Jugador> setJugadors(HashSet<Jugador> jugadors) {
		return this.jugadors = jugadors;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", jugador=" + jugadors + " ]";
	}
	
}
