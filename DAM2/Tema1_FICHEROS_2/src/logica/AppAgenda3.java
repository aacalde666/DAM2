package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import beans.Contacto;
import beans.Datos;
import dao.ContactoDao;
import daoImpl.ContactoDaoImpl;

public class AppAgenda3 {
	static Scanner scan = new Scanner(System.in);
	private static Contacto c = new Contacto();
	public static void main(String[] args) {
//		System.out.print("nombre de la agenda: ");
//		String nomAgenda = scan.nextLine();
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("configuracion.props"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String nomAgenda = conf.getProperty("nomfichero");
		ContactoDao dao = new ContactoDaoImpl(new File(nomAgenda));
		int op = -1;
		do {
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Listar");
			System.out.println("4. Eliminar");
			System.out.println("5. Actualizar");
			System.out.println("0. Salir");
			System.out.print("Opcion: ");
			try {
				op = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Solo con numeros");
			}
			switch (op) {
			case 1:
					System.out.print("Nombre: ");
					String nombre = scan.nextLine();
					System.out.print("direccion: ");
					String dir = scan.nextLine();
					System.out.print("telefono: ");
					String tel = scan.nextLine();
					c = new Contacto(nombre, new Datos(tel, dir));
					try {
						dao.addContacto(c);	
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				break;
			case 2:
				System.out.print("Introduzca nombre: ");
				String nom = scan.nextLine();
				try {
					System.out.println(dao.getContacto(nom));
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					dao.listAgenda();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.print("Nombre contacto a eliminar :");
				nom = scan.nextLine();
				try {
					dao.deleteContacto(nom);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Contacto que quieres actualizar");
				nom = scan.nextLine();
				System.out.print("direccion: ");
				dir = scan.nextLine();
				System.out.print("telefono: ");
				tel = scan.nextLine();
				c = new Contacto(nom, new Datos(tel, dir));
				try {
					dao.updateContacto(nom, c);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 0:
				System.out.println("Saliendo");
				break;
			default:
				System.out.println("Opcion equivocada");
				break;
			}
		} while (op!=0);
		scan.close();
	}

}
