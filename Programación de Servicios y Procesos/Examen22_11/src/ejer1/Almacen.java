package ejer1;

import java.util.ArrayList;

public class Almacen {
	private ArrayList<Integer> almacenPrincipal;
	private ArrayList<Integer> pares;
	private ArrayList<Integer> impares;
	int capacidadAlmacen;

	public Almacen(int capacidad) {
		almacenPrincipal = new ArrayList<Integer>();
		pares = new ArrayList<Integer>();
		impares = new ArrayList<Integer>();
		capacidadAlmacen = capacidad;
	}
	
	public Almacen() {
	}

	public synchronized void insertaAlmacenPrincipal(int n) {
        // Espera si el almacenPrincipal está lleno al 90%
        while (almacenPrincipal.size() >= capacidadAlmacen * 0.9) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        almacenPrincipal.add(n);
        notifyAll(); // Notifica a los clasificadores
    }

    public synchronized int extraeAlmacenPrincipal() {
        // Espera si el almacenPrincipal está vacío
        while (almacenPrincipal.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int n = almacenPrincipal.remove(0); // Extrae el primer elemento
        notifyAll(); // Notifica al productor si está esperando
        return n;
    }

    public synchronized void insertaPares(int n) {
        pares.add(n);
        notifyAll(); // Notifica a los consumidores de pares
    }

    public synchronized int extraePares() {
        // Espera si no hay pares disponibles
        while (pares.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return pares.remove(0); // Extrae el primer elemento
    }

    public synchronized void insertaImpares(int n) {
        impares.add(n);
        notifyAll(); // Notifica a los consumidores de impares
    }

    public synchronized int extraeImpares() {
        // Espera si no hay impares disponibles
        while (impares.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return impares.remove(0); // Extrae el primer elemento
    }

    public synchronized boolean esAlmacenPrincipalPorDebajoDel30() {
        return almacenPrincipal.size() < capacidadAlmacen * 0.3;
    }

}
