package main;

import logica.MThread;
import logica.Mensaje;

public class Main {

	public static void main(String[] args) {
		Mensaje mensaje = new Mensaje();
        mensaje.setContenido("Mensaje original");
        MThread threadModificador = new MThread(mensaje, true);
        MThread threadLector = new MThread(mensaje, false); 
        threadLector.start();
        threadModificador.start();
    }
	
}
