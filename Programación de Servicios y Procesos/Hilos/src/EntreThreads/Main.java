package EntreThreads;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Hijo h = new Hijo();
		Thread t = new Thread(h);
		t.start();
		Thread.sleep(2000);
		t.interrupt();
		t.join();
	}

}
