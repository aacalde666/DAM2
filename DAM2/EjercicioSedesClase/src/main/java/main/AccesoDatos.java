package main;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import UtilidadesTeclado.Teclado;
import modelo.Proyecto;
import modelo.ProyectoSede;
import modelo.ProyectoSedeId;
import modelo.Sede;
import modelo.Departamento;
import modelo.Empleado;
import modelo.EmpleadoDatosProf;

public class AccesoDatos {
	
	public static void insertProyecto() throws ParseException {
		
		System.out.println("Nombre proyecto:");
		String nombre = Teclado.leerCadena();
		SimpleDateFormat parserFecha = new SimpleDateFormat("dd-mm-yyyy"); 
        Date fInicio = parserFecha.parse("01-01-2026"); 
        Date fFin = parserFecha.parse("31-12-2026"); 
        
        Proyecto p = new Proyecto();
        p.setNomProy(nombre);
        p.setFInicio(fInicio);
        p.setFFin(fFin);
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        sesion.persist(p);
        
        System.out.println("En cuántas sedes se va a trabajar?");
        int num = Teclado.leerEntero();
        for(int i=0;i<num;i++) {
        	System.out.println("Id de sede:");
        	int idSede = Teclado.leerEntero();
        	ProyectoSedeId psId = new ProyectoSedeId();
        	psId.setIdProy(p.getIdProy());
        	psId.setIdSede(idSede);
        	ProyectoSede ps = new ProyectoSede();
        	ps.setId(psId);
        	ps.setFInicio(fInicio);
        	ps.setFFin(fFin);
        	sesion.persist(ps);
        	
        }
        
        tx.commit();
        sesion.close();
        
    
	}
	
	public static void incorporarDatosProfesionales() {
		
		System.out.println("Dni empleado:");
		String dni = Teclado.leerCadena();
		System.out.println("Categoría:");
		String cat = Teclado.leerCadena();
		System.out.println("Sueldo:");
		double sueldo = Teclado.leerDecimal();
		BigDecimal sueldoBruto = new BigDecimal(sueldo);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		Empleado emp = sesion.get(Empleado.class, dni);
		
		if(emp.getEmpleadoDatosProf()==null) {
			System.out.println("Se insertarán sus datos profesionales");
			EmpleadoDatosProf datosProf = new EmpleadoDatosProf(emp,cat,sueldoBruto);
//			datosProf.setCategoria(cat);
//			datosProf.setSueldoBrutoAnual(sueldoBruto);
//			datosProf.setEmpleado(emp);
			//datosProf.setDni(emp.getDni());
			sesion.persist(datosProf);
			
			
		}else {
			System.out.println("Se modificarán sus datos profesionales");
			emp.getEmpleadoDatosProf().setCategoria(cat);
			emp.getEmpleadoDatosProf().setSueldoBrutoAnual(sueldoBruto);
		}
		
		tx.commit();
		sesion.close();
		
	}
	public static void asociarProyectoSede(int idProyecto, int idSede) {
		ProyectoSedeId psId = new ProyectoSedeId();
		psId.setIdProy(idProyecto);
		psId.setIdSede(idSede);
		ProyectoSede ps = new ProyectoSede();
		ps.setId(psId);
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		
		/*Proyecto p = sesion.get(Proyecto.class, idProyecto);
		Sede s = sesion.get(Sede.class, idSede);
		ps.setProyecto(p);
		ps.setSede(s);*/
		ps.setFFin(null);
		ps.setFInicio(null);
		sesion.persist(ps);
		tx.commit();
	}
	public static void eliminarSede(int idSede) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		Sede sede = sesion.get(Sede.class, idSede);
		sesion.remove(sede);
		tx.commit();
	}
	public static Sede masProyectos() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		//List<Sede> s = sesion.createQuery("FROM Sede", Sede.class).stream().filter(Sede -> Sede.getProyectoSedes() != null).toList();
		Query<Sede> sedes = sesion.createQuery("FROM Sede", Sede.class);
		List<Sede> sedesList = sedes.getResultList();
		Sede sedeMax = null;
		for (int i = 0; i < sedesList.size(); i++) {
			if (sedesList.get(i).getProyectoSedes().size() > sedeMax.getProyectoSedes().size()) {
				sedeMax = sedesList.get(i);
			}
		}
		tx.commit();
		return sedeMax;
	}
	public static void subirSueldo(int idDepto) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sesion.beginTransaction();
		Departamento d = sesion.get(Departamento.class, idDepto);
		for (Object o : d.getEmpleados()) {
			Empleado emp = (Empleado) o;
			//emp.getEmpleadoDatosProf().setSueldoBrutoAnual(emp.getEmpleadoDatosProf().getSueldoBrutoAnual()*1.1);
		}
	}
}
