package dao_Alumno_Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import conexion.ConexionBD;
import logica_Alumno_Profesor.Alumno;

public class AlumnoDAO {
	public boolean insertarAlumno(Alumno a) {
		Connection con = ConexionBD.getConex();
		Statement sentencia = null;
		try {
			sentencia = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String SQL = "INSERT INTO alumnos VALUES("+a.getIdAlumno()+",'"+a.getNombre()+"' "
				+ ","+a.getIdProfesor()+" ,"+a.getNota()+")";
		try {
			sentencia.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertarAlumno_v2(Alumno a) {
		Connection con = ConexionBD.getConex();
		PreparedStatement sentencia;
		try {
			sentencia = con.prepareStatement("INSERT INTO alumnos VALUES(?,?,?,?)");
			sentencia.setInt(1, a.getIdAlumno());
			sentencia.setString(2, a.getNombre());
			sentencia.setInt(3, a.getIdProfesor());
			sentencia.setDouble(4, a.getNota());
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public Alumno recuperarAlumno(int idAlumno) {
		Connection con = ConexionBD.getConex();
		Alumno a = null;
		String SQL = "SELECT * FROM alumnos WHERE id = "+idAlumno;
		try {
			Statement sentecia = con.createStatement();
			ResultSet registros = sentecia.executeQuery(SQL);
			while (registros.next()) {
				a = new Alumno(registros.getInt("id"),registros.getString("nombre"),
						registros.getInt("idProfesor"),registros.getDouble("nota"));
			}
			registros.close();
			sentecia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	//CON ESTE METODO SERIA MUY FACIL HACER INYECCIONES SQL
	//List<Alumno> alumnos = alumnoDAO.recuperarAlumnoNombre("anita' or '1' = '1");
	public List<Alumno> recuperarAlumnoNombre(String nombre) {
		Connection con = ConexionBD.getConex();
		List<Alumno> alumnos = new LinkedList<Alumno>();
		String SQL = "SELECT * FROM alumnos WHERE nombre = '"+nombre+"'";
		try {
			Statement sentecia = con.createStatement();
			ResultSet registros = sentecia.executeQuery(SQL);
			while (registros.next()) {
				Alumno a = new Alumno(registros.getInt("id"),registros.getString("nombre"),
						registros.getInt("idProfesor"),registros.getDouble("nota"));
				alumnos.add(a);
			}
			registros.close();
			sentecia.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}
	//Esta version no permite inyecciones SQL
	public List<Alumno> recuperarAlumnoNombre_v2(String nombre) {
		Connection con = ConexionBD.getConex();
		List<Alumno> alumnos = new LinkedList<Alumno>();
		try {
			PreparedStatement sent = con.prepareStatement("SELECT * FROM alumnos WHERE nombre = ?");
			sent.setString(1, nombre);
			ResultSet rest = sent.executeQuery();
			while (rest.next()) {
				Alumno a = new Alumno(rest.getInt("id"),rest.getString("nombre"),
						rest.getInt("idProfesor"),rest.getDouble("nota"));
				alumnos.add(a);
			}
			rest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}
	//Transacciones
	public boolean intercambiarNota(Alumno a1, Alumno a2) {
		Connection con = ConexionBD.getConex();
		
		//Modificar nota a1
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("UPDATE alumnos set nota = ? WHERE id = ?");
			ps.setDouble(1, a2.getNota());
			ps.setInt(2, a1.getIdAlumno());
			ps.executeUpdate();
			
			ps = con.prepareStatement("UPDATE alumnos set nota = ? WHERE id = ?");
			ps.setDouble(1, a1.getNota());
			ps.setInt(2, a2.getIdAlumno());
			ps.executeUpdate();
			con.commit();
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return false;
	}
	public void darDatos() {
		Connection con = ConexionBD.getConex();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * from alumnos");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumna = rsmd.getColumnCount();
			for (int i = 1; i <= numColumna; i++) {
				System.out.print("\t"+rsmd.getColumnName(i));
			}
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= numColumna; i++) {
					System.out.print("\t"+rs.getObject(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eliminaSuspensosSubeNota_V1(double porcentaje) {
		Connection con = ConexionBD.getConex();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE from alumnos WHERE nota < 5");
			ps.executeUpdate();
			
			ps = con.prepareStatement("SELECT * from alumnos");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ps = con.prepareStatement("UPDATE alumnos set nota = ? WHERE id = ?");
				double porcent = rs.getDouble("nota");
				porcent += rs.getDouble("nota")*porcentaje/100;
				ps.setDouble(1, porcent);
				if (porcent>10) {
					ps.setDouble(1, 10);
				}
				ps.setInt(2, rs.getInt("id"));
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eliminaSuspensosSubeNota_V2(double porcentaje) {
		Connection con = ConexionBD.getConex();
	}
}
