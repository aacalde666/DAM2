package beans;

import java.io.Serializable;

public class Datos implements Serializable{
	private String telefono, direccion;

	public Datos(String telefono, String direccion) {
		super();
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Datos() {
		super();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "telefono= " + telefono + ", direccion= " + direccion;
	}
	
}
