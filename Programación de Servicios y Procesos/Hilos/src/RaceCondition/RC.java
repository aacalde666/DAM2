package RaceCondition;

public class RC implements Runnable {
	public static long x = 0;

	public static void main(String[] args) throws InterruptedException {
		RC rc = new RC();
		Thread t = new Thread(rc);
		t.start();
		
		Thread t2 = new Thread(rc);
		t2.start();

		t.join();
		t2.join();
		System.out.println("Total:" + x);
	}

	public void run() {
		actualizarX();
	}
	public synchronized void actualizarX() {
		for (int i = 0; i < 10000; i++) {
			x++;
		}
	}
}
