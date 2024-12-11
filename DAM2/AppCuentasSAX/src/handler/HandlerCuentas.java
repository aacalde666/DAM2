package handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerCuentas extends DefaultHandler{
	private List<Cuenta> cuentas = new LinkedList<>();
	private List<String> titulares = new LinkedList<>();
	private boolean enID, enNomTitular, enCantidad;
	private String id, nomTitular, cantidad;
	private Cuenta cuen = new Cuenta();
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "idCuenta":
			enID = true;
			break;
		case "nombreTitular":
			enNomTitular = true;
			break;
		case "cantidad":
			enCantidad = true;
			break;
		}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (enID) {
			id = new String(ch, start, length);
			enID = false;
		}else if (enNomTitular) {
			nomTitular = new String(ch, start, length);
			titulares.add(nomTitular);
			enNomTitular = false;
		}else if (enCantidad) {
			cantidad = new String(ch, start, length);
			enCantidad = false;
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("idCuenta")) {
			cuen.setId(Integer.parseInt(id));
		}
		if (qName.equals("titulares")) {
			for (String titular : titulares) {
				cuen.getTitulares().add(titular);
			}
			titulares = new LinkedList<>();
		}
		if (qName.equals("cantidad")) {
			cuen.setCantidad(Integer.parseInt(cantidad));
		}
		if (qName.equals("cuenta")) {
			cuentas.add(cuen);
			cuen = new Cuenta();
		}
	}
}
