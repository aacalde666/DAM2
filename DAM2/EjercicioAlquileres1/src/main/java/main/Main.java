package main;

import java.sql.Connection;
import java.sql.DriverManager;

import beans.Empleado;
import beans.Piso;
import conexion.Conexion;
import dao.EmpleadoDAO;
import dao.PisoDAO;
import daoImpl.EmpleadoDAOImpl;
import daoImpl.PisoDAOImpl;

public class Main {

	public static void main(String[] args) {
		
		//pruebaInsertaEmpleado();
		//pruebaRecuperaSueldo();
		//pruebaMejorEmpleado();
		pruebaInsertaPiso();
		
	}
	
	private static void pruebaInsertaPiso() {
		
		Piso piso = new Piso();
		piso.setAlquilado(true);
		piso.setDireccion("granada");
		piso.setMensualidad(3000);
		piso.setNif("1234");
		
		PisoDAO dao = new PisoDAOImpl();
		System.out.println(dao.insertPiso(piso));
		
		
	}

	static void pruebaInsertaEmpleado() {
		
		Empleado emp = new Empleado();
		emp.setNif("1234");
		emp.setNombre("Anita");
		emp.setSueldo(1500.45);
		
		EmpleadoDAO dao = new EmpleadoDAOImpl();
		if(dao.insertEmpleado(emp))
			System.out.println("Insertado con éxito");
		else
			System.out.println("Error en la inserción");
	}
	
	static void pruebaRecuperaSueldo() {
		
		EmpleadoDAO dao = new EmpleadoDAOImpl();
		System.out.println(dao.getSueldo("1234"));
	}
	
	static void pruebaMejorEmpleado() {
		
		EmpleadoDAO dao = new EmpleadoDAOImpl();
		System.out.println(dao.getMejorEmpleado_v2());
	}
	
	

}
