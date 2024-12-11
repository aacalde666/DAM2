package handler;

public class Productos {
	private String descripcion;
	private double precio;
	private int cantidad;
	public Productos(String descripcion, double precio, int cantidad) {
		super();
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	public Productos() {
		super();
	}
	public String getNombre() {
		return descripcion;
	}
	public void setNombre(String descripcion) {
		this.descripcion = descripcion;
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
