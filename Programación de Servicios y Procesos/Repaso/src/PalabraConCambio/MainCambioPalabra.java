package PalabraConCambio;

public class MainCambioPalabra {

	public static void main(String[] args) {
		PalabraPorSegundo p = new PalabraPorSegundo("a");
		new Thread(p).start();
		new Thread(new Escritor(p), "escritor ").start();
	}

}
