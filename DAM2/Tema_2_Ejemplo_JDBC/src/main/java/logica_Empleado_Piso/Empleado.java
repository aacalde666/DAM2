package logica_Empleado_Piso;

public class Empleado {
	private String nif;
    private String nombre;
    private double sueldoBase;
	public Empleado(String nif, String nombre, double sueldoBase) {
		this.nif = nif;
		this.nombre = nombre;
		this.sueldoBase = sueldoBase;
	}
	public Empleado() {
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
	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
    
}
