package Ejemplo_Cliente_Servidor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			System. out.println ("Creando Socket Servidor");
			ServerSocket ss = new ServerSocket();
			System.out.println("Bind") ;
			InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
			ss.bind(addr);
			System.out.println("accept");
			while (true) {
				Socket nsc = ss.accept();
				System. out.println ("conexión recibida");
				InputStream is = nsc.getInputStream();
				OutputStream os = nsc. getOutputStream();
				byte[] mensaje = new byte [25];
				is.read (mensaje);
				System.out.println("recibido:" + new String (mensaje));
				System. out.println ("closet");
				nsc.close();
				ss.close();
				System.out.println("Terminado");
			}
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}
}
