package beans;

public class Empleado {
	
	private String nif, nombre;
	private double sueldo;
	
	public Empleado(String nif, String nombre, double sueldo) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.sueldo = sueldo;
	}
	public Empleado() {
		super();
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	@Override
	public String toString() {
		return "Empleado [nif=" + nif + ", nombre=" + nombre + ", sueldo=" + sueldo + "]";
	}
	
	

}
