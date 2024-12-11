package Ej1;

import java.io.File;
import java.io.IOException;

public class ej2 {
	public void lanzadorsuma(long a, long b, String archivo, Long initTime) {
		//directorio C:\Users\aacal\Programación de Servicios y Procesos\Ejercicios\src\Ej1
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("java", "-cp",".\\bin", "Ej1.ej1", ""+a, ""+b, initTime.toString());
			pb.redirectError(new File("error.txt"));
			pb.redirectOutput(new File(archivo));
			pb.start();
		} catch (IOException e) {
			System.out.println("Error");
		}
	}

	public static void main(String[] args) {
		ej2 l = new ej2();
//		Scanner teclado = new Scanner(System.in); 
//		System.out.print("Primer número: ");
//		int a =  teclado.nextInt();
//		System.out.print("Segundo número: ");
//		int b = teclado.nextInt();
//		long end = 500000000L*4;
		long end = 100;
		l.lanzadorsuma(1, end, "uno.txt", System.currentTimeMillis());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long begin = System.currentTimeMillis();
		l.lanzadorsuma(1, end/4, "a.txt", begin);
		l.lanzadorsuma((end/4)+1, end/2, "b.txt", begin);
		l.lanzadorsuma((end/2)+1, 3*end/4, "c.txt", begin);
		l.lanzadorsuma((3*end/4), end, "d.txt", begin);
//		teclado.close();
	}

}
