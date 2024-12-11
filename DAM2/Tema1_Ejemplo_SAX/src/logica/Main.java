package logica;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import handler.HandlerPedidos;
import handler.Pedido;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		HandlerPedidos handlerPedidos = new HandlerPedidos();
		saxParser.parse("pedidos.xml", handlerPedidos);
		for(Pedido ped : handlerPedidos.getPedidos()) {
			System.out.println("Cliente: "+ped.getCliente().getNombre()+" ID: "+ped.getCliente().getId());
			System.out.println("Productos: "+ped.getListaProductos().size());
		}
	}

}
