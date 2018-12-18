public class GameServerSide extends Room implements Runnable {
	
	private Room room;
	private Timer timer;
	private int second = -1;
	
	public GameServerSide(Room room) {
		this.room = room;
		timer = new Timer();
	}
	
	public void run() {
		new Thread(timer).start();
		while(true) {
			room.writeToAll("st -");
			for(int n:num1) {
				room.writeToAll(n + "");
			}
			for(int n:num2) {
				room.writeToAll(n + "");
			}
			break;
		}
		while(true) {
			second = timer.getTime();
			if(second != 0) {
				room.writeToAll(room.getLeaderboard() + ", " + second);
			}else {
				room.writeToAll(room.getLeaderboard() + ", " + second);
				break;
			}
		}
	}
}
