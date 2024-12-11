package daoImpl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import beans.Equipo;
import beans.Jugador;
import dao.EquipoDao;
import io.ObjectOutputStreamSinHeader;

public class EquipoDaoImpl implements EquipoDao{
	Properties p = new Properties();
	File ficheroEquipo;
	public EquipoDaoImpl(File f) {
		ficheroEquipo = f;
		try {
			p.load(new FileInputStream("configuracion.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el archivo de configuracion");
		}
	}
	@Override
	public void addEquipo(String nombre) {
		Equipo e = new Equipo();
		e.setNombre(nombre);
		ObjectOutputStream oos;
		try {
			if (!ficheroEquipo.exists()) {
				oos = new ObjectOutputStream(new FileOutputStream(ficheroEquipo,true));
				oos.writeObject(e);
				oos.close();
			}else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipo));
				Equipo eq = (Equipo) ois.readObject();
				if (!eq.getNombre().equals(nombre)) {
					oos = new ObjectOutputStreamSinHeader(new FileOutputStream(ficheroEquipo,true));
					e.setNombre(nombre);
					oos.writeObject(e);
					oos.close();
				}else {
					System.out.println("Ya existe el Equipo ("+nombre+")");
				}
				ois.close();
			}
		} catch (IOException | ClassNotFoundException e1) {
			System.err.println("Error al añadir");
		}
		
	}

	@Override
	public void insertPlayer(String nombreEquipo, Jugador jugador) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipo))) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(p.getProperty("ficheroTemporal")));
			boolean finArchivo = false;
			while (!finArchivo) {
				try {
					Equipo equip = (Equipo) ois.readObject();
					if (equip.getNombre().contains(nombreEquipo)) {
						if (!equip.getJugadors().contains(jugador)) {
							equip.getJugadors().add(jugador);
							oos.writeObject(equip);
						}else {
							oos.writeObject(equip);
						}
					}else {
						oos.writeObject(equip);
					}
				} catch (ClassNotFoundException | EOFException e1) {
					finArchivo = true;
				}
			}
			ois.close();
			oos.close();
			ficheroEquipo.delete();
			new File(p.getProperty("ficheroTemporal")).renameTo(ficheroEquipo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void deletePlayer(String nombreEquipo, String nombreJugador) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipo))){
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(p.getProperty("ficheroTemporal")));
			boolean finArchivo = false;
			while (!finArchivo) {
				try {
					Equipo equip = (Equipo) ois.readObject();
					if (equip.getNombre().contains(nombreEquipo)) {
						if (equip.getJugadors().contains(new Jugador(nombreJugador))) {
							equip.getJugadors().remove(new Jugador(nombreJugador));
							oos.writeObject(equip);
						}
					}else {
						oos.writeObject(equip);
					}
				} catch (ClassNotFoundException | EOFException e1) {
					finArchivo = true;
				}
			}
			ois.close();
			oos.close();
			ficheroEquipo.delete();
			new File(p.getProperty("ficheroTemporal")).renameTo(ficheroEquipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePosition(String nombreEquipo, String nombreJugador, int posición) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipo))) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(p.getProperty("ficheroTemporal")));
			boolean finArchivo = false;
			while (!finArchivo) {
				try {
					Equipo equip = (Equipo) ois.readObject();
					if (equip.getNombre().contains(nombreEquipo)) {
						for (Jugador jugador : equip.getJugadors()) {
							if (jugador.getNombre().equals(nombreJugador)) {
								jugador.setNumero(posición);
							}
						}
						oos.writeObject(equip);
					}else {
						oos.writeObject(equip);
					}
				} catch (ClassNotFoundException | EOFException e1) {
					finArchivo = true;
				}
			}
			ois.close();
			oos.close();
			ficheroEquipo.delete();
			new File(p.getProperty("ficheroTemporal")).renameTo(ficheroEquipo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void listPlayers() {
		List<Equipo> equipo = new LinkedList<Equipo>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipo))){
			boolean finArchivo = false;
			try {
				while (!finArchivo) {
					Equipo e = (Equipo) ois.readObject();
					equipo.add(e);
				}
			} catch (EOFException | ClassNotFoundException e) {
				finArchivo = true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Equipo equi : equipo) {
			System.out.println(equi);
		}
	}

}
