package logica;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class funcionalidades {
	static String ficheroProp = "conf.props";
	private static String getNodeValue(Node pedido, String value) {
		String nombre = "";
		NodeList datosPedidos = pedido.getChildNodes();
		for (int i = 0; i < datosPedidos.getLength(); i++) {
			Node datoPedido = datosPedidos.item(i);
			NodeList datoClientes = datoPedido.getChildNodes();
			for (int j = 0; j < datoClientes.getLength(); j++) {
				Node datoCliente = datoClientes.item(j);
				if (pedido.getNodeType() == Node.ELEMENT_NODE) {
					if(datoCliente.getNodeName().equals(value)) {
						nombre = datoCliente.getFirstChild().getNodeValue();
					}
				}
			}
		}
		return nombre;
	}
	public static void insertarCliente(String nomCliente, int nifCliente, String descripcion, double precio, int cantidad) {
		Document doc;
		if (!FuncionalidadesXML.getFichero(ficheroProp).exists()) {
			try {
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element raiz = doc.createElement("pedidos");
				doc.appendChild(raiz);
				FuncionalidadesXML.escribirFicheroXML(doc, ficheroProp);
			} catch (ParserConfigurationException e) {
				System.err.println("No se a podido crear el fichero");
			}
		}
		try {
			doc = FuncionalidadesXML.leerFicheroXML(ficheroProp);
			Element nuevoPedido = doc.createElement("pedido");
			Node raiz = doc.getFirstChild();
			raiz.appendChild(nuevoPedido);
			//Cliente
			Element cliente = doc.createElement("cliente");
			nuevoPedido.appendChild(cliente);
			
			Element nomCli = doc.createElement("nombre");
			nomCli.appendChild(doc.createTextNode(nomCliente));
			cliente.appendChild(nomCli);
			
			Element nifCli = doc.createElement("nif");
			nifCli.appendChild(doc.createTextNode(nifCliente+""));
			cliente.appendChild(nifCli);
			//Pedido
			Element producto = doc.createElement("producto");
			nuevoPedido.appendChild(producto);
			
			Element desc = doc.createElement("descripcion");
			desc.appendChild(doc.createTextNode(descripcion));
			producto.appendChild(desc);
					
			Element preci = doc.createElement("precio");
			preci.appendChild(doc.createTextNode(precio+""));
			producto.appendChild(preci);
					
			Element cant = doc.createElement("cantidad");
			cant.appendChild(doc.createTextNode(cantidad+""));
			producto.appendChild(cant);
			FuncionalidadesXML.escribirFicheroXML(doc,ficheroProp);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void eliminarPedido(int nifCliente) {
		Document doc = null;
		try {
			doc = FuncionalidadesXML.leerFicheroXML(ficheroProp);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		Node raiz = doc.getFirstChild();
		NodeList pedidos = raiz.getChildNodes();
		for (int i = 0; i < pedidos.getLength(); i++) {
			Node pedido = pedidos.item(i);
			if (pedido.getNodeType() == Node.ELEMENT_NODE) {
				if (getNodeValue(pedido,"nif").equals(nifCliente+"")) {
					raiz.removeChild(pedido);
				}
			}
		}
		FuncionalidadesXML.escribirFicheroXML(doc,ficheroProp);
	}
	public static List<String> mostrarPedidosCliente(int nifCliente) {
		List<String> listaPedidos = new LinkedList<String>();
		Document doc = null;
		try {
			doc = FuncionalidadesXML.leerFicheroXML(ficheroProp);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		Node raiz = doc.getFirstChild();
		NodeList nodoListaPedidos = raiz.getChildNodes();
		for (int i = 0; i < nodoListaPedidos.getLength(); i++) {
			Node nodoPedido = nodoListaPedidos.item(i);
			if (nodoPedido.getNodeType() == Node.ELEMENT_NODE) {
				if (getNodeValue(nodoPedido,"nif").equals(nifCliente+"")) {
					String nombre = getNodeValue(nodoPedido, "nombre");
					String nif = getNodeValue(nodoPedido, "nif");
					String descripcion = getNodeValue(nodoPedido, "descripcion");
					String precio = getNodeValue(nodoPedido, "precio");
					String cantidad = getNodeValue(nodoPedido, "cantidad");
					String pedidoCompleto = "Cliente\n	Nombre: "+nombre
							+"\n	Nif: "+nif+"\n	Producto\n		descripción: "+descripcion
							+"\n		Precio: "+precio+"\n		Cantidad: "+cantidad+"\n";
					listaPedidos.add(pedidoCompleto);
				}
			}
		}
		return listaPedidos;
	}
	public static HashSet<String> listarTodosLosNifs() {
		HashSet<String> listaNifs = new HashSet<String>();
		Document doc = null;
		try {
			doc = FuncionalidadesXML.leerFicheroXML(ficheroProp);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		Node raiz = doc.getFirstChild();
		NodeList nodoListaPedidos = raiz.getChildNodes();
		for (int i = 0; i < nodoListaPedidos.getLength(); i++) {
			Node nodoPedido = nodoListaPedidos.item(i);
			if (nodoPedido.getNodeType() == Node.ELEMENT_NODE) {
				listaNifs.add(getNodeValue(nodoPedido, "nif"));
			}
		}
		return listaNifs;
	}
	public static Double mostrarGastodePedidos(int nifCliente) {
		Document doc = null;
		try {
			doc = FuncionalidadesXML.leerFicheroXML(ficheroProp);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		Node raiz = doc.getFirstChild();
		NodeList nodoListaPedidos = raiz.getChildNodes();
		double result = 0;
		for (int i = 0; i < nodoListaPedidos.getLength(); i++) {
			Node nodoPedido = nodoListaPedidos.item(i);
			if (nodoPedido.getNodeType() == Node.ELEMENT_NODE) {
				if (getNodeValue(nodoPedido,"nif").equals(nifCliente+"")) {
					double precio = Double.parseDouble(getNodeValue(nodoPedido, "precio"));
					int cantidad = Integer.parseInt(getNodeValue(nodoPedido, "cantidad"));
					result+=precio*cantidad;
				}
			}
		}
		return Math.round(result*100)/100d;
	}
}
