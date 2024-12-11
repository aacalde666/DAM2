package logica;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import beans.Contacto;
import beans.Datos;
import io.ObjectOutputStreamSinHeader;

public class AppAgenda2 {
	static Scanner scan = new Scanner(System.in);
	static File ficheroAgenda;
	public static void main(String[] args) {
		System.out.print("nombre de la agenda: ");
		String nomAgenda = scan.nextLine();
		ficheroAgenda = new File(nomAgenda);
		int op;
		do {
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Listar");
			System.out.println("4. Eliminar");
			System.out.println("0. Salir");
			System.out.print("Opcion: ");
			op = Integer.parseInt(scan.nextLine());
			switch (op) {
			case 1:
				try {
					insertarContacto();
				} catch (IOException e) {
					System.out.println("Error al insertar contacto");
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.print("Introduzca nombre: ");
				String nom = scan.nextLine();
				try {
					for (Contacto c : recuperarContacto(nom)){
						System.out.println(c.toString());
					}
				}catch (FileNotFoundException ex) {
					System.out.println("No tiene aun fichero agenda");
				} 
				catch (IOException | ClassNotFoundException e) {
					System.out.println("Error al buscar");
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					listarContactos();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.print("Nombre contacto a eliminar :");
				nom = scan.nextLine();
				try {
					eliminarContacto(nom);
				} catch (FileNotFoundException e) {
					System.out.println("No se encontrofichero");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Error al eliminar");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
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
	static List<Contacto> recuperarContacto(String nom) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Contacto> contactos = new LinkedList<Contacto>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo) {
			try {
				Contacto c = (Contacto) ois.readObject();
				if (c.getNombre().equals(nom)) {
					contactos.add(c);
				}
			}catch (EOFException e) {
				finArchivo = true;
			}
		}
		ois.close();
		return contactos;
	}
	static void insertarContacto() throws FileNotFoundException, IOException {
		System.out.print("Nombre: ");
		String nombre = scan.nextLine();
		System.out.print("direccion: ");
		String dir = scan.nextLine();
		System.out.print("telefono: ");
		String tel = scan.nextLine();
		Contacto c = new Contacto(nombre, new Datos(tel, dir));
		ObjectOutputStream oos;
		if (!ficheroAgenda.exists()) {
			oos = new ObjectOutputStream(new FileOutputStream(ficheroAgenda,true));
		}else {
			oos = new ObjectOutputStreamSinHeader(new FileOutputStream(ficheroAgenda,true));
		}
		
		oos.writeObject(c);
		oos.close();
	}
	static void listarContactos() throws ClassNotFoundException, IOException {
		List<Contacto> contactos = new LinkedList<Contacto>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo) {
			try {
				Contacto c = (Contacto) ois.readObject();
				contactos.add(c);
			}catch (EOFException e) {
				finArchivo = true;
			}
		}
		for (Contacto c : contactos) {
			System.out.println(c);
		}
		ois.close();
	}
	static void eliminarContacto(String nom) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp"));
		boolean finArchivo = false;
		while (!finArchivo) {
			try {
				Contacto c = (Contacto) ois.readObject();
				if (!c.getNombre().equals(nom)) {
					oos.writeObject(c);
				}
			}catch (EOFException e) {
				finArchivo = true;
			}
		}
		ois.close();
		oos.close();
		ficheroAgenda.delete();
		new File("temp").renameTo(ficheroAgenda);
	}
}
