package logica;

import UtilidadesTeclado.Teclado;
import jakarta.xml.bind.JAXBException;

public class FuncionesExam {
	public static void intercambiado(int idJuego) {
		
	}
	public static void eliminaUsuario(int idUsuario) {
		try {
			BibliotecaVideojuegos bv = Funciones.leerFicheroXMLJAXB();
			for (int i = 0; i < bv.getUsuarios().size(); i++) {
				if (bv.getUsuarios().get(i).getId_usuario() == idUsuario) {
					if (bv.getIntercambios().get(i).getId_emisor()==idUsuario) {
						int idJuego = bv.getIntercambios().get(i).getId_juego();
						bv.getUsuarios().remove(i);
						bv.getIntercambios().remove(i);
						if (bv.getJuegos().get(i).getId_juego()==idJuego) {
							bv.getJuegos().remove(i);
						}
						Funciones.escribirFicheroXMLJAXB(bv);
					}
					
				}else {
					System.out.print("el id "+idUsuario+", no a hecho ningun intercambio para poder ser eliminado\n"
							+ "aun asi quieres eliminarlo? (s o n)\n-> ");
					String op = Teclado.leerCadena();
					boolean sn = false;
					while (!sn) {
						if (op.equals("s")) {
							bv.getUsuarios().remove(i);
							Funciones.escribirFicheroXMLJAXB(bv);
							System.out.println("Eliminacion de usuario hecha");
							sn = true;
						}else if (op.equals("n")) {
							System.out.println("Ok, no se eliminara el usuario con id "+idUsuario);
							sn = true;
						}else {
							System.out.print("Solo con s o n\n"
									+ "-> ");
							op = Teclado.leerCadena();
							sn = false;
						}
					}
				}
				if (bv.getUsuarios().get(i).getId_usuario()!=idUsuario) {
					System.out.println("No existe esta id usuario en la base de datos");
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	public static void generaInforme(int idUsuario) {
		
	}
}
