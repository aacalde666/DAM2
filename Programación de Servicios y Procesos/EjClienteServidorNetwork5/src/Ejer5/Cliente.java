package Ejer5;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
	
	public static void main(String[] args) {
		String host = "localhost";
        int puerto = 12345;
        try(Socket socket = new Socket(host, puerto)){
        	new Thread(new ClienteWorkerEscritura(socket)).start();
        	new Thread(new ClienteWorkerLectura(socket)).start();
        }catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
