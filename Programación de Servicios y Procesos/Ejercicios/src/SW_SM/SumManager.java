package SW_SM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class SumManager {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		Process p = new ProcessBuilder("java","-cp",".\\bin","SW_SM.SumWorker").start();
		PrintStream ps = new PrintStream(p.getOutputStream(),true);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		boolean loop=false;
		int n=0;
		while (!loop) {
			System.out.print("Introduce un número: ");
			n = scan.nextInt();
			if (n<0) {
				System.out.println("Número negativo recibido. Terminando el programa...");
				p.destroy();
				loop=true;
			}else {
				ps.println(n);
	            System.out.println("SumWorker respondió: " + br.readLine());
			}
		}
		br.close();
		scan.close();
	}
}
