package Ejer3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTeclado implements Runnable {

	Socket socket;
	public ClienteTeclado(Socket s)
	{
		this.socket=s;
	}
	public void run() {
		Scanner scan=new Scanner(System.in);
		try {
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
			String msg=scan.nextLine();
			IOUtility.escribir(bw, msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
