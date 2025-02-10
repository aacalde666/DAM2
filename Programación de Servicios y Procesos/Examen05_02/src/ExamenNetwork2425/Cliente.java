package ExamenNetwork2425;

import java.io.*;
import java.net.*;
import java.util.*;

class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;
    
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             ObjectInputStream objEntrada = new ObjectInputStream(socket.getInputStream())) {
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("1. Añadir tarea\n2. Completar tarea\n3. Listar tareas\n4. Salir");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                if (opcion == 1) {
                    System.out.print("Nombre de la tarea: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Descripción de la tarea: ");
                    String descripcion = scanner.nextLine();
                    
                    salida.write("AGREGAR\n");
                    salida.write(nombre + "\n");
                    salida.write(descripcion + "\n");
                    salida.flush();
                    System.out.println(entrada.readLine());
                } else if (opcion == 2) {
                    System.out.print("Nombre de la tarea a completar: ");
                    String nombre = scanner.nextLine();
                    
                    salida.write("COMPLETAR\n");
                    salida.write(nombre + "\n");
                    salida.flush();
                    System.out.println(entrada.readLine());
                } else if (opcion == 3) {
                    salida.write("LISTAR\n");
                    salida.flush();
                    @SuppressWarnings("unchecked")
					List<Tarea> tareas = (List<Tarea>) objEntrada.readObject();
                    tareas.forEach(System.out::println);
                } else if (opcion == 4) {
                    break;
                }
            }
            scanner.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}