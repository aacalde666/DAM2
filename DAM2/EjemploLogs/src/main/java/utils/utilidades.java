package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class utilidades {
	public static Logger logger = LogManager.getLogger(utilidades.class);
	public static void saludar() {
		logger.info("Entrando en metodo saludar");
		System.out.println("Hello there");
		logger.error("Error");
	}
}
