package logica;

public class Main {

	public static void main(String[] args) {
		 CuentaBancaria cuenta = new CuentaBancaria(1000);  // Cuenta con saldo inicial de 1000

	        Thread persona1 = new Thread(new Persona(cuenta, false, 500), "Retirador1");
	        Thread persona2 = new Thread(new Persona(cuenta, false, 700), "Retirador2");
	        Thread persona3 = new Thread(new Persona(cuenta, true, 300), "Depositante1");
	        Thread persona4 = new Thread(new Persona(cuenta, true, 200), "Depositante2");

	        persona1.start();
	        persona2.start();
	        persona3.start();
	        persona4.start();
	}

}
