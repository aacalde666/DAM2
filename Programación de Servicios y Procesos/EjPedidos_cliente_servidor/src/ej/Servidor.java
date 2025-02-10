package ej;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static int puerto = 12345;
	public static void main(String[] args) {
		try (ServerSocket servidor = new ServerSocket(puerto)){
			System.out.println("Servidor iniciado en el puerto "+puerto);
			while (true) {
				Socket clienteSocket = servidor.accept();
				System.out.println("Nuevo cliente");
				new Thread(() -> ManejadorCliente(clienteSocket)).start();
			}
		} catch (IOException e) {
			System.out.println("Error en el servidor: " + e.getMessage());
		}
	}
	private static void ManejadorCliente(Socket clienteSocket) {
		try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
			 BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(clienteSocket.getOutputStream()))){
			salida.write("Bienvenido a la app de pedidos. Por favor, seleccione una opción:");
			salida.newLine();
			salida.write("1. login\n2. register\n0. salida\n");
			salida.flush();
			String op;
            while ((op = entrada.readLine()) != null) {
                if (op.equals("1") || op.equals("login")) {
                    salida.write("Ingrese su nombre de usuario:\n");
                    salida.flush();
                    String username = entrada.readLine();
                    salida.write("Bienvenido user " + username + "\n");
                    salida.flush();
                } else if (op.equals("2") || op.equals("register")) {
                    salida.write("Ingrese su nombre de usuario:\n");
                    salida.flush();
                    String username = entrada.readLine();
                    salida.write("Registrado exitosamente\nBienvenido user " + username + "\n");
                    salida.flush();
                } else if (op.equals("0") || op.equals("salida")) {
                    salida.write("Saliendo...\n");
                    salida.flush();
                    break;
                } else {
                    salida.write("Opción equivocada\n");
                    salida.flush();
                }
            }
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
