package main;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Empleado;
import modelo.Proyecto;

public class MainPruebas {
	
	public static void main(String[] args) {
		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Introduzca dni:");
//		String dni = scan.nextLine();
//		proyectosTrabajaEmpleado(dni);
		
//		insertaEmpleado();
		
//		insertaProyecto();
		
//		deleteEmpleado();
		
		asignarEmpleadoProyecto();
		
		
		
	}
	
	private static void proyectosTrabajaEmpleado(String dni) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = sesion.beginTransaction();
		
		Empleado emp = sesion.get(Empleado.class, dni);
		//a partir de aquí emp es un objeto persistente
		
		//este sería transitorio:
		Empleado emp2 = new Empleado();
		emp2.setDni(dni);
		
		for(Proyecto p : emp.getProyectosTrabaja())
			System.out.println(p.getNomProy());
		
		tx.commit();
		
		sesion.close();
	}
	
	private static void insertaEmpleado() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Dame dni:");
		String dni = scan.nextLine();
		System.out.println("Dame nombre:");
		String nombre = scan.nextLine();
		
		Empleado emp = new Empleado();
		emp.setDni(dni);
		emp.setNomEmp(nombre);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = sesion.beginTransaction();
		
		sesion.persist(emp);
		
		tx.commit();
		
		sesion.close();
		
	}
	
	private static void insertaProyecto() {
		
		Scanner scan = new Scanner(System.in);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = sesion.beginTransaction();
		
		Proyecto p = new Proyecto();
		System.out.println("Dame nombre:");
		String nombre = scan.nextLine();
		p.setNomProy(nombre);
		
		System.out.println("Dame dni del jefe:");
		String dni = scan.nextLine();
		
		//CUALQUIERA DE LAS DOS OPCIONES SIGUIENTES
		//SERÍA VÁLIDA:
		//opción A:
		//Empleado emp = sesion.get(Empleado.class, dni);
		
		//opción B:
		Empleado emp2 = new Empleado();
		emp2.setDni(dni);
		
		p.setJefe(emp2);
		
		sesion.persist(p);
		
		tx.commit();
		
		sesion.close();
		
	}
	
	private static void deleteEmpleado() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = sesion.beginTransaction();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Dni:");
		String dni = scan.nextLine();
		
		Empleado emp = sesion.get(Empleado.class, dni);
		
		sesion.remove(emp);
		
		tx.commit();
		
		sesion.close();
		
	}
	
	private static void asignarEmpleadoProyecto() {
		
		Scanner scan = new Scanner(System.in);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = sesion.beginTransaction();
		
		System.out.println("Dni:");
		String dni = scan.nextLine();
		System.out.println("Id proyecto:");
		int id = scan.nextInt();
		
		Empleado emp = sesion.get(Empleado.class, dni);
		
		//Proyecto p = sesion.get(Proyecto.class, id);
		Proyecto p = new Proyecto();
		p.setIdProy(id);
		
		emp.getProyectosTrabaja().add(p);
		
		tx.commit();
		sesion.close();
		
	}

}
