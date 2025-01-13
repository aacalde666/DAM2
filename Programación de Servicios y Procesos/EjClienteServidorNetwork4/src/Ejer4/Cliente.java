package Ejer4;

public class Cliente {

	public static void main(String[] args) {
		new Thread(new ClienteWorkerEscritura()).start();
	}

}
