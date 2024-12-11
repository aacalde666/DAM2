package logica;

public class CuentaBancaria {
	private double saldo = 0;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }
    public synchronized void retirar(double cantidad) throws InterruptedException {
        while (cantidad > saldo) {
            System.out.println(Thread.currentThread().getName() + " espera porque el saldo es insuficiente.");
            wait();
        }
        saldo -= cantidad;
        System.out.println(Thread.currentThread().getName() + " retiró " + cantidad + ". Saldo actual: " + saldo);
    }
    public synchronized void depositar(double cantidad) {
        saldo += cantidad;
        System.out.println(Thread.currentThread().getName() + " depositó " + cantidad + ". Saldo actual: " + saldo);
        notifyAll();
    }
    public synchronized double obtenerSaldo() {
        return saldo;
    }
}
