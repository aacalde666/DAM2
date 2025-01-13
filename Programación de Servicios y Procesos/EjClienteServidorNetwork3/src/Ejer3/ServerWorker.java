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

public class ServerWorker implements Runnable {
	Socket s;
	Mensaje msg;

	BufferedWriter bwCliente;

	public ServerWorker(Socket s, Mensaje m) {
		this.msg=m;
		this.s = s;

		try {
			OutputStream os = s.getOutputStream();
			bwCliente = new BufferedWriter(new OutputStreamWriter(os));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		long time=1000;
		
		while(true) {
		IOUtility.escribir(bwCliente, msg.getMsg());
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}

	}


