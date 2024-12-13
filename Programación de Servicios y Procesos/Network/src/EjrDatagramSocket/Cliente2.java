package EjrDatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente2 {
	public static void main(String[] args) {
		try {
			int cont = 0;
			boolean fin = false;
			System.out.println("iniciando c2");

			DatagramSocket send = new DatagramSocket();
			DatagramSocket recive = new DatagramSocket(8888);

			int puntuacion = 0;
			while (!fin) {
				if (puntuacion < 5) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (cont < 10) {
						// Recepción
						byte[] msg = new byte[100];
						DatagramPacket dp = new DatagramPacket(msg, msg.length);
						recive.receive(dp);
						String receivedMsg = new String(dp.getData(), 0, dp.getLength());
						System.out.println("Recibido: " + receivedMsg);

						if (receivedMsg.equals("WIN")) {
							fin = true;
						} else {
							// Envío
							String msg2 = "Pong";
							InetAddress ia = InetAddress.getByName("localhost");
							DatagramPacket dp1 = new DatagramPacket(msg2.getBytes(), msg2.getBytes().length, ia, 8889);
							send.send(dp1);
							System.out.println("Enviado: Pong, Contador: " + cont);
							cont = (int) (Math.random() * 10) + 1;
						}
					} else {
						String msg2 = "FINE";
						InetAddress ia = InetAddress.getByName("localhost");
						DatagramPacket dp1 = new DatagramPacket(msg2.getBytes(), msg2.getBytes().length, ia, 8889);
						send.send(dp1);
						puntuacion++;
						cont = (int) (Math.random() * 10) + 1;
						System.out.println("Puntage c2: "+puntuacion);
					}
				}else {
					String msg2 = "WIN";
					InetAddress ia = InetAddress.getByName("localhost");
					DatagramPacket dp1 = new DatagramPacket(msg2.getBytes(), msg2.getBytes().length, ia, 8889);
					send.send(dp1);
					System.out.println("ganador c2");
					fin=true;
				}
			}

			send.close();
			recive.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
