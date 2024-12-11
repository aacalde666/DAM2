package logica;

public class Profesor implements Runnable{
	private final Clase clase;

    public Profesor(Clase clase) {
        this.clase = clase;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clase.llegaProfesor();
    }
}
