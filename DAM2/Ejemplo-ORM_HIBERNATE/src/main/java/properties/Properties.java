package properties;

import java.io.IOException;
import java.io.InputStream;

public class Properties {
	static private java.util.Properties config = null;
	public static java.util.Properties getConfig() {
		if (config != null) {
			return config;
		}
		config = new java.util.Properties();
		try {
			InputStream inputStream = Properties.class.getClassLoader().getResourceAsStream("bd.properties");
			config.load(inputStream);
//			config.load(new FileInputStream("src/main/resources/bd.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
}
