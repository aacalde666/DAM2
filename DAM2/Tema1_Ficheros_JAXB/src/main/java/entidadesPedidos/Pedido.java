package entidadesPedidos;

import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

public class Pedido {
	private Cliente cliente;
	private List<Productos> listaProductos = new LinkedList<Productos>();
	public Pedido(Cliente cliente, List<Productos> productos) {
		super();
		this.cliente = cliente;
		this.listaProductos = productos;
	}
	public Pedido() {
		super();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@XmlElementWrapper(name = "listaProductos")
	@XmlElement(name = "producto")
	public List<Productos> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<Productos> listaProductos) {
		this.listaProductos = listaProductos;
	}
}
