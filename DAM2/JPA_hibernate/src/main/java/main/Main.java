package main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Departamento;
import modelo.Empleado;
import modelo.HibernateUtil;

public class Main {

	public static void main(String[] args) {
//		addDepartamento();
//		addEmpleado();
//		listaEmpleados(2);
	}
	private static void addDepartamento() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		Departamento dept = new Departamento();
		dept.setNomDept("informatica");
		dept.setNotas("6");
		sesion.persist(dept);
		tx.commit();
		sesion.close();
	}
	private static void addEmpleado() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		Empleado emp = new Empleado();
		emp.setNombre("Juanito");
		Departamento d = sesion.get(Departamento.class, 2);
		emp.setDepartamento(d);
		sesion.persist(emp);
		tx.commit();
		sesion.close();
	}
	private static void listaEmpleados(int id) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		Departamento d = sesion.get(Departamento.class, id);
		for (Empleado e : d.getEmpleados()) {
			System.out.println(e.toString());
		}
	}
}
