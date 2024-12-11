package logica_Empleado_Piso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;

public class Func_Emp_Piso {
	public void insertarEmpleado(Empleado empleado) {
	    String query = "INSERT INTO empleados (nif, nombre, sueldo_base) VALUES (?, ?, ?)";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setString(1, empleado.getNif());
	        stmt.setString(2, empleado.getNombre());
	        stmt.setDouble(3, empleado.getSueldoBase());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void insertarPiso(Piso piso) {
	    String query = "INSERT INTO pisos (direccion, mensualidad, alquilado, nif_empleado) VALUES (?, ?, ?, ?)";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setString(1, piso.getDireccion());
	        stmt.setDouble(2, piso.getMensualidad());
	        stmt.setBoolean(3, piso.isAlquilado());
	        stmt.setString(4, piso.getNifEmpleado());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void modificarMensualidad(int codigoPiso, double nuevaMensualidad) {
	    String query = "UPDATE pisos SET mensualidad = ? WHERE codigo = ?";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setDouble(1, nuevaMensualidad);
	        stmt.setInt(2, codigoPiso);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void cambiarEmpleadoPiso(int codigoPiso, String nuevoNif) {
	    String query = "UPDATE pisos SET nif_empleado = ? WHERE codigo = ?";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setString(1, nuevoNif);
	        stmt.setInt(2, codigoPiso);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void alquilarPiso(int codigoPiso, boolean alquilar) {
	    String query = "UPDATE pisos SET alquilado = ? WHERE codigo = ?";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setBoolean(1, alquilar);
	        stmt.setInt(2, codigoPiso);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public String obtenerNombreEmpleado(int codigoPiso) {
	    String query = "SELECT e.nombre FROM empleados e INNER JOIN pisos p ON e.nif = p.nif_empleado WHERE p.codigo = ?";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, codigoPiso);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("nombre");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public double calcularSueldoEmpleado(String nif) {
	    String query = "SELECT e.sueldo_base, SUM(p.mensualidad * 0.1) AS extra FROM empleados e " +
	                   "LEFT JOIN pisos p ON e.nif = p.nif_empleado AND p.alquilado = true " +
	                   "WHERE e.nif = ? GROUP BY e.sueldo_base";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setString(1, nif);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble("sueldo_base") + rs.getDouble("extra");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0.0;
	}
	public String empleadoConMasPisos() {
	    String query = "SELECT e.nombre, COUNT(p.codigo) AS total FROM empleados e " +
	                   "INNER JOIN pisos p ON e.nif = p.nif_empleado WHERE p.alquilado = true " +
	                   "GROUP BY e.nif ORDER BY total DESC LIMIT 1";
	    try (Connection con = ConexionBD.getConex();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("nombre");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
