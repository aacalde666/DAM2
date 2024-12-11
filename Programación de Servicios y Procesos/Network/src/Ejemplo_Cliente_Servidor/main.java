package Ejemplo_Cliente_Servidor;

public class main {
	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			new Thread((Runnable) new Cliente());
		}
	}
}
