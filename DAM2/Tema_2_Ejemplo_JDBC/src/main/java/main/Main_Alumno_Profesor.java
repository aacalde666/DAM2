package main;

import java.sql.SQLException;
import java.util.List;

import UtilidadesTeclado.Teclado;
import dao_Alumno_Profesor.AlumnoDAO;
import dao_Alumno_Profesor.ProfesorDAO;
import logica_Alumno_Profesor.Alumno;
import logica_Alumno_Profesor.Profesor;

public class Main_Alumno_Profesor {

	public static void main(String[] args) {
//		Alumno a = new Alumno(5,"mama",1,2.5);
		AlumnoDAO alumnoDAO = new AlumnoDAO();
//		System.out.println(alumnoDAO.insertarAlumno_v2(a));
//		System.out.println(alumnoDAO.recuperarAlumno(1));
//		for (Alumno a : alumnoDAO.recuperarAlumnoNombre_v2("anita")) {
//			System.out.println(a);
//		}
		ProfesorDAO profesorDAO = new ProfesorDAO();
//		System.out.println(profesorDAO.updateProfesor(new Profesor(1, "manolo", "base de datos")));
//		System.out.println(profesorDAO.insertarProfesor(new Profesor(3, "raquel", "programacion")));
//		System.out.println(profesorDAO.deleteProfesor(3));
//		for (Profesor p : profesorDAO.getProfesor(2)) {
//			System.out.println(p);
//		}
//		System.out.println("Introduzca id de alumno a1");
//		Alumno a1 = alumnoDAO.recuperarAlumno(Teclado.leerEntero());
//		System.out.println("Introduzca id de alumno a2");
//		Alumno a2 = alumnoDAO.recuperarAlumno(Teclado.leerEntero());
//		alumnoDAO.intercambiarNota(a1, a2);
		alumnoDAO.eliminaSuspensosSubeNota_V1(50);
		alumnoDAO.darDatos();
	}

}
