package ExamenNetwork2425;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                new Thread(new GestorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
