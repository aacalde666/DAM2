package logica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.utilidades;

public class Main {
	//PATRON SINGLETRON: SE HACE PRIVADO EL CONTRUCTOR Y SE GENERA
	//EL OBJETO CON UN MÉTODO, QUE SOLO LO INSTANCIA SI NO HAY YA UNO CREADO
	public static Logger logger = LogManager.getLogger(Main.class);
	public static void main(String[] args) {
		logger.debug("Mensaje de depuracion de Main");
		//EXISTEN VARIOS NIVELES DE MENSAJES:
		//DEBUG, INFO, WARN, ERROR, FATAL
		logger.info("Entrando en la aplicación");
		logger.error("Ha ocurrido un error en Main");
		utilidades.saludar();
	}

}
