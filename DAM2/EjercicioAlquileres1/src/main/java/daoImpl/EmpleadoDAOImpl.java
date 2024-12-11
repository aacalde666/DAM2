package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Empleado;
import conexion.Conexion;
import dao.EmpleadoDAO;

public class EmpleadoDAOImpl implements EmpleadoDAO{

	@Override
	public boolean insertEmpleado(Empleado emp) {
		
		Connection con = Conexion.getConex();
		String sql = "INSERT INTO empleados VALUES(?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, emp.getNif());
			ps.setString(2, emp.getNombre());
			ps.setDouble(3, emp.getSueldo());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public double getSueldo(String nif) {
		
		Connection con = Conexion.getConex();
		
		String sql1 = "SELECT sueldo FROM empleados WHERE nif = ?";
		
		String sql2 = "SELECT SUM(mensualidad)*0.1 "+
					"AS total FROM pisos "+
				"WHERE alquilado != 0 and nifEmpleado=?";
		
		PreparedStatement ps;
		double sueldoBase = -1, incremento = 0;
		try {
			ps = con.prepareStatement(sql1);
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				sueldoBase = rs.getDouble("sueldo");
			ps = con.prepareStatement(sql2);
			ps.setString(1, nif);
			rs = ps.executeQuery();
			while(rs.next())
				incremento = rs.getDouble("total");			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return -1;
		}
		
		return sueldoBase + incremento;
	}

	@Override
	public Empleado getMejorEmpleado() {
		
		Connection con = Conexion.getConex();
		String nombre = "";
		String dni = "";
		double sueldo = 0;
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT nombre, sueldo, nifEmpleado, COUNT(nifEmpleado) "
					+ "AS cuenta FROM pisos INNER JOIN empleados "
					+ "WHERE empleados.nif = pisos.nifEmpleado "
					+ "AND alquilado != 0 "
					+ "GROUP BY nifEmpleado "
					+ "ORDER BY cuenta DESC "
					+ "LIMIT 1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dni = rs.getString("nifEmpleado");
				nombre = rs.getString("nombre");
				sueldo = rs.getDouble("sueldo");
			}

			

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return new Empleado(dni, nombre, sueldo);
	}
	
	public Empleado getMejorEmpleado_v2() {
		
		Empleado emp = null;
		
		Connection con = Conexion.getConex();
		
		String sql = "SELECT nif FROM empleados";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet nifs = ps.executeQuery();
			int max = 0;
			String nifMax = null;
			while(nifs.next()) {
				
				if(pisosAlquilados(nifs.getString("nif"))>max) {
					nifMax = nifs.getString("nif");
					max = pisosAlquilados(nifs.getString("nif"));
				}
			}
			
			emp = getEmpleado(nifMax);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return emp;
	}

	private int pisosAlquilados(String nif) {
		
		Connection con = Conexion.getConex();
		int cont = 0;
		
		String sql = "SELECT * FROM pisos WHERE alquilado != 0 and nifEmpleado=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				cont++;
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cont;
	}

	@Override
	public Empleado getEmpleado(String nif) {
		
		String nombre = "";
		double sueldo = 0;
		Connection con = Conexion.getConex();
		String sql = "SELECT * FROM empleados WHERE nif = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				nombre = rs.getString("nombre");
				sueldo = rs.getDouble("sueldo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Empleado(nif,nombre,sueldo);
	}
	
	

}
