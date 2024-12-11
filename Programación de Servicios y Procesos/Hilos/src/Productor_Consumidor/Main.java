package Productor_Consumidor;

public class Main {
	public static void main(String[] args) {
        Productos productos = new Productos();
        Productor productor = new Productor(productos);
        productor.start();
        for (int i = 0; i < 5; i++) {
            Consumidor consumidor = new Consumidor(productos);
            consumidor.start();
        }
    }
}
