package entidadesMatricula;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"precio","nombre"})
public class Asignaturas {
	private double precio;
	private String nombre;
	public Asignaturas(double precio, String nombre) {
		super();
		this.precio = precio;
		this.nombre = nombre;
	}
	public Asignaturas() {
		super();
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Asignatura\n	-> precio=" + precio + ", nombre=" + nombre + "\n	";
	}
}
