package properties;

import java.io.InputStream;
import java.util.Properties;

import jakarta.servlet.ServletContext;

public class Properti {
    private static Properties config = null;

    public static Properties getConfig(ServletContext context) {
        if (config != null) {
            return config;
        }

        config = new Properties();
        try {
            // Obtener el archivo desde WEB-INF
            InputStream inputStream = context.getResourceAsStream("/WEB-INF/bd.properties");
            if (inputStream == null) {
                System.out.println("Error: No se pudo cargar el archivo bd.properties. InputStream es null.");
                return null;
            }

            config.load(inputStream);
            System.out.println("Archivo bd.properties cargado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return config;
    }
}

