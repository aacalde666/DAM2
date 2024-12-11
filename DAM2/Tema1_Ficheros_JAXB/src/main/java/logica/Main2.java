package logica;

import java.io.File;

import entidadesPedidos.Cliente;
import entidadesPedidos.Pedido;
import entidadesPedidos.Pedidos;
import entidadesPedidos.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class Main2 {

	public static void main(String[] args) throws JAXBException {
		Cliente cliente = new Cliente();
		cliente.setId(23);
		cliente.setNif("1224");
		cliente.setNombre("Julia");
		Productos p1 = new Productos();
		p1.setCantidad(3);
		p1.setNombre("ordenador");
		p1.setPrecio(1000);
		Productos p2 = new Productos();
		p2.setCantidad(5);
		p2.setNombre("pantalla");
		p2.setPrecio(10000);
		Pedido ped = new Pedido();
		ped.setCliente(cliente);
		ped.getListaProductos().add(p1);
		ped.getListaProductos().add(p2);
		Pedidos pedidos = new Pedidos();
		pedidos.getPedidos().add(ped);
		escribirFicheroXML(pedidos, new File("pedidos1.xml"));
	}
	private static void escribirFicheroXML(Pedidos pedido, File fichero) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(pedido, fichero);
	}
}
