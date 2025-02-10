package ej;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente {
	
	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 12345;
		try (Socket socket = new Socket(host, puerto);
			 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			 BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
			Thread hiloLectura = new Thread(() -> {
                try {
                    String line;
                    while ((line = entrada.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer del servidor: " + e.getMessage());
                }
            });
            hiloLectura.start();

            String op;
            while ((op = teclado.readLine()) != null) {
                salida.write(op + "\n");
                salida.flush();
                if (op.equals("0") || op.equals("salida")) {
                    break;
                }else if (op.equals("1") || op.equals("login")) {
                    
                } else if (op.equals("2") || op.equals("register")) {
                
                }
                    
            }
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
