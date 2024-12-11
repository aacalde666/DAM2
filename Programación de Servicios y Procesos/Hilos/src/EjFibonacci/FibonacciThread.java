package EjFibonacci;

import UtilidadesTeclado.Teclado;

public class FibonacciThread extends Thread{
	public void run() {
		System.out.print("-> ");
		int n = Teclado.leerEntero();
		if (n<3) {
			System.out.println("insertar un numero mayor que 2");
		}else {
			int n1 = 0;
			int n2 = 1;
			System.out.println(n1);
			System.out.println(n2);
			for (int i = 0; i < n; i++) {
				System.out.println(i+":"+(n1+n2)+"--"+n1+"+"+n2);
				int var = n2;
				n2 += n1;
				n1 = var;
			}
		}
	}
	public static void main(String[] args) {
		Thread ft1 = new Thread(new FibonacciThread());
		ft1.start();
	}
}
