package gingermathgame;

import java.util.*;

public class NumberGenerator {
	
	private static Random rdm = new Random();
	
	public static void genNum(ArrayList<Integer> arr) {
		for(int i=1; i<=6; i++) {
			int  n = rdm.nextInt(9) + 1;
			arr.add(n);
		}
		for(int i=1; i<=8; i++) {
			int  n = rdm.nextInt(90) + 10;
			arr.add(n);
		}
		for(int i=1; i<=12; i++) {
			int  n = rdm.nextInt(900) + 100;
			arr.add(n);
		}
		for(int i=1; i<=14; i++) {
			int  n = rdm.nextInt(9000) + 1000;
			arr.add(n);
		}
	}
	
}
