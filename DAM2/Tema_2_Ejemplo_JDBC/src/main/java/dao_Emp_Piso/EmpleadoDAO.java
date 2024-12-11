package dao_Emp_Piso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import logica_Empleado_Piso.Empleado;

public class EmpleadoDAO {
	public boolean insertarEmpleado(Empleado empleado) {
		Connection con = ConexionBD.getConex();
		String sql = "INSERT INTO empleados VALUES(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, empleado.getNif());
			ps.setString(2, empleado.getNombre());
			ps.setDouble(3, empleado.getSueldoBase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public double getSueldo(String nif){
		Connection con = ConexionBD.getConex();
		String sql1 = "SELECT sueldo FROM empleados WHERE nif = ?";
		String sql2 = "SELECT SUM(mensualidad)*0.1 AS total FROM pisos WHERE alquilado != 0 and nifEmpleado = ?";
		try {
			double sueldoBase = -1, incremento = 0;
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sueldoBase = rs.getDouble("sueldo");
			}
			ps = con.prepareStatement(sql2);
			ps.setString(1, nif);
			rs = ps.executeQuery();
			while (rs.next()) {
				incremento = rs.getDouble("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
	}
	public Empleado getMejorEmpleado() {
		Connection con = ConexionBD.getConex();
		String sql = "SELECT nombre, sueldo, nifEmpleado, COUNT";
		return null;
	}
	private int pisosAlquilados(String nif) {
		Connection con = ConexionBD.getConex();
		String sql = "SELECT * FROM pisos WHERE nifEmpleado=?";
		int cont = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cont++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cont;
	}
	public Empleado getEmpleado(int nif) {
		String nombre;
		
		return null;
	}
}
