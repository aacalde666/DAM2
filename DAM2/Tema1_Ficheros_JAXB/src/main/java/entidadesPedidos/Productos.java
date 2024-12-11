package entidadesPedidos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlType(propOrder = {"nombre","precio","cantidad"})
public class Productos {
	private String nombre;
	private double precio;
	private int cantidad;
	public Productos(String nombre, double precio, int cantidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	public Productos() {
		super();
	}
	@XmlElement(name = "descripcion")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
