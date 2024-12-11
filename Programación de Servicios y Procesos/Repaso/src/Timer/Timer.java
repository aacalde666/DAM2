package Timer;

public class Timer implements Runnable{
	private int cont,contador;
	
	public Timer(int contador) {
		this.contador = contador;
	}

	public synchronized int getContador() {
		return contador;
	}

	public synchronized void setContador(int contador) {
		this.contador = contador;
	}

	@Override
	public void run() {
		long actual = System.nanoTime();
		while (true) {
			while (System.nanoTime() - actual < 1000000000l) {}
			synchronized (this) {
				cont++;
				System.out.println(Thread.currentThread().getName()+" tick: "+System.nanoTime()+" "+ cont);
				notifyAll();
			}
			actual = System.nanoTime();
		}
	}
}
