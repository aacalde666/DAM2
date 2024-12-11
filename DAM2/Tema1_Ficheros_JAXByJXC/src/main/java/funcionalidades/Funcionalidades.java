package funcionalidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import clasesGeneradas.Pedidos;
import clasesGeneradas.Pedidos.Pedido;
import clasesGeneradas.Pedidos.Pedido.Cliente;
import clasesGeneradas.Pedidos.Pedido.ListaProductos;
import clasesGeneradas.Pedidos.Pedido.ListaProductos.Producto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Funcionalidades {
	public static Pedidos leerFicheroXMLJAXB(File fichero) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		Pedidos libros = (Pedidos) u.unmarshal(fichero);
		return libros;
	}
	
	public static void escribirFicheroXML(Pedidos pedidos) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(pedidos, getFichero());
	}
	public static File getFichero() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("configuracion.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
		}
		return new File(conf.getProperty("nomfichero"));
	}
	public static void insertarProducto(int id, String nif, String nombre, String descripcion, double precio, int contidad) {
		Pedidos pedidos = null;
		Pedido pedido = null;
		if (!getFichero().exists()) {
			pedidos = new Pedidos();
		}else {
			try {
				pedidos = leerFicheroXMLJAXB(getFichero());
				pedido = recuperarPedido(pedidos);
			} catch (JAXBException e) {
				System.err.println("Error al leer el fichero");
				e.printStackTrace();
			}
		}
		if (pedido.getCliente().getId().equals(id)) {
			pedido = agregarNuevoPedidoaCliente(id, descripcion, precio, contidad, pedido);
		}else {
			pedido = agregarNuevoPedido(id, nif, nombre, descripcion, precio, contidad);
			pedidos.getPedido().add(pedido);
		}
		try {
			escribirFicheroXML(eliminarPedido(pedidos, 0));
		} catch (JAXBException e) {
			System.err.println("No se a podido escribir en el fichero");
			e.printStackTrace();
		}
	}
	static Pedido agregarNuevoPedido(int id,String nif, String nombre, String descripcion, double precio, int contidad) {
		Pedido pedido = new Pedido();
		ListaProductos listaProductos = new ListaProductos();
		Producto producto = new Producto(descripcion, precio, contidad);
		listaProductos = new ListaProductos();
		listaProductos.getProducto().add(producto);
		pedido = new Pedido();
		pedido.setListaProductos(listaProductos);
		pedido.setCliente(new Cliente(nombre, nif, id));
		System.err.println("Agregado correctamente el nuevo pedido");
		return pedido;
	}
	static Pedido agregarNuevoPedidoaCliente(int id, String descripcion, double precio, int contidad, Pedido pedido) {
		ListaProductos listaProductos = pedido.getListaProductos();
		Producto producto = new Producto(descripcion, precio, contidad);
		listaProductos.getProducto().add(producto);
		pedido.setListaProductos(listaProductos);
		System.err.println("Agregado correctamente al nif: "+pedido.getCliente().getNif());
		return pedido;
	}
	static Producto recuperarProducto(ListaProductos listaProductos) {
		Producto producto = null;
		for (Producto produc : listaProductos.getProducto()) {
			producto = produc;
		}
		return producto;
	}
	static Pedido recuperarPedido (Pedidos pedidos) {
		Pedido pedido = null;
		for (Pedido ped : pedidos.getPedido()) {
			pedido = ped;
		}
		return pedido;
	}
	public static boolean verSiEstaYaInsertadoUnCliente(int id) {
		Pedidos pedidos = null;
		boolean esta = true;
		if (!getFichero().exists()) {
			crearFicheroClienteVacio();
		}else {
			try {
				pedidos = leerFicheroXMLJAXB(getFichero());
			} catch (JAXBException e) {
				System.err.println("No se a podido leer el fichero");
				e.printStackTrace();
			}
			Pedido pedido = recuperarPedido(pedidos);
			if (pedido.getCliente().getId().equals(id)) {
				esta = false;
				System.err.println("Ya existe el cliente: "+pedido.getCliente().getNombre()+" cuyo nif es: "+pedido.getCliente().getNif());
			}else {
				esta=true;
			}
		}
		return esta;
	}
	public static String clienteMayorFactura() {
		Pedidos pedidos = null;
		try {
			pedidos= leerFicheroXMLJAXB(getFichero());
		} catch (JAXBException e) {
			System.err.println("No se a podido leer el fichero");
			e.printStackTrace();
		}
		double preci = 0;
		double max = 0;
		String nomCliente = "";
		ListaProductos listaProductos;
		for (Pedido pedido : pedidos.getPedido()) {
			preci=0;
			listaProductos = pedido.getListaProductos();
			for (Producto prod : listaProductos.getProducto()) {
				preci += prod.getPrecio();
			}
			if (max<preci) {
				max=preci;
				nomCliente = pedido.toString();
			}
		}
		return nomCliente;
	}
	static void crearFicheroClienteVacio() {
		try {
			Pedido pedido = new Pedido(new Cliente("", "", 0), null);
			Pedidos pedidos = new Pedidos();
			pedidos.getPedido().add(pedido);
			escribirFicheroXML(pedidos);
		} catch (JAXBException e) {
			System.err.println("No se a podido escribir el fichero");
			e.printStackTrace();
		}
	}
	public static Pedidos eliminarPedido(Pedidos pedidos,int id) throws JAXBException {
		for (int i = 0; i < pedidos.getPedido().size(); i++) {
			if (pedidos.getPedido().get(i).getCliente().getId().equals(id)) {
				pedidos.getPedido().remove(i);
			}
		}
		return pedidos;
	}
	public static void listaId(Pedidos pedidos) {
		String id = "";
		for (Pedido pedido : pedidos.getPedido()) {
			id += pedido.getCliente().getId()+", ";
		}
		System.err.println(id);
	}
	public static void verProductos(Pedidos pedidos, int id) {
		String p = "";
		for (Pedido pedido : pedidos.getPedido()) {
			for (Producto producto : pedido.getListaProductos().getProducto()) {
				if (pedido.getCliente().getId().equals(id)) {
					p+=producto.getDescripcion()+", ";
				}
			}
		}
		System.out.println(p);
	}
	public static Pedidos eliminarProducto(Pedidos pedidos, String descripcion) {
		for (int i = 0; i < pedidos.getPedido().size(); i++) {
			for (int j = 0; j < pedidos.getPedido().get(i).getListaProductos().getProducto().size(); j++) {
				if (pedidos.getPedido().get(i).getListaProductos().getProducto().get(j).getDescripcion().equals(descripcion)) {
					pedidos.getPedido().get(i).getListaProductos().getProducto().remove(j);
				}
			}
		}
		return pedidos;
	}
}
