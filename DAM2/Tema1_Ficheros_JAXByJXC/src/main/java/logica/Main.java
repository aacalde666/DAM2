package logica;

import UtilidadesTeclado.Teclado;
import clasesGeneradas.Pedidos;
import funcionalidades.Funcionalidades;
import jakarta.xml.bind.JAXBException;

public class Main {

	public static void main(String[] args) {
		int op = -1;
		do {
			System.out.println("Elige una de estas opciones");
			System.out.print("1. Insertar nuevo pedido a un cliente nuevo o a un cliente existente \n"
					+ "2. Mostrar el cliente con la mayor factura \n"
					+ "3. Eliminar pedido \n"
					+ "4. Eliminar producto \n"
					+ "0. Salir\n"
					+ "->");
			op = Teclado.leerEntero();
			switch (op) {
			case 1:
				System.out.println("Inserta id nuevo o existente");
				int id = Teclado.leerEntero();
				String nifCliente = "";
				String nomCliente = "";
				boolean esta = Funcionalidades.verSiEstaYaInsertadoUnCliente(id);
				if (esta) {
					System.out.println("Inserta el nif del cliente");
					nifCliente = Teclado.leerCadena();
					System.out.println("Inserta el nombre del cliente");
					nomCliente = Teclado.leerCadena();
				}
				System.out.println("Cuantos pedidos va a hacer?");
				int cant = Teclado.leerEntero();
				for (int i = 1; i <= cant; i++) {
					System.out.println("Inserta la "+i+"º descripcion del producto");
					String desProducto = Teclado.leerCadena();
					System.out.println("Inserta el "+i+"º precio del producto");
					double precio = Teclado.leerDecimal();
					System.out.println("Instroduce la "+i+"º cantidad deseada");
					int cantidad = Teclado.leerEntero();
					Funcionalidades.insertarProducto(id, nifCliente, nomCliente, desProducto, precio, cantidad);
				}
				break;
			case 2:
				System.out.println(Funcionalidades.clienteMayorFactura());
				break;
			case 3:
				Pedidos pedidos = null;
				try {
					pedidos = Funcionalidades.leerFicheroXMLJAXB(Funcionalidades.getFichero());
					Funcionalidades.listaId(pedidos);
					System.out.print("Id del pedido que quieres eliminar\n->");
					id = Teclado.leerEntero();
					Funcionalidades.escribirFicheroXML(Funcionalidades.eliminarPedido(pedidos, id));
				} catch (JAXBException e) {
					System.err.println("Error al leer o escribir el fichero");
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					pedidos = Funcionalidades.leerFicheroXMLJAXB(Funcionalidades.getFichero());
					System.out.println("Id del pedido que quieres eliminar su producto");
					Funcionalidades.listaId(pedidos);
					System.out.print("-> ");
					id = Teclado.leerEntero();
					System.out.println("Indica que producto quieres eliminar");
					Funcionalidades.verProductos(pedidos,id);
					String descripcion = Teclado.leerCadena();
					Funcionalidades.escribirFicheroXML(Funcionalidades.eliminarProducto(pedidos, descripcion));
				} catch (JAXBException e) {
					System.err.println("Error al leer o escribir el fichero");
					e.printStackTrace();
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
