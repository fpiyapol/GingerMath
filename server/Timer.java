public class Timer implements Runnable {
	
	private int second = -1;
	
	public int getTime() {
		return second;
	}
	
	public void run() {
		for(int s = 63; s >= 0; s--) {
			try {
				second = s;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Timer error : " + e);			
			}
		}
	}
}
