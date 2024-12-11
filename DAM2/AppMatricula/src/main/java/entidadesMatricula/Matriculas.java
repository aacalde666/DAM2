package entidadesMatricula;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Matriculas {
	private List<Matricula> matriculas = new LinkedList<Matricula>();
	public Matriculas(List<Matricula> matriculas) {
		super();
		this.matriculas = matriculas;
	}
	public Matriculas() {
		super();
	}
	@XmlElement(name = "matricula")
	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
}
