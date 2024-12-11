package Ej1;

public class ej1 {

	public static void main(String[] args) {
		long a =  Integer.parseInt(args[0]);
		long b = Integer.parseInt(args[1]);
		Long time=Long.parseLong(args[2]);
		if (time==01) {
			time=System.currentTimeMillis();
		}
		long result = 0;
//		if (a>b) {
//			int temp = a;
//			a = b;
//			b = temp;
//		}
		for (long i = a; i <= b; i++) {
//			int r = result;
			result += i;
//			System.out.println("-> "+ i+" + "+r+" = " + result);
		}
		System.out.println("->" +result);
		System.out.println("("+(System.currentTimeMillis() - time)+" m.s)");
		
	}

}
