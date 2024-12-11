package PruebaRuntime;

import java.io.IOException;

public class P1 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		try {
			Process p = r.exec("notepad.exe");
			System.out.println("Esta funcionando? = "+p.isAlive());
			Thread.sleep(1000);
			p.destroy();
			System.out.println("Esta funcionando? = "+p.isAlive());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

}
