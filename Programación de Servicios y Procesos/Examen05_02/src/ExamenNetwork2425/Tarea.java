package ExamenNetwork2425;

import java.io.Serializable;

public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    String nombre;
    String descripcion;
    boolean completada;

    public Tarea(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.completada = false;
    }

    public void marcarCompletada() {
        this.completada = true;
    }

    public void marcarPendiente() {
        this.completada = false;
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion + " - " + (completada ? "Completada" : "Pendiente");
    }
}
