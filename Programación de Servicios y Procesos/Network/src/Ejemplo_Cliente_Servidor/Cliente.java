package Ejemplo_Cliente_Servidor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		Socket s=new Socket();
		InetSocketAddress isa=new InetSocketAddress("localhost", 9999);
		try {
			s.connect(isa) ;
			OutputStream os=s . getOutputStream() ;
			OutputStreamWriter osw=new OutputStreamWriter(os);
			osw.write("Hooola");
			osw.flush();
			os.close();
			s.close();
		}catch (IOException e){
			e . printStackTrace(); 
		}
	}
}
