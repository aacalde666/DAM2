package daoImpl;

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

import beans.Contacto;
import dao.ContactoDao;
import io.ObjectOutputStreamSinHeader;

public class ContactoDaoImpl implements ContactoDao{
	File ficheroAgenda;
	public ContactoDaoImpl(File f) {
		ficheroAgenda = f;
	}
	@Override
	public void addContacto(Contacto c) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream oos;
		if (!ficheroAgenda.exists()) {
			oos = new ObjectOutputStream(new FileOutputStream(ficheroAgenda,true));
			oos.writeObject(c);
			oos.close();
		}else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
			Contacto con = (Contacto) ois.readObject();
			if (!con.getNombre().equals(c.getNombre())) {
				oos = new ObjectOutputStreamSinHeader(new FileOutputStream(ficheroAgenda,true));
				oos.writeObject(c);
				oos.close();
			}else {
				System.out.println("Ya existe el contacto: "+c.getNombre()+"\n usa la opcion de actulizar");
			}
			ois.close();
		}
	}
	@Override
	public Contacto getContacto(String nombre) throws IOException, ClassNotFoundException {
		Contacto contactos = new Contacto();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo) {
			try {
				Contacto c = (Contacto) ois.readObject();
				if (c.getNombre().equals(nombre)) {
					contactos.setNombre(nombre);
					contactos.setDatos(c.getDatos());
				}
			}catch (EOFException e) {
				finArchivo = true;
			}
		}
		ois.close();
		return contactos;
	}

	@Override
	public void updateContacto(String nombre, Contacto c) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp"));
		boolean finArchivo = false;
		while (!finArchivo) {
			try {
				Contacto con = (Contacto) ois.readObject();
				if (con.getNombre().equals(c.getNombre())) {
					oos.writeObject(c);
				}else {
					oos.writeObject(con);
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

	@Override
	public void deleteContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp"));
		boolean finArchivo = false;
		while (!finArchivo) {
			try {
				Contacto c = (Contacto) ois.readObject();
				if (!c.getNombre().equals(nombre)) {
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
	@Override
	public void listAgenda() throws IOException, ClassNotFoundException {
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
}