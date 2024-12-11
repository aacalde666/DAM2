package EntreThreads;

public class Hijo extends Thread{

	@Override
	public void run() {
		System.out.println("hijo empezo");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println("Interrumpido");
		}
		if (!Thread.interrupted()) {
			System.err.println("Interrumpido2");
		}else {
			for (int i = 0; i < 50; i++) {
				System.out.println("Click");
			}
		}
		System.out.println("hijo termino");
	}
	
}
