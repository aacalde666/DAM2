package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import beans.Equipo;
import beans.Jugador;
import dao.EquipoDao;
import daoImpl.EquipoDaoImpl;

public class Main {
	/*1.	Crear equipo nuevo (sin jugadores)
	 *2.	Insertar jugador en equipo
	 *3.	Eliminar jugador de un equipo dado su nombre
	 *4.	Cambiar posición de un jugador dado nombre de equipo, nombre de jugador y nueva posición
	 */
//	private static Equipo e = new Equipo("",null);
	private static Properties p = new Properties();
	public static void main(String[] args) {
		try {
			p.load(new FileInputStream("configuracion.props"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		File carpetaFile = new File(p.getProperty("NuevaCarpeta"));
		if (!carpetaFile.exists()) {
			carpetaFile.mkdir();
		}
		EquipoDao dao = new EquipoDaoImpl(new File(p.getProperty("nomfichero")));
		Scanner scan = new Scanner(System.in);
		int opcion = -1;
		do {
			System.out.println("1. Crear equipo nuevo (sin jugadores)\r\n"
					+ "2. Insertar jugador en equipo\r\n"
					+ "3. Eliminar jugador de un equipo dado su nombre\r\n"
					+ "4. Cambiar posición de un jugador dado nombre de equipo, "
					+ "nombre de jugador y nueva posición\r\n"
					+ "5. Listar jugadores\r\n"
					+ "0. Salir del programa");
			System.out.print("Elige una opcion \n ->");
			try {
				opcion = scan.nextInt();
			} catch (NumberFormatException | InputMismatchException e) {
				opcion=-1;
				System.err.println("Porfavor solo con numeros");
			}
			scan.nextLine();
			switch (opcion) {
			case 1:
				System.out.print("Nombre del equipo \n ->");
				String nomEquipo = scan.nextLine();
				dao.addEquipo(nomEquipo);
				break;
			case 2:
				System.out.print("Nombre del equipo \n ->");
				nomEquipo = scan.nextLine();
				boolean siEstaEquipo = existeEquipoEnFichero(nomEquipo);
				if (siEstaEquipo) {
					System.out.print("Nombre del jugador a introducir \n ->");
					String nomjugador = scan.nextLine();
					System.out.print("numero \n ->");
					int numJugador = scan.nextInt();
					scan.nextLine();
					System.out.print("y posicion del jugador \n ->");
					String posJugador = scan.nextLine();
					dao.insertPlayer(nomEquipo, new Jugador(nomjugador, numJugador, posJugador));
				}else {
					System.err.println("No existe el nombre ("+nomEquipo+") en ningun equipo \n"
							+ "Porfavor utiliza la opcion de insertar nuevo equipo (opcion 1)");
				}
				break;
			case 3:
				System.out.print("Nombre del equipo \r\n ->");
				nomEquipo = scan.nextLine();
				siEstaEquipo = existeEquipoEnFichero(nomEquipo);
				if (siEstaEquipo) {
					System.out.print("Nombre jugador a eliminar \r\n ->");
					String nombreJug = scan.nextLine();
					dao.deletePlayer(nomEquipo, nombreJug);
				}else {
					System.err.println("No existe el nombre ("+nomEquipo+") en ningun equipo \n"
							+ "Porfavor utiliza la opcion de insertar nuevo equipo (opcion 1)");
				}
				break;
			case 4:
				System.out.print("Nombre del equipo \r\n ->");
				nomEquipo = scan.nextLine();
				siEstaEquipo = existeEquipoEnFichero(nomEquipo);
				if (siEstaEquipo) {
					System.out.print("Nombre del jugador \r\n ->");
					String nomJugador = scan.nextLine();
					System.out.print("Numero actualizado del jugador \r\n ->");
					int numJugador = scan.nextInt();
					dao.updatePosition(nomEquipo, nomJugador, numJugador);
				}else {
					System.err.println("No existe el nombre ("+nomEquipo+") en ningun equipo \n"
							+ "Porfavor utiliza la opcion de insertar nuevo equipo (opcion 1)");
				}
				break;
			case 5:
				dao.listPlayers();
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				break;
			}
		} while (opcion != 0);
		scan.close();
	}
	private static boolean existeEquipoEnFichero(String nomEquipo) {
		ObjectInputStream ois;
		boolean encontrado = false;
		try {
			ois = new ObjectInputStream(new FileInputStream(p.getProperty("nomfichero")));
			boolean finFichero = false;
			while (!finFichero) {
				try {
					Equipo equipo = (Equipo) ois.readObject();
					if (equipo.getNombre().equals(nomEquipo)) {
						encontrado = true;
						finFichero = true;
						ois.close();
					}
				} catch (EOFException e) {
					finFichero = true;
				}catch (ClassNotFoundException | IOException e) {
					System.err.println("Error... Fich");
				}
			}
		} catch (IOException e) {
			System.err.println("No se pudo leer el fichero donde estan guardados los equipos");
		}
		return encontrado;
	}
}
