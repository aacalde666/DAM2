package DosVariables;

public class N implements Runnable{
	private int c1;
	private int c2;
	private Object matrix = new Object();
	private Object matrix1 = new Object();
	public static void main(String[] args) throws InterruptedException {
		Thread t = null;
		N n = new N();
		for (int i = 0; i < 5; i++) {
			t = new Thread(n);
			t.start();
		}
		t.join();
		System.out.println("valor final de c1: "+n.c1+" y c2: "+n.c2);
	}

	@Override
	public void run() {
		s();
	}
	private void s() {
		for (int i = 0; i < 1000; i++) {
			inc1();
			inc2();
		}
	}
	private void inc1() {
		synchronized (matrix) {
			c1++;
		}
	}
	private void inc2() {
		synchronized (matrix1) {
			c2++;
		}
	}
}
