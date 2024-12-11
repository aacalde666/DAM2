package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import logica.funcionalidades;

public class Main {
	public static void main(String[] args) {
		String errorNum = "Solo con numeros!";
		Scanner scan = new Scanner(System.in);
		System.out.println("Bienvenido al sistema de pedidos");
		int op = -1;
		do {
			System.out.println("Elige una opcion:");
			System.out.println("1. Insertar pedido del cliente");
			System.out.println("2. Eliminar pedido del cliente");
			System.out.println("3. Mostrar pedidos del cliente");
			System.out.println("4. Mostrar gasto de pedidos del cliente");
			System.out.println("5. Lista de todos los nifs");
			System.out.print("0. Salida \r\n-> ");
			try {
				op = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.err.println(errorNum);
			}
			/**
			 * cliente(nombre, nif)/producto(descripcion, precio, cantidad)
			 */
			switch (op) {
			case 0:
				System.out.println("Gracias por haber usado la app \nAdios.");
				break;
			case 1:
				System.out.print("Nombre del cliente \r\n-> ");
				String nomCliente = scan.nextLine();
				System.out.print("nif del cliente \r\n-> ");
				int nifCliente = 0;
				try {
					nifCliente = scan.nextInt();
					scan.nextLine();
				} catch (InputMismatchException e) {
					scan.nextLine();
					System.err.println(errorNum + "\nIntentalo de nuevo");
					break;
				}
				System.out.print("Descripcion del pedido \r\n-> ");
				String descripcion = scan.nextLine();
				System.out.print("Precio del pedido \r\n-> ");
				double precio = 0;
				try {
					precio = scan.nextDouble();
					scan.nextLine();
				} catch (InputMismatchException e) {
					scan.nextLine();
					System.err.println(errorNum+" (Ej: 4,5)\nIntentalo de nuevo");
					break;
				}
				System.out.print("Cantidad del pedido \r\n-> ");
				int cantidad = 0;
				try {
					cantidad = scan.nextInt();
					scan.nextLine();
				} catch (InputMismatchException e) {
					scan.nextLine();
					System.err.println(errorNum + "\nIntentalo de nuevo");
					break;
				}
				funcionalidades.insertarCliente(nomCliente,nifCliente,descripcion,precio,cantidad);
				break;
			case 2:
				System.out.println("Nif del cliente");
				nifCliente = scan.nextInt();
				funcionalidades.eliminarPedido(nifCliente);
				break;
			case 3:
				System.out.println("Nif del cliente");
				nifCliente = scan.nextInt();
				System.out.println(funcionalidades.mostrarPedidosCliente(nifCliente));
				break;
			case 4:
				System.out.println("Nif del cliente");
				nifCliente = scan.nextInt();
				System.out.println(funcionalidades.mostrarGastodePedidos(nifCliente));
				break;
			case 5:
				System.out.println(funcionalidades.listarTodosLosNifs());
				break;
			default:
				System.err.println("Opcion equivocada");
				break;
			}
		} while (op!=0);
		scan.close();
	}
}
