package main;

import logica.Alumno;
import logica.Clase;
import logica.Profesor;

public class Main {

	public static void main(String[] args) {
		Clase clase = new Clase();
	        for (int i = 1; i <= 5; i++) {
	            new Thread(new Alumno(clase, "Alumno " + i)).start();
	        }
	        new Thread(new Profesor(clase)).start();
	}

}
