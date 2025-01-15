package Ejer5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteWorkerLectura implements Runnable{
	private Socket socket;
	public ClienteWorkerLectura(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try (Socket socket = this.socket;
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			System.out.println(entrada.readLine());
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
}
