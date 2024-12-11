package Practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
	/*
	 * Main: se encarga de lanzar los otros procesos, comunicar con ellos 
	 * y de comunicarse con el usuario.
	 * 
	 * DataReader: se encarga de leer un fichero grande con 
	 * una secuencia muy grande de datos dentro (data.dat)
	 * 
	 * NumberCounter: recibe una secuencia de datos y 
	 * cuenta cuantos unos, cuantos dos, cuantos tres… hay en esa secuencia. 
	 * Después los envías al Main en algún formato.
	 * 
	 * El objetivo es que el Main ejecute un DataReader y varios NumberCounter. 
	 * El DataReader leerá una secuencia (grande) de datos,
	 * se la pasará al Main y este la repartirá entre los NumberCounter. 
	 * 
	 * Al final el Main leerá la respuesta de los varios NumberCounter 
	 * y sacará por pantalla la cantidad de unos, dos, tres… del fichero.
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		String local = ".\\src\\Practica\\data.dat";
		//Tamaño del bloque = 1000(100 procesos = 1:49558, 1000 procesos = 1:163440)
//		String local = ".\\src\\Practica\\datos.txt";
		int numProcesos = 100;
        int tamanoBloque = 3500;
        Long time = System.currentTimeMillis();
        System.out.println("Inicio=("+(System.currentTimeMillis() - time)+" m.s)");
        Map<Integer, Integer> resultadoFinal = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            resultadoFinal.put(i, 0);
        }
        Process datReader;
        Process numCounter;
        for (int i = 0; i < numProcesos; i++) {
        	ProcessBuilder DataReader = new ProcessBuilder
        			("java","-cp",".\\bin","Practica.DataReader",local,String.valueOf
        					(i * tamanoBloque),String.valueOf((i+1) * tamanoBloque));
    		datReader = DataReader.start();
    		BufferedReader brDataReader = new BufferedReader
    				(new InputStreamReader(datReader.getInputStream()));
    		ProcessBuilder NumberCounter = new ProcessBuilder
    				("java","-cp",".\\bin","Practica.NumberCounter");
    		numCounter = NumberCounter.start();
    		BufferedWriter bwNumberWriter = new BufferedWriter
    				(new OutputStreamWriter(numCounter.getOutputStream()));
    		String l;
    		while ((l = brDataReader.readLine())!=null) {
    			bwNumberWriter.write(l);
    		}
    		bwNumberWriter.close();
    		BufferedReader brNumberFormat = new BufferedReader
    				(new InputStreamReader(numCounter.getInputStream()));
    		String l1;
    		String result="";
            while ((l1 = brNumberFormat.readLine()) != null) {
                result+=l1+"\n";
                String[] partes = l1.split(":");
                if (partes.length == 2) {
                String part0 = partes[0];
                String part1 = partes[1];
                int numero = Integer.parseInt(part0);
                int valor = Integer.parseInt(part1);
                resultadoFinal.put(numero, resultadoFinal.get(numero) + valor);
                }
            }
            if (i==(numProcesos/2)) {
            	System.out.println("Datos del proceso "+(numProcesos/2)+": \n"+result);
			}
            try {
                datReader.waitFor();
                numCounter.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Termino proceso -> "+(i+1));
		}
        for (int i = 0; i <= 9; i++) {
            System.out.println(i + ":" + resultadoFinal.get(i));
        }
        System.out.println("Final=("+(System.currentTimeMillis() - time)+" m.s)");
	}

}
