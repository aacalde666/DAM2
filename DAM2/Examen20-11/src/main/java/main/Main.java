package main;

import UtilidadesTeclado.Teclado;
import logica.FuncionesExam;

public class Main {

	public static void main(String[] args) {
		int op = -1;
		do {
			System.out.print("Elige una de las opciones\n"
					+ "1. Ver intercambio\n"
					+ "2. Eliminar usuario\n"
					+ "3. \n"
					+ "0. Salir\n"
					+ "-> ");
			op = Teclado.leerEntero();
			switch (op) {
			case 2:
				System.out.print("Indica el id del usuario al que quieres eliminar\n"
						+ "-> ");
				int idUsuario = Teclado.leerEntero();
				FuncionesExam.eliminaUsuario(idUsuario);
				break;

			default:
				break;
			}
		} while (op != 0);
	}

}
