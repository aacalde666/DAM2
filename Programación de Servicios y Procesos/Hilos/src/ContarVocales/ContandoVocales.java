package ContarVocales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;

public class ContandoVocales implements Runnable{
	private static File f = new File("vocales");
	private  char caracter;
	private ContadorHilo ch;
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String t = "";
		String l;
		while ((l = br.readLine())!=null) {
			t+=l;
		}
		br.close();
		ContadorHilo ch = new ContadorHilo(t);
		ArrayList<Thread> tl = new ArrayList<Thread>();
		tl.add(new Thread(new ContandoVocales('a',ch)));
		tl.add(new Thread(new ContandoVocales('e',ch)));
		tl.add(new Thread(new ContandoVocales('i',ch)));
		tl.add(new Thread(new ContandoVocales('o',ch)));
		tl.add(new Thread(new ContandoVocales('u',ch)));
		for (Thread t1 : tl) {
			t1.start();
		}
		for (Thread t1 : tl) {
			t1.join();
		}
		System.out.println("hay "+ch.get()+" vocales");
	}
	@Override
	public void run() {
		for (int i = 0; i < ch.getText().length(); i++) {
			if (ch.getText().charAt(i)==caracter) {
				ch.add();
			}
		}
	}
	public ContandoVocales(char numVocales, ContadorHilo ch) {
		super();
		this.caracter = numVocales;
		this.ch = ch;
	}
	
	
}
