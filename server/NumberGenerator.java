import java.util.ArrayList;
import java.util.Random;

public class NumberGenerator {
	private Random rdm;
	private ArrayList<Integer> numSet;
	
	public NumberGenerator() {
		numSet = new ArrayList<>();
		rdm = new Random();
	}
	
	public ArrayList<Integer> generateNumber(){
		for(int i=1; i<=6; i++) {
			int  n = rdm.nextInt(10) + 1;
			numSet.add(n);
		}
		for(int i=1; i<=4; i++) {
			int  n = rdm.nextInt(41) + 10;
			numSet.add(n);
		}
		for(int i=1; i<=4; i++) {
			int  n = rdm.nextInt(61) + 40;
			numSet.add(n);
		}
		for(int i=1; i<=12; i++) {
			int  n = rdm.nextInt(901) + 100;
			numSet.add(n);
		}
		for(int i=1; i<=14; i++) {
			int  n = rdm.nextInt(9001) + 1000;
			numSet.add(n);
		}
		return numSet;
	}
}
