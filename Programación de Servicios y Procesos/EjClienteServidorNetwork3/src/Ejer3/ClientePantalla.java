package Ejer3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientePantalla implements Runnable{
	Socket socket;
	public ClientePantalla(Socket s)
	{
		this.socket=s;
	}
	public void run() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true)
			{
				IOUtility.leer(br);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
