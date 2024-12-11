package entidadesPedidos;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pedidos {
	private List<Pedido> pedidos = new LinkedList<Pedido>();
	@XmlElement(name = "pedido")
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Pedidos(List<Pedido> pedidos) {
		super();
		this.pedidos = pedidos;
	}
	public Pedidos() {
		super();
	}
	
}
