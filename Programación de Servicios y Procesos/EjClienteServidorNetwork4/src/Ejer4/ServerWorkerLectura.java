package Ejer4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerWorkerLectura implements Runnable{
	private static int contadorConexiones = 0;
	@Override
	public void run() {
		int puerto = 12345;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                contadorConexiones++;

                new Thread(() -> manejarCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	private static void manejarCliente(Socket clienteSocket) {
		try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
             PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);) {
            salida.println("Conexión número: " + contadorConexiones);
            while (true) {
				IOUtility.leer(entrada);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
