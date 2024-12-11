package logica;

import java.util.LinkedList;
import java.util.List;

import UtilidadesTeclado.Teclado;
import entidadesMatricula.Asignaturas;
import entidadesMatricula.Matricula;
import entidadesMatricula.Matriculas;
import funcionalidades.Funcionalidades;
import jakarta.xml.bind.JAXBException;

public class Main {
	public static void main(String[] args) throws JAXBException {
		int op = -1;
		do {
			System.out.println("Elige una de estas opciones");
			System.out.print("1. Insertar Matricula \n"
					+ "2. Mostrar alumnos que tienen la asignatura \n"
					+ "3. Mostrar la matricula mas cara \n"
					+ "0. Salir\n"
					+ "->");
			op = Teclado.leerEntero();
			switch (op) {
			case 1:
				System.out.println("Inserta el nombre del alumno");
				String nomAlumno = Teclado.leerCadena();
				System.out.println("Inserta las asignaturas y su precio que va a llevar el alumno "+nomAlumno);
				Matricula matricula = new Matricula();
				matricula.setNombre(nomAlumno);
				insertarMatricula(matricula);
				break;
			case 2:
				System.out.print("Asignatura para ver los alumnos matriculados en ella\n->");
				String nomAsignatura = Teclado.leerCadena();
				mostrarAlumnosDeDichaAsignatura(nomAsignatura);
				break;
			case 3:
				mostrarMatriculaMasCara();
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				break;
			}
		} while (op != 0);
		
	}
	
	private static void insertarMatricula(Matricula matricula) throws JAXBException {
		System.out.println("Cuantas asignaturas quiere insertar?");
		int cant = Teclado.leerEntero();
		Matriculas matriculas = null;
		for (int i = 1; i <= cant; i++) {
			Asignaturas asignatura = new Asignaturas();
			System.out.println(i+". Nombre de la asignatura");
			String nomAsignatura = Teclado.leerCadena();
			asignatura.setNombre(nomAsignatura);
			System.out.println(i+". Precio de la asignatura");
			double predAsignatura = Teclado.leerDecimal();
			asignatura.setPrecio(predAsignatura);
			matricula.getAsignaturas().add(asignatura);
			if (!Funcionalidades.getFichero().exists()) {
				matriculas  = new Matriculas();
			}else {
				matriculas = Funcionalidades.leerFicheroXMLJAXB(Funcionalidades.getFichero());
			}
			matriculas.getMatriculas().add(matricula);
		}
		Funcionalidades.escribirFicheroXML(matriculas);
	}
	private static void mostrarAlumnosDeDichaAsignatura(String nomAsignatura) throws JAXBException {
		Matriculas matriculas = Funcionalidades.leerFicheroXMLJAXB(Funcionalidades.getFichero());
		
		System.out.println("Alumnos con asignatura "+nomAsignatura+":");
		List<String> alumnos = new LinkedList<>();
		for (Matricula matricula : matriculas.getMatriculas()) {
			for (Asignaturas asignaturas : matricula.getAsignaturas()) {
				if (asignaturas.getNombre().equals(nomAsignatura)) {
					alumnos.add(matricula.getNombre());
				}
			}
		}
		System.out.println(alumnos);
	}
	private static void mostrarMatriculaMasCara() throws JAXBException {
		Matriculas matriculas = Funcionalidades.leerFicheroXMLJAXB(Funcionalidades.getFichero());
//		List<String> preciAsignatura = new LinkedList<>();
		double m=0;
		double p;
		Matricula mat = null;
		for (Matricula matricula : matriculas.getMatriculas()) {
			p=0;
			for (Asignaturas asignaturas : matricula.getAsignaturas()) {
					p+= asignaturas.getPrecio();
			}
			if (m<p) {
				m = p;
				mat = matricula;
			}
		}
//		for (Matricula matricula : matriculas.getMatriculas()) {
//			double cant=0;
//			for (int i = 0; i < matricula.getAsignaturas().size(); i++) {
//				cant += matricula.getAsignaturas().get(i).getPrecio();
//				if (cant==m) {
//						preciAsignatura.add(matricula.getNombre()+":"
//							+ "\n	"+matricula.getAsignaturas()+"\n");
//				}
//			}
//		}
		System.out.println(mat.toString());
	}
}
