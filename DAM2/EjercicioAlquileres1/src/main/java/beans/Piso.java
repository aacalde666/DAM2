package beans;

public class Piso {
	
	private int id;
	private String direccion, nif;
	private int mensualidad;
	private boolean alquilado;
	
	public Piso(int id, String direccion, String nif, int mensualidad, boolean alquilado) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.nif = nif;
		this.mensualidad = mensualidad;
		this.alquilado = alquilado;
	}
	
	
	
	public Piso() {
		super();
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public int getMensualidad() {
		return mensualidad;
	}
	public void setMensualidad(int mensualidad) {
		this.mensualidad = mensualidad;
	}
	public boolean isAlquilado() {
		return alquilado;
	}
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}



	@Override
	public String toString() {
		return "Piso [direccion=" + direccion + ", nif=" + nif + ", mensualidad=" + mensualidad + ", alquilado="
				+ alquilado + "]";
	}
	
	
	
	
	

}
