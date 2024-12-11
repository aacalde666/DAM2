package ejer2;

import java.util.LinkedList;
import java.util.Queue;

/*
* 4 puntos
*En este programa, se simula el funcionamiento de una empresa.
*Hay una solo supervisor.
*Existen 2 manager. 
*Algunos empleados trabajan para un manager y otros para el otro.
*Los dos manager son supervisionados por el mismo supervisor.
*Los dos manager no pueden reunirse a la vez con el supervisor.
*Los empleados, hacen informes de sus trabajos y se los entregan a los correspondientes manager.
*
*En el programa no se ha sincronizado nada. Si lo considera necesario, añade el código necesario para la sincronización.
*Además, escribe en este mismo comentario, qué has añadido y por qué.
*
*Cambios:
*	- En la clase Empleado le e puesto un bloque synchronized relacionado con miManager en el metodo run encerrando el for
*/

class Manager implements Runnable {
	private Queue<String> informes = new LinkedList<>();
	Object supervisor;

	public Manager(Object supervisor) {
		this.supervisor = supervisor;
	}

	public void agregarInforme(String informe, String empleado) {
		synchronized(informe) {
			informes.add(informe);
			informes.notifyAll();
		}
		System.out.println(empleado + " entregó a su manager un informe: " + informe);
	}

	public void run() {
		while (true) {
			String informe = null;
			synchronized(informes) {
				if (!informes.isEmpty()) {//------estos dos es una operacion ato
					informe = informes.poll();//--|
				}
			}
			if (informe!=null) {
				synchronized (supervisor) {
					System.out.println("El manager se reune con el supervisor para hablar de: " + informe);
					try {
						Thread.sleep(2000); // Simula el tiempo para leer un informe
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			} else {
				System.out.println(" no hay informes sobre qué hablar");
				try {
					Thread.sleep(20); // Simula el tiempo que espera antes de mirar si hay informes
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Empleado implements Runnable {
	private String nombre;
	private Manager miManager;

	public Empleado(String nombre, Manager manager) {
		this.nombre = nombre;
		this.miManager = manager;
	}

	@Override
	public void run() {
			// Simula el envío de informes por parte de los empleados
		for (int i = 1; i <= 50; i++) {
			String informe = nombre + "-Informe-" + i;
			miManager.agregarInforme(informe, nombre);
			try {
				Thread.sleep(1000); // Simula el tiempo entre envío de informes
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Dos {
	public static void main(String[] args) {
		// un supervisor
		Object supervisor = new Object();

		// dos managers
		Manager manager1 = new Manager(supervisor);
		Manager manager2 = new Manager(supervisor);

		// varios empleados
		Thread empleado1 = new Thread(new Empleado("empleado1", manager1));
		Thread empleado2 = new Thread(new Empleado("empleado2", manager1));
		Thread empleado3 = new Thread(new Empleado("empleado3", manager2));
		Thread empleado4 = new Thread(new Empleado("empleado4", manager2));

		// lanzo los threads
		empleado1.start();
		empleado2.start();
		empleado3.start();
		empleado4.start();
		new Thread(manager1).start();
		new Thread(manager2).start();
	}
}
