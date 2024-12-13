package servidor;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Servidor {
    private static int contadorConexiones = 0;
    private static final String CARPETA_SERVIDOR = "./src/servidor/";

    public static void main(String[] args) {
        int puerto = 12345;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            // Crear carpeta del servidor si no existe
            Files.createDirectories(Paths.get(CARPETA_SERVIDOR));

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
             PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true)) {

            salida.println("Conexión número: " + contadorConexiones);
            salida.print("Operaciones disponibles: \n1. CREAR <nombre_fichero>\n2. BAJAR <nombre_fichero>");

            String comando = entrada.readLine();
            if (comando != null) {
                String[] partes = comando.split(" ", 2);
                String accion = partes[0].toUpperCase();

                switch (accion) {
                    case "CREAR":
                        if (partes.length > 1) {
                            crearFichero(partes[1], salida);
                        } else {
                            salida.println("Error: Falta el nombre del fichero.");
                        }
                        break;

                    case "BAJAR":
                        if (partes.length > 1) {
                            enviarFichero(partes[1], clienteSocket.getOutputStream(), salida);
                        } else {
                            salida.println("Error: Falta el nombre del fichero.");
                        }
                        break;

                    default:
                        salida.println("Comando no reconocido.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void crearFichero(String nombreFichero, PrintWriter salida) {
        try {
            String contenido = "Fichero creado el: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            Path rutaFichero = Paths.get(CARPETA_SERVIDOR, nombreFichero);
            Files.write(rutaFichero, contenido.getBytes());
            salida.println("Fichero '" + nombreFichero + "' creado correctamente en la carpeta del servidor.");
        } catch (IOException e) {
            salida.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    private static void enviarFichero(String nombreFichero, OutputStream outputStream, PrintWriter salida) {
        try {
            Path rutaFichero = Paths.get(CARPETA_SERVIDOR, nombreFichero);
            if (Files.exists(rutaFichero)) {
                Files.copy(rutaFichero, outputStream);
                salida.println("Fichero enviado correctamente.");
            } else {
                salida.println("Error: El fichero no existe.");
            }
        } catch (IOException e) {
            salida.println("Error al enviar el fichero: " + e.getMessage());
        }
    }
}
