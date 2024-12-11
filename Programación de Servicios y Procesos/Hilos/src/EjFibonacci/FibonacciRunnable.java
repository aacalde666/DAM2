package EjFibonacci;

import UtilidadesTeclado.Teclado;

public class FibonacciRunnable implements Runnable{
	private int n;
	public FibonacciRunnable(int n) {
		this.n = n;
	}
	@Override
	public void run() {
		if (n<3) {
			System.out.println("insertar un numero mayor que 2");
		}else {
			int n1 = 1;
			int n2 = 2;
			System.out.println(n1);
			System.out.println(n2);
			for (int i = 0; i < n; i++) {
				System.out.println(i+":"+(n1+n2));
				int var = n1;
				n1 += n2;
				n2 = var;
			}
		}
	}
	public static void main(String[] args) {
		System.out.print("-> ");
		int n = Teclado.leerEntero();
		FibonacciRunnable fr = new FibonacciRunnable(n);
		Thread t1 = new Thread(fr);
		t1.start();
	}
}
