package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import properties.Properties;
//CLASEN CON PATRON DE DISEÑO SINGLETON
public class ConexionBD {
	private static Connection conex = null;
	
	public static Connection getConex() {
		if (conex != null) {
			return conex;
		}
		String url = Properties.getConfig().getProperty("url");
		String user = Properties.getConfig().getProperty("user");
		String passwd = Properties.getConfig().getProperty("passwd");
		try {
			conex = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conex;
	}
}
