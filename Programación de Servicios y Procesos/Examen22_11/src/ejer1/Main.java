package ejer1;

/*6 puntos
 * Implementa el siguiente programa:
 * 
 * clase Almacen: la clase almacen contiene una lista llamada almacenPrincipal  que representa 
 * un almacen donde llegan unos números enteros positivos (estrictamente >0). Puede contener máximo N enteros. 
 * Además tiene otras dos listas,llamadas pares e impares, 
 * donde se almacenarán números pares e impares como explicado más adelante.
 * 
 * clase Prod: hay una sola instancia de la clase Prod. 
 * Representa un thread productor que normalmente duerme. 
 * Si el almacenPrincipal está a menos del 30% de su capacidad máxima N, el productor se despertará 
 * y producirá nuevos números llamando el método "produce" proporcionado por el profesor e irá 
 * almacenando los números en el almacenPrincipal.En el almacenPrincipal se irán reponiendo 
 * números hasta que el almacenPrincipal se llene al 90%. 
 * A ese punto el productor volverá a dormir.
 * NOTA: mientras el productor produce, los clasificadores siguien trabajando si pueden.
 * 
 * clase Clasificador: hay X instancias de clasificadores. Representa un thread clasificador que pilla un número 
 * del almacenPrincipal, ejecuta el método "clasifica" proporcionado por el profesor, 
 * y luego almacena el número en el array correcto (pares o impares).
 * 
 * clase Cons: hay C instancias de Cons. Cada Cons, aleatoriamente, decide si consumir un par o un impar. Accederá al array 
 * correspondiente, pillará un número y ejecutará el método "consumir" proporcionado por el profesor. 
 * Repetirán esto comportamiento indefinitamente. Si no hay números en el array, se quedarán a la espera de que haya.
 * */
public class Main {

	static final int N = 10;// tamaño de los arrays de Almacen
	static final int X = 2;// numero de Clasificadores
	static final int C = 4;// numero de Cons

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Almacen a = new Almacen(N);
		// lanza un Prod
		new Thread(new Prod()).start();

		// lanza X Clasificador
		for (int i = 0; i < X; i++) {
			new Thread(new Clasificador()).start();
		}

		// lanza C Consumidores, la mitad consume pares, la mitad consume impares
		for (int i = 0; i < C; i++) {
			new Thread(new Cons(i % 2 == 0)).start();
		}

	}

}