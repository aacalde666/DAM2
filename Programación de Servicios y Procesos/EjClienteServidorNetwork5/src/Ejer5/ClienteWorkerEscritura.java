package Ejer5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteWorkerEscritura implements Runnable{
	private Socket socket;
	public ClienteWorkerEscritura(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
        try (Socket socket = this.socket;
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				System.out.print("->");
				String palabra = teclado.readLine();
				salida.println(palabra);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
