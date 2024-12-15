package logica;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlType(propOrder = {"id_intercambio","id_emisor","id_receptorm","id_juego"})
public class Intercambios {
	private int id_intercambio,id_emisor,id_receptorm,id_juego;
	private Integer ID_Usuario;  // Asegúrate de que el campo sea correcto

    // Getter
    public Integer getID_Usuario() {
        return ID_Usuario;
    }

    // Setter
    public void setID_Usuario(Integer ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

	public Intercambios(int id_intercambio, int id_emisor, int id_receptorm, int id_juego) {
		super();
		this.id_intercambio = id_intercambio;
		this.id_emisor = id_emisor;
		this.id_receptorm = id_receptorm;
		this.id_juego = id_juego;
	}

	public Intercambios() {
		super();
	}
	@XmlElement(name = "ID_Intercambio")
	public int getId_intercambio() {
		return id_intercambio;
	}

	public void setId_intercambio(int id_intercambio) {
		this.id_intercambio = id_intercambio;
	}
	@XmlElement(name = "ID_Usuario_Emisor")
	public int getId_emisor() {
		return id_emisor;
	}

	public void setId_emisor(int id_emisor) {
		this.id_emisor = id_emisor;
	}
	@XmlElement(name = "ID_Usuario_Receptor")
	public int getId_receptorm() {
		return id_receptorm;
	}

	public void setId_receptorm(int id_receptorm) {
		this.id_receptorm = id_receptorm;
	}
	@XmlElement(name = "ID_Juego")
	public int getId_juego() {
		return id_juego;
	}

	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}
	
}
