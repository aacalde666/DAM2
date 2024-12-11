package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class Alumno implements Serializable {

	private int numMatricula;
	private String nombre;
	private int edad;
	private LinkedList<String> asignaturas = new LinkedList<String>();

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(LinkedList<String> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Alumno() {
	}

	public Alumno(int numMatricula, String nombre, int edad) {
		this.numMatricula = numMatricula;
		this.nombre = nombre;
		this.edad = edad;
	}

//	@Override
//	public String toString() {
//
//		String datos = "";
//		datos += "El alumno " + nombre + " con n�mero de matr�cula " + numMatricula + " tiene " + edad + " a�os \n";
//
//		if (asignaturas == null) {
//			datos += "y no est� matriculado en ninguna asignatura. \n \n";
//			return datos;
//		} else {
//			datos += "y est� matriculado en las siguientes asignaturas: \n";
//			for (String asig : asignaturas) {
//				datos += " | " + asig.substring(0, 1).toUpperCase() + asig.substring(1, asig.length());
//			}
//			datos += ". \n \n";
//		}
//
//		return datos;
//	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return numMatricula == other.numMatricula;
	}

	@Override
	public String toString() {
		return "Alumno [numMatricula=" + numMatricula + ", nombre=" + nombre + ", edad=" + edad + ", asignaturas="
				+ asignaturas + "]";
	}

}
