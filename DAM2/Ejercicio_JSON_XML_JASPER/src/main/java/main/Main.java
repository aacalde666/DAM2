package main;

import UtilidadesTeclado.Teclado;
import logica.Funciones;
import net.sf.jasperreports.engine.JRException;

public class Main {

	public static void main(String[] args) {
		int op = -1;
		do {
			System.out.println("Elige una de estas opciones");
			System.out.println("1. Mostrar los libros escritos por un autor");
			System.out.print("0. Salir\n->");
			op = Teclado.leerEntero();
			switch (op) {
			case 1:
				System.out.println("Estos son todos los autores que hay");
				Funciones.verTodosLosAutores();
				System.out.println("Que auto quieres ver");
				String autor = Teclado.leerCadena();
				Funciones.mostrarLibrosPorUnAutor(autor);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Quieres generar pdf del autor elegido?\n->(s para generar)");
				String s = Teclado.leerCadena();
				if (s.equals("s")) {
					try {
						Funciones.generarInformes(Funciones.generarPDF(autor));
					} catch (JRException e) {
						e.printStackTrace();
					};
				}
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				break;
			}
		} while (op!=0);
	}
}
