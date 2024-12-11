package examRA1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MyManagerExam {
	static Process process;
	static int id=0;
	public static void main(String[] args) {
		String op = "";
		Scanner scan = new Scanner(System.in);

		// inserir tu nombre y apellidos en la siguiente variable
		String autor = "Adrian Alonso Calderon";

		// 3 colecciones de datos
		int[][] datos = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ 2, 2, 424, 234, 42, 423, 423, 4234, 234, 234, 234, 234, 234, 234, 234, 234, 234, 234, 23, 423, 423,
						423, 423, 423, 23, 423, 4, 234, 234, 234, 23, 423, 42, 35, 23, 54, 6543, 6, 576, 456, 34, 6534,
						5324, 67, 454, 754, 73, 453, 453, 476, 45, 745, 6 },
				{ 11, 22, 33, 44, 55, 66, 77, 88, 99, 111, 222, 333, 444, 555, 666, 777, 888, 999 } };
		boolean sigue = true;
		while (sigue) {
			System.out.println("Bienvenido a MyManager de " + autor);
			System.out.println("Indica la operacion (escribe \"menu\" para ver el menu)");
			System.out.print("MyManager>");
			op = scan.next();
			if (op.equals("menu")) {
				System.out.println("operaciones permitidas:");
				System.out.println("menu  -> abre este menu");
				System.out.println("input   -> recibe datos y crea MyProcessors");
				System.out.println("list  -> indica la lista de los MyProcessors activos");
				System.out.println("reset  -> elimina todos los MyWriter activos");
				System.out.println("connect -> lee lo que está escribiendo el MyProcessor con un determinado identificador");
				System.out.println("exit -> sale del programa");

			}

			if (op.equals("input")) {
				/*
				 * implementa la funcionalidad create: Esta funcion solicita al usuario un
				 * numero para saber con cuál coleccion de datos va a trabajar (Las 3
				 * colecciones de datos disponibles estan en la variable datos) En base a
				 * cuantos numeros hay en la colecion seleccionada , el programa crea
				 * MyProcessors. Habrá un MyProcessor por cada 5 numeros que se tienen que
				 * procesar. Por ejemplo, si el usuario ha elegido la primera coleccion, habrá 2
				 * MyProcessors. My manager luego repartirá los numeros por procesar entre los
				 * MyProcessors. Nota: si se vuelve a ejecutar la operación input, los
				 * MyProcessor existente deberán desaparecer y se crearán nuevos.
				 */
				id++;
				ProcessBuilder  pb = new ProcessBuilder("java","-cp",".\\bin","examRA1.MyProcessor",""+id);
				try {
					process = pb.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Indicame de las 3 colecciones quieres seleccionar");
				int colect = scan.nextInt();
				for (int i = 0; i <= datos.length; i++) {
					if (colect == 1) {
						boolean fin = false;
						int cont = 0;
						while (!fin) {
							for (int j = 0; j < 2; j++) {
								BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
								try {
									if(cont!=datos[colect].length) {
										cont++;
									}else if(cont==datos[colect].length) {
										fin=true;
									}
									bw.write(datos[i][cont-1]);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							
						}
						
					}
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String l;
				try {
					while ((l = br.readLine())!=null) {
						System.out.println(l);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (op.equals("list")) {
				/*
				 * implementa la funcionalidad list: Esta funcionalidad escribe una lista de los
				 * MyProcessor que se están actualmente ejecutando
				 */
				System.out.println(process);
			}

			if (op.equals("reset")) {
				/*
				 * implementa la funcionalidad reset: esta funcionalidad devuelve el programa al
				 * mismo estado en el que estaba al princio de su ejecución es decir que no
				 * deberá haber ningún MyProcessor ejecutandose
				 */
				reset();
			}

			if (op.equals("connect")) {
				/*
				 * implementa la funcionalidad connect: Esta funcionalidad solicita al usuario
				 * un número de identificador e imprime en pantalla todo lo que ha escrito el
				 * MyProcessor que tiene dicho identificador
				 */
				System.out.println("Indica a que proceso quieres conectarte");
				int id = scan.nextInt();
//				if (id>=0&&id<process.size()) {
//					try {
//						String line="";
//						while (in.get(id).ready()) {
//							System.out.println();
//						}
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//				}
			}
			if (op.equals("exit")) {
				/*
				 * implementa la funcionalidad exit: limpia los recursos y sale del programa
				 */
				reset();
				sigue = false;
			}
		}
		System.out.println("Gracias por haber usado MyManager de " + autor);
	}
	public static void reset() {
//		for (Process p : process) {
//			p.destroy();
//		}
	}

}
