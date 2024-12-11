package dao_Alumno_Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexion.ConexionBD;
import logica_Alumno_Profesor.Profesor;

public class ProfesorDAO {
	public boolean insertarProfesor(Profesor p) {
		Connection con = ConexionBD.getConex();
		PreparedStatement sentencia;
		try {
			sentencia = con.prepareStatement("INSERT INTO profesores VALUES(?,?,?)");
			sentencia.setInt(1, p.getIdProfesor());
			sentencia.setString(2, p.getNombre());
			sentencia.setString(3, p.getModulo());
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean deleteProfesor(int id) {
		Connection con = ConexionBD.getConex();
		PreparedStatement sentencia;
		try {
			sentencia = con.prepareStatement("DELETE FROM profesores WHERE id = ?");
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<Profesor> getProfesor(int id) {
		Connection con = ConexionBD.getConex();
		List<Profesor> profesores = new LinkedList<Profesor>();
		try {
			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM profesores WHERE id = ?");
			sentencia.setInt(1, id);
			ResultSet rest = sentencia.executeQuery();
			while (rest.next()) {
				Profesor p = new Profesor(rest.getInt("id"),rest.getString("nombre"),rest.getString("modulo"));
				profesores.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profesores;
	}
	public boolean updateProfesor(Profesor p) {
		Connection con = ConexionBD.getConex();
		PreparedStatement sentencia;
		try {
			sentencia = con.prepareStatement("UPDATE profesores set nombre = ?, modulo = ? where id = ?");
			sentencia.setInt(3, p.getIdProfesor());
			sentencia.setString(1, p.getNombre());
			sentencia.setString(2, p.getModulo());
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
