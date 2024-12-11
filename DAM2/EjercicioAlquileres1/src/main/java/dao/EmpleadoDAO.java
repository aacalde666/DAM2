package dao;

import beans.Empleado;

public interface EmpleadoDAO {
	
	boolean insertEmpleado(Empleado emp);
	
	double getSueldo(String nif);
	
	Empleado getMejorEmpleado();
	
	Empleado getMejorEmpleado_v2();
	
	Empleado getEmpleado(String nif);

}
