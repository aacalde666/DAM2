package logica;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import handler.HandlerCuentas;
import handler.Cuenta;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		HandlerCuentas handlerCuentas = new HandlerCuentas();
		saxParser.parse("cuenta.xml", handlerCuentas);
		int n = 1;
		for(Cuenta cuen : handlerCuentas.getCuentas()) {
			if (n == cuen.getId()) {
				System.out.println("Total del dinero de la id cuenta: "+cuen.getId()+"\n"+cuen.getCantidad());
			}
		}
	}

}
