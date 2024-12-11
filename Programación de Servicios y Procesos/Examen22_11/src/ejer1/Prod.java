package ejer1;

public class Prod implements Runnable {
	static int nextPar = 0;
	static int nextImpar = -1;
	private Almacen a = new Almacen();
	// el método produce crea y devuelve un número
	// NO SE PUEDE MODIFICAR
	public int produce() {
		nextPar += 2;
		nextImpar += 2;
		long init = System.currentTimeMillis();
		double tiempoDeProduccion = (Math.random() * 750) + 250;
		while (System.currentTimeMillis() < init + tiempoDeProduccion) {
		}
		double d = Math.random();
		return (d < 0.5) ? nextPar : nextImpar;
	}

	@Override
	public void run() {
		while (true) {
			int n = produce();
			procesaProducto(n);
		}
	}

	private void procesaProducto(int n) {
		a.insertaAlmacenPrincipal(n);
	}
}
