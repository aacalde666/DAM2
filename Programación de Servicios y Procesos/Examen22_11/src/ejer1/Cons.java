package ejer1;

public class Cons implements Runnable {
	private boolean consumePares;
	private Almacen a = new Almacen();
	// el método consumir consume un número
	// NO SE PUEDE MODIFICAR
	public void consume(int numero) {
		long init = System.currentTimeMillis();
		double tiempoDeConsumición = (Math.random() * 750) + 250;
		while (System.currentTimeMillis() < init + tiempoDeConsumición) {
		}
		System.out.println(Thread.currentThread().getName() + " ha consumido " + numero);
	}

	public Cons(boolean consumePares) {
		this.consumePares = consumePares;
	}

	@Override
	public void run() {
		while (true) {
			// si soy un consumadores de pares
			if (consumePares) {
				// entonces extraigo desde los pares y consumo
				consume(extraePares());
			} else {
				// si no extraigo desde los impares y consumo
				consume(extraeImpares());
			}
		}

	}

	private int extraeImpares() {
		return a.extraeImpares();
	}

	private int extraePares() {
		return a.extraePares();
	}
}
