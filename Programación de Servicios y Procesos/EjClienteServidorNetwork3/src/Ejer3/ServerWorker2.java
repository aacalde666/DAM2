package Ejer3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerWorker2 implements Runnable {
	Socket s;
	BufferedReader brCliente;
	Mensaje msg;
	
	public ServerWorker2(Socket s, Mensaje m)
	{this.s=s;
	this.msg=m;
	InputStream is;
	try {
		is = s.getInputStream();
		brCliente = new BufferedReader(new InputStreamReader(is));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	public void run()
	{
		while(true) {
		String contenido=IOUtility.leer(brCliente);
		msg.setMsg(contenido);
		}
		}
		
	}


