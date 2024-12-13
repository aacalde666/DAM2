package EjrDatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente1 {
	public static void main(String[] args) {
		try {
			int cont = 0;
			boolean fin = false;
			System.out.println("iniciando c1");

			DatagramSocket send = new DatagramSocket();
			DatagramSocket receive = new DatagramSocket(8889);

			int puntuacion = 0;
			while (!fin) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (puntuacion < 5) {
					if (cont < 10) {
						// Envío
						String msg = "Ping";
						InetAddress ia = InetAddress.getByName("localhost");
						DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 8888);
						send.send(dp);
						System.out.println("Enviado: Ping, Contador: " + cont);

						// Recepción
						byte[] msg2 = new byte[100];
						DatagramPacket dp1 = new DatagramPacket(msg2, msg2.length);
						receive.receive(dp1);
						String receivedMsg = new String(dp1.getData(), 0, dp1.getLength());
						System.out.println("Recibido: " + receivedMsg);

						if (receivedMsg.equals("WIN")) {
							fin = true;
						} else {
							cont = (int) (Math.random() * 10) + 1;
						}
					} else {
						String msg = "FINE";
						InetAddress ia = InetAddress.getByName("localhost");
						DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 8888);
						send.send(dp);
						cont = (int) (Math.random() * 10) + 1;
						puntuacion++;
						System.out.println("Puntage c1: " + puntuacion);
					}
				} else {
					String msg = "WIN";
					InetAddress ia = InetAddress.getByName("localhost");
					DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 8888);
					send.send(dp);
					System.out.println("ganador c1");
					fin = true;
				}
			}

			send.close();
			receive.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
