package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CLASE CON PATRÓN DE DIDEÑO SINGLETON
public class Conexion {
	
	private static Connection conex = null;
	
	public static Connection getConex() {
		
		if(conex != null)
			return conex;
		
		String url = Properties.Properties.getConfig().getProperty("url");
		String user = Properties.Properties.getConfig().getProperty("user");;
		String passwd = Properties.Properties.getConfig().getProperty("passwd");;
		
		try {
			conex = DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conex;
		
	}

}
