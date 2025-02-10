package main;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;

import modelo.Sede;

public class Main {

	public static void main(String[] args) throws ParseException {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); // Solo muestra errores graves
        Logger.getLogger("org.hibernate.SQL").setLevel(Level.SEVERE);
        Logger.getLogger("org.hibernate.type").setLevel(Level.SEVERE);
		//pruebaConfiguracion();
		//AccesoDatos.insertProyecto();
		//AccesoDatos.incorporarDatosProfesionales();
		//AccesoDatos.asociarProyectoSede(1, 3);
        Sede s = AccesoDatos.masProyectos();
	}
	
	private static void pruebaConfiguracion() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		System.out.println("hola");
		sesion.close();
	}
}
