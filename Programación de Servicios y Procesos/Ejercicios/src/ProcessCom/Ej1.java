package ProcessCom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej1 {

	public static void main(String[] args) throws IOException {
		long init = System.currentTimeMillis();
		ProcessBuilder pb =  new ProcessBuilder("ping", "-t","www.google.com");
		Process p = pb.start();
		InputStreamReader isr = new InputStreamReader(p.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String n;
		while ((n = br.readLine())!= null) {
			System.out.println(n);
		}
		long time = System.currentTimeMillis();
		System.out.println("\n -------------------------- \n Tiempo transcurrido: "+(time-init));
	}

}
