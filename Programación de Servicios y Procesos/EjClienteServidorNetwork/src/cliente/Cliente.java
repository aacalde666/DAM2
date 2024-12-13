package cliente;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Cliente {
    private static final String CARPETA_CLIENTE = "./src/cliente/";

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            // Crear carpeta del cliente si no existe
            Files.createDirectories(Paths.get(CARPETA_CLIENTE));

            System.out.println(entrada.readLine()); // Número de conexión
            System.out.println(entrada.readLine()); // Operaciones disponibles

            System.out.print("Ingrese comando: ");
            String comando = teclado.readLine();
            salida.println(comando);

            if (comando.startsWith("BAJAR")) {
                String[] partes = comando.split(" ", 2);
                if (partes.length > 1) {
                    Path rutaFichero = Paths.get(CARPETA_CLIENTE, partes[1]);
                    try (FileOutputStream fos = new FileOutputStream(rutaFichero.toFile())) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        InputStream socketInput = socket.getInputStream();

                        while ((bytesRead = socketInput.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }

                        System.out.println("Fichero '" + partes[1] + "' descargado correctamente en la carpeta del cliente.");
                    }
                }
            } else {
                System.out.println(entrada.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}