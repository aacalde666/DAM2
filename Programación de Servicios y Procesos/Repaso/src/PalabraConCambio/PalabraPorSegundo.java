package PalabraConCambio;

import UtilidadesTeclado.Teclado;

public class PalabraPorSegundo implements Runnable{
	String palabra;
	
	public PalabraPorSegundo(String palabra) {
		this.palabra = palabra;
	}
	public void Palabra() {
		this.palabra = Teclado.leerCadena();
	}
	public String getPalabra() {
		return palabra;
	}
	@Override
	public void run() {
		while (true) {
			Palabra();
		}
	}
}
