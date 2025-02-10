package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "departamento")
public class Departamento implements Serializable{
	//para clave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre")
	private String nomDept;
	@OneToMany(mappedBy = "departamento")
	private List<Empleado> empleados = new LinkedList<>();
	//esta propiedad no se mapea
	@Transient
	private String notas;
	
	public Departamento(int id, String nomDept, List<Empleado> empleados) {
		this.id = id;
		this.nomDept = nomDept;
		this.empleados = empleados;
	}
	public Departamento() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomDept() {
		return nomDept;
	}
	public void setNomDept(String nomDept) {
		this.nomDept = nomDept;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nomDept=" + nomDept + ", empleados=" + empleados + "]";
	}
	
}
