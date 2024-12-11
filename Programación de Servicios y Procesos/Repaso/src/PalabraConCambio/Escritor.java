package PalabraConCambio;

public class Escritor implements Runnable{
	PalabraPorSegundo p;
	
	public Escritor(PalabraPorSegundo p) {
		this.p = p;
	}

	@Override
	public void run() {
		long actual = System.nanoTime();
		while (true) {
			while (System.nanoTime() - actual < 1000000000l) {}
			System.out.println(Thread.currentThread().getName()+" "+ p.getPalabra());
			actual = System.nanoTime();
		}
	}

}
