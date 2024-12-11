package dao;

import beans.Piso;

public interface PisoDAO {
	
	boolean insertPiso(Piso piso);
	
	boolean updateMens(Piso piso);
	
	boolean rentPiso(Piso piso);
	
	String getNombreEmpleado(int id);

}
