package ejmplosFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AccesoFicherosTexto {
	/**
	 * Metodo que dado un nombre de fichero que
	 * muestre sus propiedades. Si no existe lo indicará
	 * Clase File: objeto que esta asociado a una ruta
	 * del sistema de fichero
	 */
	static void listarPropiedades(File f){
		System.out.println((f.canExecute())?"Se puede ejecutar":"No se puede ejecutar");
		System.out.println(f.length()+" bytes");
		System.out.println(f.getTotalSpace()+" bytes");
	}
	
	public static void main(String[] args) throws IOException {
//		System.out.print("Nombre del Fichero: ");
		Scanner scan = new Scanner(System.in);
//		String nombreFich = scan.nextLine();
//		//nombreFich tiene que especificar toda la ruta,
//		//si no asume que es la ruta raíz del proyecto:
//		//C:\\Users\\aacal\\Desktop\\UtilidadesTeclado.jar
//		File file = new File(nombreFich);
//		listarPropiedades(file);
//		try {
//			System.out.println(listarDirectorio_v2(file,""));
//		} catch (DirectorioException e) {
//			System.out.println("No es un directorio");
//			//e.printStackTrace();
//		}
//		System.out.print("Ruta de directorio: ");
//		String d = scan.nextLine();
//		file = new File(d);
//		System.out.print("Fichero: ");
//		String n = scan.nextLine();
//		System.out.println(existeArchivo(file, n));
//		ejemploJavaNIO();
//		eliminarCaracter(new File("aaa"), '5');
//		System.out.print("Direccion completa del directorio: ");
//		File directorio = new File(scan.nextLine());
//		System.out.print("Nombre del fichero que deveria"
//				+ " existir en el directorio indicado (ej: fichero.txt): ");
//		String fichero = scan.nextLine();
//		System.out.println(existeArchivo(directorio, fichero));
//		System.out.print("Achivo de los usuarios: ");
//		String s = scan.nextLine();
		System.out.print("Usuario a eliminar: ");
		String usuario = scan.nextLine();
		eliminarUsuario(new File("usuarios"), usuario);
		scan.close();
	}
	
	/**
	 * Este metodo recibe una ruta que se supone
	 * que es un directorio
	 * @param directorio Ruta del sistema de 
	 * archivos
	 * @return una cadena con el listado de todo el contenido,
	 * si el parametro no es directorio devuelve null
	 */
	static String listarDirectorio(File directorio){
		String s = "";
		if (!directorio.isDirectory()) {
			return null;
		}
		for (File f : directorio.listFiles()) {
			s+=f+"\n";
		}
		return s;
	}
	/**
	 * Este metodo recibe una ruta que se supone
	 * que es un directorio
	 * @param directorio Ruta del sistema de 
	 * archivos
	 * @return una cadena con el listado de todo el contenido
	 * @throws DirectorioException si el parametro no es directorio
	 */
	static String listarDirectorio_v2(File directorio,String tab) throws DirectorioException {
		String s = "";
		if (!directorio.isDirectory()) {
			throw new DirectorioException("No es un directorio");
		}
		
		for (File f : directorio.listFiles()) {
//			s+=tab+"-"+f.getName()+"\n";
//			tab+="\t";
//			if (f.isDirectory()) {
//				s+=listarDirectorio_v2(f);
//				tab="\t";
//				tab+="\t";
//			}
			if (f.isDirectory()) {
				s+= tab+"*"+f.getName()+"\n"+listarDirectorio_v2(f,tab+"\t");
			} else {
				s+= tab+"*"+f.getName()+"\n";
			}
		}
		return s;
	}
	/**
	 * 
	 * @param directorio Direccion del directorio
	 * @param nomArchivo Archivo que deveria existir en el directorio indicado
	 * @return
	 */
	static boolean existe = false;
	static boolean existeArchivo(File directorio, String nomArchivo) {
		for (File f : directorio.listFiles()) {
			if (f.isDirectory()) {
				existeArchivo(f, nomArchivo);
			}else if (f.getName().equals(nomArchivo)) {
				existe = true;
				System.out.println("Esta es su ruta: "+"\n"+f);
			}
		}
		return existe;
	}
	static void ejemploJavaNIO() {
		//Clases Path, Paths, Files
		Path ruta = Paths.get("aaa");
		List<String> lineas = null;
		try {
			lineas = Files.readAllLines(ruta);
		} catch (IOException e) {
			System.out.println("Problema al abrir el archivo");
			e.printStackTrace();
		}
		if(lineas != null)
			for (String lin : lineas)
				System.out.println(lin);
	}
	/**
	 * Metodo que elimina un caracter de un
	 * archivo de texto
	 * @param archivo Nombre del archivo
	 * @param caracter Caracter a eliminar
	 * @throws IOException 
	 */
	static File eliminarCaracter(File archivo, char caracter) throws IOException {
		File f = new File("aa");
		FileReader fr;
		try {
			fr = new FileReader(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		FileWriter fw;
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
			fr.close();
			return null;
		}
		int t;
		while ((t=fr.read()) != -1) {
			if ((char) t != caracter) {
				fw.append((char)t);
			}
		}
		fr.close();
		fw.close();
		archivo.delete();
		f.renameTo(archivo);
		return f;
	}
	/**
	 * Metodo para eliminar un usuario de un fichero
	 * @param usuarios Fichero csv con los nombres de los usuarios
	 * @param nombre nombre del usuario a eliminar
	 * @throws IOException 
	 */
	static void eliminarUsuario(File usuarios, String nombre) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(usuarios));
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp"));
		String linea = null;
		while ((linea = br.readLine()) != null) {
//			if (!linea.contains(nombre)) {
//				bw.write(linea+"\n");
//			}
			String[] campos = linea.split(";");
			boolean esta = false;
			for (String c : campos) {
				if (c.equals(nombre)) {
					esta = true;
				}
			}if (!esta) {
				bw.write(linea+"\n");
			}
		}
		br.close();
		bw.close();
		usuarios.delete();
		new File("temp").renameTo(usuarios);
	}
}
