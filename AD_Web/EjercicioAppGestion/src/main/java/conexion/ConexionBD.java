package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import properties.Properti;

// CLASE CON PATRON DE DISEÑO SINGLETON
public class ConexionBD {
    private static Connection conex = null;

    public static Connection getConex(ServletContext context) {
        if (conex != null) {
            return conex;
        }
        // Obtener propiedades desde bd.properties
        String url = Properti.getConfig(context).getProperty("url");
        String user = Properti.getConfig(context).getProperty("user");
        String passwd = Properti.getConfig(context).getProperty("passwd");
        
        try {
            conex = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conex;
    }
}
