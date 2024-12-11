package logica_Alumno_Profesor;

public class Alumno {
	private int idAlumno;
	private String nombre;
	private double nota;
	private int idProfesor;
	public Alumno(int idAlumno, String nombre, int idProfesor, double nota) {
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.nota = nota;
		this.idProfesor = idProfesor;
	}
	public Alumno() {
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public int getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}
	@Override
	public String toString() {
		return "idAlumno=" + idAlumno + ", nombre=" + nombre + ", nota=" + nota + ", idProfesor=" + idProfesor;
	}
	
}
