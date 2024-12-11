package Timer;

public class MainTimer {

	public static void main(String[] args) {
		Timer timer = new Timer(0);
		Thread t = new Thread(timer, "contador");
		t.start();
		for (int i = 0; i < 2; i++) {
			new Thread(new Escritor(timer),"Escritor "+i).start();
		}
	}
}
