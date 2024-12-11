package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Piso;
import conexion.Conexion;
import dao.PisoDAO;

public class PisoDAOImpl implements PisoDAO{

	@Override
	public boolean insertPiso(Piso piso) {
		
		Connection con = Conexion.getConex();
		
		String sql = "INSERT INTO pisos(direccion,mensualidad,alquilado,nifEmpleado) VALUES(?,?,?,?)";
		
		try {
			
			//SI QUEREMOS SABER LA CLAVE PRIMARIA QUE SE HA GENERADO:
			PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, piso.getDireccion());
			ps.setInt(2, piso.getMensualidad());
			ps.setInt(3, piso.isAlquilado()?1:0);
			ps.setString(4, piso.getNif());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			int claveGenerada = 0;
			while(rs.next())
				claveGenerada = rs.getInt(1);
			
			System.out.println("La clave generada es: "+claveGenerada);
			
			//SI QUEREMOS
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateMens(Piso piso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rentPiso(Piso piso) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreEmpleado(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
