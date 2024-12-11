package logica;

public class MThread extends Thread{
	private Mensaje mensaje;
    private boolean modificar;
    public MThread(Mensaje mensaje, boolean modificar) {
        this.mensaje = mensaje;
        this.modificar = modificar;
    }
    @Override
    public void run() {
        if (modificar) {
            synchronized (mensaje) {
                System.out.println("Hilo modificador: Modificando el contenido del mensaje...");
                mensaje.setContenido("El mensaje ha sido modificado por " + Thread.currentThread().getName());
                mensaje.notify();
            }
        } else {
            synchronized (mensaje) {
                try {
                    mensaje.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hilo lector: El contenido del mensaje es: " + mensaje.getContenido());
            }
        }
    }
}
