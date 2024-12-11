package dao;
import java.io.FileNotFoundException;
import java.io.IOException;

import beans.Contacto;

public interface ContactoDao {
	//metodos para hacer el CRUD
	//del contacto
	//CRUD: Create Reading Update Delete
	void addContacto(Contacto c) throws FileNotFoundException, IOException, ClassNotFoundException;
	Contacto getContacto(String nombre) throws IOException, ClassNotFoundException;
	void updateContacto(String nombre, Contacto c) throws FileNotFoundException, IOException, ClassNotFoundException;
	void deleteContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException;
	void listAgenda() throws IOException, ClassNotFoundException;
}
