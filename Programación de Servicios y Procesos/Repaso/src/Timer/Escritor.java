package Timer;

public class Escritor implements Runnable{
	private Timer timer;
	private int n;
	public Escritor(Timer timer) {
		this.timer = timer;
	}
	@Override
	public void run() {
		while (true) {
			synchronized (timer) {
				try {
					timer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}n++;
				timer.setContador(n);
				System.out.println(Thread.currentThread().getName() + " acaba de escribir: "+timer.getContador());
			}
		}
	}
}
