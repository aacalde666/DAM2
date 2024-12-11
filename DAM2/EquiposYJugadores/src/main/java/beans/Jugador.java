package beans;

import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombre;
	int numero;
	String posicion;
	public Jugador(String nombre, int posJugador, String posicion) {
		super();
		this.nombre = nombre;
		this.numero = posJugador;
		this.posicion = posicion;
	}
	public Jugador() {
		super();
	}
	public Jugador(String nombreJugador) {
		this.nombre=nombreJugador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre)|Objects.hash(numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(nombre, other.nombre)|Objects.equals(numero, other.numero);
	}
	@Override
	public String toString() {
		return " nombre=" + nombre + ", numero=" + numero + ", posicion=" + posicion;
	}
	
}
