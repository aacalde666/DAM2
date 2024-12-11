package logica;

import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

public class Main {

	public static void main(String[] args) {
		List<DatosAlumno> alumnos = cargarAlumnos();
		try {
			Informes.generarInformes(alumnos);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private static List<DatosAlumno> cargarAlumnos() {
		List<DatosAlumno> alumnos = new LinkedList<>();
		for (int i = 0; i < 40; i++) {
			alumnos.add(new DatosAlumno("Pepito perez", (int) (Math.random()*10)));
			alumnos.add(new DatosAlumno("Juanito lopez", (int) (Math.random()*10)));
			alumnos.add(new DatosAlumno("Sarita Marquez", (int) (Math.random()*10)));
		}
		
		return alumnos;
	}

}
