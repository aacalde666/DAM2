package ClassGenerate;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "shiporders")
public class Shiporders {
	private List<Shiporder> shiporders = new LinkedList<>();
	public Shiporders(List<Shiporder> shiporders) {
		this.shiporders = shiporders;
	}
	public Shiporders() {
	}
	@XmlElement(name = "shiporder")
	public List<Shiporder> getShiporders() {
		return shiporders;
	}
	public void setShiporders(List<Shiporder> shiporders) {
		this.shiporders = shiporders;
	}
}
