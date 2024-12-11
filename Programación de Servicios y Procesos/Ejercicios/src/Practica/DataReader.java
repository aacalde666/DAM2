package Practica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
	public static void main(String[] args) throws IOException {
		int inicio = Integer.parseInt(args[1]);
		int fin = Integer.parseInt(args[2]);
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String l;
		String d="";
		int cont = 0;
		while ((l = br.readLine())!=null) {
			if (cont>= inicio && cont < fin) {
				d+=l+"\n";
			}
			cont++;
		}
		System.out.println(d);
		br.close();
	}
}
