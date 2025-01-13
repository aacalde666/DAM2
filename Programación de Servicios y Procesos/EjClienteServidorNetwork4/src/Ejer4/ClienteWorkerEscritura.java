package Ejer4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteWorkerEscritura implements Runnable{
	
	@Override
	public void run() {
		String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println(entrada.readLine());
			while (true) {
				String palabra = teclado.readLine();
				salida.println(palabra);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
