package handler;

public class Cliente {
	private String nombre, nif;
	private int id;
	public Cliente(String nombre, String nif) {
		super();
		this.nombre = nombre;
		this.nif = nif;
	}
	public Cliente() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
