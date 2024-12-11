package Funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import ClassGenerate.Shiporder;
import ClassGenerate.Shiporder.Item;
import ClassGenerate.Shiporder.Shipto;
import ClassGenerate.Shiporders;
import UtilidadesTeclado.Teclado;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Func {
	static boolean error = false;
	private static Shiporders leerFicheroXMLJAXB(File fichero) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Shiporders.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		Shiporders shiporders = (Shiporders) u.unmarshal(fichero);
		return shiporders;
	}
	
	private static void escribirFicheroXMLJAXB(Shiporders shiporders) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Shiporders.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(shiporders, getFichero());
	}
	public static File getFichero() {
		Properties conf = new Properties();
		try {
			conf.load(new FileInputStream("conf.props"));
		} catch (IOException e) {
			System.err.println("No se encontro el fichero "+conf.toString());
			e.printStackTrace();
		}
		return new File(conf.getProperty("nomfichero"));
	}
	public static void insertarEnvio(String oderPerson, String orderid) {
		Shiporders shiporders = null;
		if (!getFichero().exists()) {
			shiporders = new Shiporders();
		}else {
			shiporders = cargarShiporders();
		}
		Shiporder shiporder = new Shiporder();
		shiporder.setOrderperson(oderPerson);
		shiporder.setShipto(shipto());
		System.out.println("Cuantos items/productos quieres insertar?");
		int cant = 0;
		while (!error) {
			try {
				System.out.print("-> ");
				cant = Teclado.leerEntero();
				error = true;
			} catch (NumberFormatException | InputMismatchException e) {
				error = false;
				System.err.println("Solo con numeros");
			}
		}
		error = false;
		for (int i = 0; i < cant; i++) {
			shiporder.getItem().add(item());
		}
		shiporder.setOrderid(orderid);
		shiporders.getShiporders().add(shiporder);
		try {
			escribirFicheroXMLJAXB(shiporders);
			System.out.println("\n---------------------------------------------------------\n"
					+ "Envio agregado correctamente al ficher: "+getFichero().getName()+"\n"
					+ "---------------------------------------------------------\n");
		} catch (JAXBException e) {
			System.err.println("No se a podido escribir en el fichero: "+getFichero().getName());
			e.printStackTrace();
		}
	}
	public static Shipto shipto() {
		Shipto shipto = new Shipto();
		System.out.print("Inserta nombre del cliente que pido el envio\n-> ");
		String nomCliente = Teclado.leerCadena();
		shipto.setName(nomCliente);
		System.out.println("Insertar la dirección, ciudad y país en la que vive el cliente");
		System.out.print("Dirección -> ");
		String address = Teclado.leerCadena();
		shipto.setAddress(address);
		System.out.print("Ciudad -> ");
		String city = Teclado.leerCadena();
		shipto.setCity(city);
		System.out.print("País -> ");
		String country = Teclado.leerCadena();
		shipto.setCountry(country);
		return shipto;
	}
	public static Item item () {
		Item item = new Item();
		System.out.println("Introduce el titulo, nota, cantidad y precio del item/producto");
		System.out.print("Titulo -> ");
		String title = Teclado.leerCadena();
		item.setTitle(title);
		System.out.print("Nota -> ");
		String note = Teclado.leerCadena();
		item.setNote(note);
		System.out.print("Cantidad ");
		int queantity = 0;
		while (!error){
			try {
				System.out.print("->");
				queantity = Teclado.leerEntero();
				error = true;
			} catch (NumberFormatException | InputMismatchException e) {
				error = false;
				System.err.println("Solo con numeros");
			}
		}
		error = false;
		item.setQuantity(queantity);
		System.out.print("Precio ");
		double price = 0;
		while (!error) {
			try {
				System.out.print("-> ");
				price = Teclado.leerDecimal();
				error = true;
			} catch (NumberFormatException | InputMismatchException e) {
				error = false;
				System.err.println("Solo con numeros y decimales");
			}
		}
		error = false;
		item.setPrice(price);
		return item;
	}
	public static void insertarItem(String orderid) {
		Shiporders shiporders = cargarShiporders();
		int cont = 0;
		for (Shiporder ship : shiporders.getShiporders()) {
			if (ship.getOrderid().equals(orderid)) {
				cont++;
			}
		}
		String nombre = "";
		if (cont>1) {
			System.out.print("Elige un nombre de cliente al que le quieres agregar su item/pedido\n"
					+ "-> ");
			nombre = Teclado.leerCadena();
			for (Shiporder ship : shiporders.getShiporders()) {
				if (ship.getShipto().getName().equals(nombre)) {
					ship.getItem().add(item());
				}
			}
		}else {
			for (Shiporder ship : shiporders.getShiporders()) {
				if (ship.getOrderid().equals(orderid)) {
					ship.getItem().add(item());
				}
			}
		}
		try {
			escribirFicheroXMLJAXB(shiporders);
			System.out.println("\n---------------------------------------------------------\n"
					+ "Item agregado correctamente al envio de: "+nombre+"\n"
					+ "---------------------------------------------------------\n");
		} catch (JAXBException e) {
			System.err.println("No se a podido escribir en el fichero: "+getFichero().getName());
			e.printStackTrace();
		}
	}
	public static void mostrarIdsDeEnvios() {
		Shiporders shiporders = cargarShiporders();
		Set<String> idEnvio = new TreeSet<>();
		for (Shiporder ship : shiporders.getShiporders()) {
			idEnvio.add(ship.getOrderid());
		}
		System.out.println("Nº Ids: "+idEnvio);
	}
	public static void mostrarInformacionDelEnvio(String orderid) {
		Shiporders shiporders = cargarShiporders();
		String idEnvio = "";
		int cont = 0;
		for (Shiporder ship : shiporders.getShiporders()) {
			if (ship.getOrderid().equals(orderid)) {
				cont++;
			}
		}
		String info="";
		if (cont>1) {
			info="Nombres: ";
			for (Shiporder order : shiporders.getShiporders()) {
				if (order.getOrderid().equals(orderid)) {
					idEnvio += order.getShipto().getName()+", ";
				}
			}
		}else {
			info="Info envio:\n";
			for (Shiporder order : shiporders.getShiporders()) {
				if (order.getOrderid().equals(orderid)) {
					idEnvio += "Nombre: "+order.getShipto().getName()+"\n"
							+ "Dirección: "+order.getShipto().getAddress()+"\n"
							+ "Ciudad: "+order.getShipto().getCity()+"\n"
							+ "País: "+order.getShipto().getCountry()+"\n";
				}
			}
		}
		System.out.println(info+""+idEnvio);
	}
	public static void modificarPrecioItem(String orderid) {
		Shiporders shiporders = cargarShiporders();
		Set<String> Item = new TreeSet<String>();
		for (Shiporder order : shiporders.getShiporders()) {
			if (order.getOrderid().equals(orderid)) {
				for (Item item : order.getItem()) {
					Item.add(item.getTitle());
				}
			}
		}
		System.out.println("Nombre de todos los items de: "+orderid);
		System.out.println(Item);
		System.out.print("Elige uno de los Items enseñados ^^\n-> ");
		String titItem = Teclado.leerCadena();
		if (!titItem.equals("")) {
			boolean ya = false;
			for (Shiporder order : shiporders.getShiporders()) {
				if (order.getOrderid().equals(orderid)) {
					String note = "";
					int i = 0;
					while (!ya) {
						if(order.getItem().get(i).getTitle().equals(titItem)) {
							int cont = 0;
							for (int j = 0; j < order.getItem().size(); j++) {
								if(order.getItem().get(j).getTitle().equals(titItem)) {
									cont++;
								}
							}
							if (cont>1) {
								System.out.println("Hay mas de un mismo nombre:"+order.getItem().get(i).getTitle());
								for (Item item : order.getItem()) {
									if (item.getTitle().equals(order.getItem().get(i).getTitle())) {
										note += item.getNote()+", ";
									}
								}
								System.out.println(note);
								System.out.println("Indica cual nota quieres modificar su precio ^^");
								String nota = Teclado.leerCadena();
								for (Item item : order.getItem()) {
									if (item.getNote().equals(nota)) {
										System.out.print("Precio anterior de "+titItem+" "+nota+": "+item.getPrice());
										double price = 0;
										while (!error) {
											try {
												System.out.print("-> ");
												price = Teclado.leerDecimal();
												error = true;
											} catch (NumberFormatException | InputMismatchException e) {
												error = false;
												System.out.println("Solo con numeros y decimales");
											}
										}
										item.setPrice(price);
									}
								}
								try {
									escribirFicheroXMLJAXB(shiporders);
									System.out.println("\n---------------------------------------------------------\n"
											+ "Precio modificado correctamente a "+titItem+" de: "+nota+"\n"
											+ "---------------------------------------------------------\n");
									ya = true;
								} catch (JAXBException e) {
									System.err.println("No se a podido escribir en el fichero: "+getFichero().getName());
									e.printStackTrace();
								}
							}else {
								System.out.print("Introduce el nuevo precio de "+titItem+"\n"
										+ "Precio anterior: "+order.getItem().get(i).getPrice());
								double price = 0;
								while (!error) {
									try {
										System.out.print("-> ");
										price = Teclado.leerDecimal();
										error = true;
									} catch (NumberFormatException | InputMismatchException e) {
										error = false;
										System.err.println("Solo con numeros y decimales");
									}
								}
								error = false;
								order.getItem().get(i).setPrice(price);
								try {
									escribirFicheroXMLJAXB(shiporders);
									System.out.println("\n---------------------------------------------------------\n"
											+ "Precio modificado correctamente a: "+titItem+"\n"
											+ "---------------------------------------------------------\n");
									ya = true;
								} catch (JAXBException e) {
									System.err.println("No se a podido escribir en el fichero: "+getFichero().getName());
									e.printStackTrace();
								}
							}
						}
						i++;
					}
				}
			}
		}else {
			System.err.println("Error, insertaste espacio en blanco");
		}
	}
	private static Shiporders cargarShiporders() {
		Shiporders shiporders = null;
		try {
			shiporders = leerFicheroXMLJAXB(getFichero());
		} catch (JAXBException e) {
			System.err.println("No se a podido leer el fichero: "+getFichero().getName());
			e.printStackTrace();
		}
		return shiporders;
	}
	public static boolean revisarQueExistaLaId(String orderid) {
		Shiporders shiporders = cargarShiporders();
		for (Shiporder shiporder : shiporders.getShiporders()) {
			if (shiporder.getOrderid().equals(orderid)) {
				return true;
			}
		}
		return false;
	}
}
