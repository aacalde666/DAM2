package main;

import java.util.InputMismatchException;

import Funciones.Func;
import UtilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {
		int op = -1;
		do {
			System.out.println("Elige una de estas opciones");
			if (!Func.getFichero().exists()) {
				System.out.println("1. Insertar Envio\n"
						+ "0. Salir");
			}else {
				System.out.println("1. Insertar Envio\n"
						+ "2. Añadir Item a envio\n"
						+ "3. Modificar precio item\n"
						+ "0. Salir");
			}
			boolean error = false;
			while (!error) {
				try {
					System.out.print("-> ");
					op = Teclado.leerEntero();
					error = true;
				} catch (NumberFormatException | InputMismatchException e) {
					error = false;
					System.err.println("Solo con numeros");
				}
			}
			switch (op) {
			case 1:
				System.out.print("--Nuevo envio--\n"
						+ "Insertar nombre empleado\n-> ");
				String orderPerson = Teclado.leerCadena();
				System.out.print("ID del envio\n-> ");
				String orderid = Teclado.leerCadena();
				Func.insertarEnvio(orderPerson, orderid);
				break;
			case 2:
				if (!Func.getFichero().exists()) {
					System.err.println("No existe ningun fichero, haga uno nuevo en la opcion 1");
				}else {
					System.out.println("Introduce el ID del envio");
					Func.mostrarIdsDeEnvios();
					System.out.print("-> ");
					orderid = Teclado.leerCadena();
					if (Func.revisarQueExistaLaId(orderid)) {
						Func.mostrarInformacionDelEnvio(orderid);
						Func.insertarItem(orderid);
					}else {
						System.err.println("no existe el id de envio: "+orderid
								+ "\nSi quiere guardar ese id hagalo en la primera opcion");
					}
					
				}
				break;
			case 3:
				if (!Func.getFichero().exists()) {
					System.err.println("No existe ningun fichero, haga uno nuevo en la opcion 1");
				}else {
					System.out.println("Introduce el numero de empleado");
					Func.mostrarIdsDeEnvios();
					System.out.print("-> ");
					orderid = Teclado.leerCadena();
					if (Func.revisarQueExistaLaId(orderid)) {
						Func.mostrarInformacionDelEnvio(orderid);
						Func.modificarPrecioItem(orderid);
					}else {
						System.err.println("no existe el id de envio: "+orderid
								+ "\nSi quiere guardar ese id hagalo en la primera opcion");
					}
				}
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				break;
			}
		} while (op != 0);
	}

}
