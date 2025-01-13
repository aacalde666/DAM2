package Ejer3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class LadoClient {

	public static void main(String[] args) {
		
		Socket s= new Socket();
		InetSocketAddress isa=new InetSocketAddress("localhost",9999);
		try {
			System.out.println("CLIENTE:INICIADO");
			s.connect(isa);
			
			Thread ct= new Thread(new ClienteTeclado(s));
			Thread cp= new Thread(new ClientePantalla(s));
			ct.start();
			cp.start();
			
			
			

		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
