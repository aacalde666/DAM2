package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException {
		JSONArray notas = leerFicheroJSON(new File("notas.json"));
		for (int i = 0; i < notas.length(); i++) {
			JSONObject alumno = notas.getJSONObject(i);
			String nombre = alumno.getString("nombre");
			System.out.println("Alumno: "+nombre);
			JSONArray notasAlumno = alumno.getJSONArray("notas");
			for (int j = 0; j < notasAlumno.length(); j++) {
				JSONObject nota = notasAlumno.getJSONObject(j);
				System.out.println(nota.getString("materia")+":"+nota.getInt("nota"));
			}
		}
		System.out.println();
		mostrarnotaMedia("carlos");
	}
	private static JSONArray leerFicheroJSON(File f) throws IOException {
		String cadena = "";
		InputStream  inputStream = Main.class.getClassLoader().getResourceAsStream(f.getName());
		BufferedReader ent = new BufferedReader(new InputStreamReader(inputStream));
		String linea;
		while ((linea = ent.readLine()) != null) {
			cadena+=linea;
		}
		ent.close();
		JSONArray notas = new JSONArray(cadena);
		return notas;
	}
	/**
	 * Mostrar la nota media del alumno pasado por parametro
	 * @throws IOException 
	 */
	private static void mostrarnotaMedia(String nomAlumno) throws IOException {
		double media = 0;
		JSONArray notas = leerFicheroJSON(new File("notas.json"));
		for (int i = 0; i < notas.length(); i++) {
			JSONObject alumno = notas.getJSONObject(i);
			String nombre = alumno.getString("nombre");
			if (nombre.equals(nomAlumno)) {
				System.out.println("Alumno: "+nombre);
				JSONArray notasAlumno = alumno.getJSONArray("notas");
				int n = 0;
				for (int j = 0; j < notasAlumno.length(); j++) {
					JSONObject nota = notasAlumno.getJSONObject(j);
					media+=nota.getDouble("nota");
					n++;
				}
				System.out.println("Nota media: "+(media/n));
			}
		}
	}
}
