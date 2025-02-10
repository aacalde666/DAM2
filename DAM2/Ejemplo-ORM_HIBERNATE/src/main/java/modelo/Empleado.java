package modelo;
// Generated 15 ene 2025, 12:30:59 by Hibernate Tools 6.5.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Empleado generated by hbm2java
 */
public class Empleado implements java.io.Serializable {

	private String dni;
	private String nomEmp;
	
	//conjunto de proyectos en los que trabaja el empleado
	private Set<Proyecto> proyectosTrabaja = new HashSet<>(0);
	
	//conjunto de proyectos que ese empleado dirige
	private Set<Proyecto> proyectosJefe = new HashSet<>(0);

	public Empleado() {
	}

	public Empleado(String dni, String nomEmp) {
		this.dni = dni;
		this.nomEmp = nomEmp;
	}

	public Empleado(String dni, String nomEmp, Set<Proyecto> proyectos, Set<Proyecto> proyectosJefe) {
		this.dni = dni;
		this.nomEmp = nomEmp;
		this.proyectosTrabaja = proyectos;
		this.proyectosJefe = proyectosJefe;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNomEmp() {
		return this.nomEmp;
	}

	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}

	public Set<Proyecto> getProyectosTrabaja() {
		return this.proyectosTrabaja;
	}

	public void setProyectosTrabaja(Set<Proyecto> proyectosTrabaja) {
		this.proyectosTrabaja = proyectosTrabaja;
	}

	public Set getProyectosJefe() {
		return this.proyectosJefe;
	}

	public void setProyectosJefe(Set<Proyecto> proyectosJefe) {
		this.proyectosJefe = proyectosJefe;
	}

}
