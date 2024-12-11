package ejer1;

public class Clasificador implements Runnable {
	private Almacen a = new Almacen();
	// el método clasifica recibe un número positivo y devuelve
	// true-> si el número es par
	// false-> si el número es impar
	// NO SE PUEDE MODIFICAR
	public boolean clasifica(int numero) {
		long init = System.currentTimeMillis();
		double tiempoDeClasificacion = (Math.random() * 1000) + 500;
		while (System.currentTimeMillis() < init + tiempoDeClasificacion) {
		}
		return numero % 2 == 0;
	}

	@Override
	public void run() {
		while (true) {
			// pilla el número que tiene que clasificar desde el almacenPrincipal
			int actual = extraeNumeroAlmacenPrincipal();

			// lo clasifica
			if (clasifica(actual)) {
				// si es par, lo inserta en los pares
				procesaPar(actual);
			} else {
				// si es impar lo inserta en los impares
				procesaImpar(actual);
			}
		}

	}

	private void procesaImpar(int actual) {
		a.insertaImpares(actual);
	}

	private void procesaPar(int actual) {
		a.insertaPares(actual);
	}

	private int extraeNumeroAlmacenPrincipal() {
		int n = a.extraeAlmacenPrincipal();
		clasifica(n);
		return n;
	}
}
