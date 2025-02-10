package ExamenNetwork2425;

import java.io.*;
import java.net.*;
import java.util.*;

class GestorCliente implements Runnable {
    private Socket socket;
    private BufferedReader entrada;
    private BufferedWriter salida;
    private ObjectOutputStream objSalida;
    private List<Tarea> tareas;

    public GestorCliente(Socket socket) {
        this.socket = socket;
        this.tareas = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            objSalida = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                String comando = entrada.readLine();
                if (comando == null) {
                    System.out.println("Cliente desconectado.");
                    break;
                }

                if (comando.equals("AGREGAR")) {
                    String nombre = entrada.readLine();
                    String descripcion = entrada.readLine();
                    agregarTarea(nombre, descripcion);
                } else if (comando.equals("COMPLETAR")) {
                    String nombre = entrada.readLine();
                    completarTarea(nombre);
                } else if (comando.equals("LISTAR")) {
                    listarTareas();
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void agregarTarea(String nombre, String descripcion) throws IOException {
        for (Tarea t : tareas) {
            if (t.nombre.equals(nombre)) {
                t.descripcion = t.descripcion + " UPDATE " + descripcion;
                salida.write("Tarea actualizada.\n");
                salida.flush();
                return;
            }
        }
        tareas.add(new Tarea(nombre, descripcion));
        salida.write("Tarea añadida.\n");
        salida.flush();
    }

    private void completarTarea(String nombre) throws IOException {
        for (Tarea t : tareas) {
            if (t.nombre.equals(nombre)) {
                t.marcarCompletada();
                salida.write("Tarea marcada como completada.\n");
                salida.flush();
                return;
            }
        }
        salida.write("No se encontró la tarea con ese nombre.\n");
        salida.flush();
    }

    private void listarTareas() throws IOException {
    	objSalida.reset();
        objSalida.writeObject(new ArrayList<>(tareas));
        objSalida.flush();
    }
}
