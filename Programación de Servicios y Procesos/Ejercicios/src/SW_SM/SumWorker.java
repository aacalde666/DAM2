package SW_SM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumWorker {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String l = "";
			int n=0;
			while ((l = br.readLine())!=null) {
				n = Integer.parseInt(l);
				System.out.println(n + 1);
			}
		}
	}
}

