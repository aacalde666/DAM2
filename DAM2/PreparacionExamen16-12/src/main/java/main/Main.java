package main;

import UtilidadesTeclado.Teclado;
import logica.Funciones;
import logica.FuncionesExam;

public class Main {

	public static void main(String[] args) {
		int op = -1;
		do {
			System.out.print("Elige una de las opciones\n"
					+ "1. Ver intercambio\n"
					+ "2. Eliminar usuario\n"
					+ "3. Crear informe\n"
					+ "0. Salir\n"
					+ "-> ");
			op = Teclado.leerEntero();
			switch (op) {
			case 1:
				Funciones.mostrarTodosLosIdJuegos();
			    System.out.print("Indica el ID del juego para ver los intercambios\n-> ");
			    int idJuego = Teclado.leerEntero();
			    FuncionesExam.intercambiado(idJuego);
			    break;
			case 2:
				Funciones.mostrarTodosLosIdUsuarios();
				System.out.print("Indica el id del usuario al que quieres eliminar\n"
						+ "-> ");
				int idUsuario = Teclado.leerEntero();
				FuncionesExam.eliminaUsuario(idUsuario);
				break;
			case 3:
				Funciones.mostrarTodosLosIdUsuarios();
			    System.out.print("Indica el ID del usuario para generar el informe\n-> ");
			    int idUsuarioInforme = Teclado.leerEntero();
			    FuncionesExam.generaInforme(idUsuarioInforme);
			    break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opcion equivocada");
				break;
			}
		} while (op != 0);
	}

}
