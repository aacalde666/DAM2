package entidadesMatricula;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
@XmlType(propOrder = {"nombre","asignaturas"})
public class Matricula {
	private String nombre;
	private List<Asignaturas> asignaturas = new LinkedList<>();
	public Matricula(String nombre, List<Asignaturas> asignaturas) {
		super();
		this.nombre = nombre;
		this.asignaturas = asignaturas;
	}
	public Matricula() {
		super();
	}
	@XmlElement(name = "alumno")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElementWrapper(name = "asignaturas")
	@XmlElement(name = "asignatura")
	public List<Asignaturas> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(List<Asignaturas> asignaturas) {
		this.asignaturas = asignaturas;
	}
	@Override
	public String toString() {
		return "Matricula [nombre=" + nombre + ", asignaturas=" + asignaturas + "]";
	}
}
