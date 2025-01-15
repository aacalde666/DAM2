package Ejer5;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class IOUtility {
	public static void escribir(BufferedWriter bw,String msg)
	{
		try {
		bw.write(msg);
		bw.newLine();
		bw.flush();
		}catch(Exception e) {}
	}
	
	
	
	public static String leer(BufferedReader br)
	{
		String mensaje ="";
		try {
			mensaje = br.readLine();
			System.out.println(mensaje);
		}catch(Exception e) {}
		return mensaje;
	}
}
