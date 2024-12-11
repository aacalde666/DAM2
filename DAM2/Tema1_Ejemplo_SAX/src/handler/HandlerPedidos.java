package handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerPedidos extends DefaultHandler{
	private List<Pedido> pedidos = new LinkedList<>();
	private boolean enNombre, enNif, enDescripcion, enPrecio, enCantidad;
	private String nombre, nif, descripcion, precio, cantidad;
	private Cliente cli = new Cliente();
	private Pedido ped = new Pedido();
	private Productos prod = new Productos();
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("nombre")) {
			enNombre = true;
		}else if (qName.equals("nif")) {
			enNif = true;
		}else if (qName.equals("descripcion")) {
			enDescripcion = true;
		}else if (qName.equals("precio")) {
			enPrecio = true;
		}else if (qName.equals("cantidad")) {
			enCantidad = true;
		}else if (qName.equals("cliente")) {
			cli.setId(Integer.parseInt(attributes.getValue("id")));
		}
//		switch (qName) {
//		case "nombre":
//			enNombre = true;
//			break;
//		case "nif":
//			enNif = true;
//			break;
//		case "descripcion":
//			enDescripcion = true;
//			break;
//		case "precio":
//			enPrecio = true;
//			break;
//		case "cantidad":
//			enCantidad = true;
//			break;
//		}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (enNombre) {
			nombre = new String(ch, start, length);
			enNombre = false;
			cli.setNombre(nombre);
		}else if (enNif) {
			nif = new String(ch, start, length);
			enNif = false;
			cli.setNif(nif);
		}else if (enDescripcion) {
			descripcion = new String(ch, start, length);
			enDescripcion = false;
			prod.setNombre(descripcion);
		}else if (enPrecio) {
			precio = new String(ch, start, length);
			enPrecio = false;
			prod.setPrecio(Double.parseDouble(precio));
		}else if (enCantidad) {
			cantidad = new String(ch, start, length);
			enCantidad = false;
			prod.setCantidad(Integer.parseInt(cantidad));
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("cliente")) {
			ped.setCliente(cli);
			cli = new Cliente();
		}
		if (qName.equals("producto")) {
			ped.getListaProductos().add(prod);
			prod = new Productos();
		}
		if (qName.equals("listaProductos")) {
			pedidos.add(ped);
			ped = new Pedido();
		}
	}
}
