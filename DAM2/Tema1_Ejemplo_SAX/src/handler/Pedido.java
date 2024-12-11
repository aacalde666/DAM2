package handler;

import java.util.LinkedList;
import java.util.List;

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
	public List<Productos> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<Productos> listaProductos) {
		this.listaProductos = listaProductos;
	}
}
