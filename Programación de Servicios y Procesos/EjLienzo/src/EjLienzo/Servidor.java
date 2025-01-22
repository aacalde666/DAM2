package EjLienzo;

import java.awt.Point;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final List<Point> puntosGlobales = new CopyOnWriteArrayList<>();
    private static final List<PrintWriter> clientesConectados = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket clienteSocket = servidor.accept();
                System.out.println("Nuevo cliente conectado desde: " + clienteSocket.getInetAddress());

                PrintWriter salidaCliente = new PrintWriter(clienteSocket.getOutputStream(), true);
                clientesConectados.add(salidaCliente);

                // Enviar puntos históricos al cliente
                synchronized (puntosGlobales) {
                    for (Point punto : puntosGlobales) {
                        salidaCliente.println(punto.x + "," + punto.y);
                    }
                    salidaCliente.println("END");
                }

                Thread manejadorThread = new Thread(new ManejadorCliente(clienteSocket, salidaCliente));
                manejadorThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static class ManejadorCliente implements Runnable {
        private final Socket socket;
        private final PrintWriter salida;
        private final BufferedReader entrada;

        public ManejadorCliente(Socket socket, PrintWriter salida) throws IOException {
            this.socket = socket;
            this.salida = salida;
            this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    String[] coords = mensaje.split(",");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);

                    Point nuevoPunto = new Point(x, y);

                    synchronized (puntosGlobales) {
                        puntosGlobales.add(nuevoPunto);
                    }

                    for (PrintWriter clienteSalida : clientesConectados) {
                        clienteSalida.println(mensaje);
                    }
                }
            } catch (IOException e) {
                System.err.println("Conexión con cliente perdida: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
                clientesConectados.remove(salida);
            }
        }
    }
}
