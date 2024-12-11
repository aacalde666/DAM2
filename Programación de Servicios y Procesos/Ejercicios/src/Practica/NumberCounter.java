package Practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class NumberCounter {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            countMap.put(i, 0);
        }
        while ((inputData = br.readLine()) != null) {
            for (char c : inputData.toCharArray()) {
                if (Character.isDigit(c)) {
                    int num = Character.getNumericValue(c);
                    if (countMap.containsKey(num)) {
                        countMap.put(num, countMap.get(num) + 1);
                    }
                }
            }
        }
        String n="";
        for (int i = 0; i <= 9; i++) {
        	n+=i + ":" + countMap.get(i)+"\n";
        }
        System.out.println(n);
        br.close();
	}
}
