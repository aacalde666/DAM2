package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import beans.Contacto;
import beans.Datos;

public class AppAgenda1 {
	static Map<Integer, Contacto> agenda = new TreeMap<>();
	static int id = agenda.size()+1;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		System.out.print("nombre de la agenda: ");
		String nomAgenda = scan.nextLine();
		try {
			recuperarAgenda(new File(nomAgenda));
		} catch (IOException e) {
			System.out.println("Error al recuperar la agenda");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error al recuperar la agenda");
			e.printStackTrace();
		}
		int op;
		do {
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Salir");
			System.out.print("Opcion: ");
			op = Integer.parseInt(scan.nextLine());
			switch (op) {
			case 1:
				insertarContacto();
				break;
			case 2:
				System.out.print("Introduzca nombre: ");
				String nom = scan.nextLine();
				for (Contacto c : guardarContacto(nom)){
					System.out.println(c.toString());
				}
				break;
			case 3:
				try {
					guardarAgenda(new File(nomAgenda));
				} catch (IOException e) {
					System.out.println("Error al guardar los contactos");
					e.printStackTrace();
				}
				System.out.println("Saliendo");
				break;
			default:
				break;
			}
		} while (op!=3);
		scan.close();
	}
	static void insertarContacto() {
		System.out.print("Nombre: ");
		String nombre = scan.nextLine();
		System.out.print("direccion: ");
		String dir = scan.nextLine();
		System.out.print("telefono: ");
		String tel = scan.nextLine();
		Contacto c = new Contacto(nombre, new Datos(tel, dir));
		agenda.put(id++, c);
	}
	static LinkedList<Contacto> guardarContacto(String nombre) {
		LinkedList<Contacto> contactos = new LinkedList<Contacto>();
//		Set<Integer> claves = agenda.keySet();
//		Iterator<Integer> it = claves.iterator();
//		while (it.hasNext()) {
//			Integer clave = it.next();
//			if (agenda.get(clave).getNombre().equals(nombre)) {
//				contactos.add(agenda.get(clave));
//				
//			}
//		}
		for (Map.Entry<Integer, Contacto> cts : agenda.entrySet()) {
			if (cts.getValue().getNombre().equals(nombre)) {
				contactos.add(cts.getValue());
			}
		}
		return contactos;
	}
	static void guardarAgenda(File f) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(agenda);
		oos.close();
	}
	static void recuperarAgenda(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (!f.exists()) {
			return;
		}
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		agenda = (Map<Integer, Contacto>) ois.readObject();
		ois.close();
	}
}
