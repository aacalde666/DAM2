package Exception;

@SuppressWarnings("serial")
public class AlumnoException extends RuntimeException {

	public AlumnoException(String mensaje) {
		super(mensaje);
	}
	public AlumnoException() {
		super("El a�o de nacimiento no puede ser negativo ni 0. \n");
	}
}
