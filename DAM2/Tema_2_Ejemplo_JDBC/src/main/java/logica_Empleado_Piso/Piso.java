package logica_Empleado_Piso;

public class Piso {
	private int codigo;
    private String direccion;
    private double mensualidad;
    private boolean alquilado;
    private String nifEmpleado;
	public Piso(int codigo, String direccion, double mensualidad, boolean alquilado, String nifEmpleado) {
		this.codigo = codigo;
		this.direccion = direccion;
		this.mensualidad = mensualidad;
		this.alquilado = alquilado;
		this.nifEmpleado = nifEmpleado;
	}
	public Piso() {
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public double getMensualidad() {
		return mensualidad;
	}
	public void setMensualidad(double mensualidad) {
		this.mensualidad = mensualidad;
	}
	public boolean isAlquilado() {
		return alquilado;
	}
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	public String getNifEmpleado() {
		return nifEmpleado;
	}
	public void setNifEmpleado(String nifEmpleado) {
		this.nifEmpleado = nifEmpleado;
	}
    
}
