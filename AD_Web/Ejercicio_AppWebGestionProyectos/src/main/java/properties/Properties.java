package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletContext;

//CLASE CON PATRÓN SINGLETON
public class Properties {
	
	private static java.util.Properties config = null;
	
	public static java.util.Properties getConfig(ServletContext context) {
		
		if(config != null)
			return config;
		
		config = new java.util.Properties();
		
		try {
            // Cargar el archivo .properties desde la ruta relativa
			// cuidado, WEB-INF a secas no está en el path,
			//está WEB-INF/classes, ojo porque la estructura
			// de una aplicación en desarrollo no es la
			//estructura de una aplicación desplegada
			//usamos el SevletContext porque, entre otras cosas, nos da el path
			// de la aplicación web. Además, en este caso, WEB-INF es una
			// carpeta protegida por seguridad, luego no está ofertada
			// en el class si no es a través de ServletContext
			//
            InputStream is = context.getResourceAsStream("/WEB-INF/config.properties");
            config.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
}
