package logica;

public class Clase {
    private boolean haIniciado = false;

    public void llegaProfesor() {
        synchronized (this) {
            System.out.println("El profesor ha llegado.");
            haIniciado = true;
            System.out.println("El profesor dice: La clase ha comenzado.");
            notifyAll();
        }
    }
    public void llegaAlumno(String nombreAlumno) {
        synchronized (this) {
            System.out.println(nombreAlumno + " ha llegado.");
            while (!haIniciado) {
                try {
                    System.out.println(nombreAlumno + " está esperando a que empiece la clase.");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(nombreAlumno + " fue interrumpido mientras esperaba.");
                }
            }
            System.out.println(nombreAlumno + " dice: Buenos días, profesor.");
        }
    }
}
