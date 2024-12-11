package logica;

public class DatosAlumno {
	private String nombreAlumno;
	private int notaAlumno;
	public DatosAlumno(String nombreAlumno, int notaAlumno) {
		this.nombreAlumno = nombreAlumno;
		this.notaAlumno = notaAlumno;
	}
	public DatosAlumno() {
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public int getNotaAlumno() {
		return notaAlumno;
	}
	public void setNotaAlumno(int notaAlumno) {
		this.notaAlumno = notaAlumno;
	}
}
