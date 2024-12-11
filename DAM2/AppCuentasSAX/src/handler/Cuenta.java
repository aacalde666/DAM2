package handler;

import java.util.LinkedList;

public class Cuenta {
	private LinkedList<String> titulares = new LinkedList<>();
	private int id, cantidad;
	public Cuenta(LinkedList<String> titulares, int id, int cantidad) {
		this.titulares = titulares;
		this.id = id;
		this.cantidad = cantidad;
	}
	public Cuenta() {
	}
	public LinkedList<String> getTitulares() {
		return titulares;
	}
	public void setTitulares(LinkedList<String> titulares) {
		this.titulares = titulares;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
