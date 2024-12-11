package main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import logica.Fun;
import logica.Pedido;
import net.sf.jasperreports.engine.JRException;

public class Main {
	public static void main(String[] args) {
		try {
			JSONArray ped = Fun.leerFicheroJSON(Fun.getFichero("ficheroDaros"));
			List<Pedido> pedidos = new LinkedList<Pedido>();
			for (int i = 0; i < ped.length(); i++) {
				JSONObject pedi = ped.getJSONObject(i);
				String nombre = pedi.getString("nombre");
				int cantidad = pedi.getInt("cantidad");
				double precio = pedi.getDouble("precio");
				pedidos.add(new Pedido(nombre,cantidad,precio));
			}
			try {
				Fun.generarInformes(pedidos);
			} catch (JRException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
