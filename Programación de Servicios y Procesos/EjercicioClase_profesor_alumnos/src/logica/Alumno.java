package logica;

public class Alumno implements Runnable{
	private Clase clase;
    private String nombre;
    public Alumno(Clase clase, String nombre) {
        this.clase = clase;
        this.nombre = nombre;
    }
    @Override
    public void run() {
        clase.llegaAlumno(nombre);
    }
}
