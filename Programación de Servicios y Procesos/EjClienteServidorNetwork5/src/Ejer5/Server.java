package Ejer5;

public class Server {
	public static void main(String[] args) {
		new Thread(new ServerWorkerLectura()).start();
	}
		

}
