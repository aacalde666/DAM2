package logica;

public class Persona implements Runnable{private CuentaBancaria cuenta;
	private boolean esDepositor;
	private double cantidad;

	public Persona(CuentaBancaria cuenta, boolean esDepositor, double cantidad) {
	    this.cuenta = cuenta;
	    this.esDepositor = esDepositor;
	    this.cantidad = cantidad;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
		        if (esDepositor) {
		        	Thread.sleep(1500);
		            cuenta.depositar(cantidad);
		        } else {
		        	Thread.sleep(100);
		            cuenta.retirar(cantidad);
		        }
		        
		    } catch (InterruptedException e) {
		        Thread.currentThread().interrupt();
		    }
		}
	    
	}
}
