import java.util.ArrayList;

public class Room extends Lobby {
	
	private String roomName;
	private Player[] players;
	private int membership;
	protected ArrayList<Integer> num1;
	protected ArrayList<Integer> num2;
	private NumberGenerator generator;
	private Sorter sorter;
	private Data[] leaderboard;
	private String ldb;
	private boolean playing;
	
	public Room() {
		players = new Player[4]; //actually 4
		generator = new NumberGenerator();
		num1 = generator.generateNumber();
		num2 = generator.generateNumber();
		sorter = new Sorter();
		leaderboard = new Data[4]; //actually 4
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void playerJoin(Player player) {
//		System.out.println("player join");
		players[membership] = player;
		membership++;
	}
	
	public void kickPlayer(String name) {
		for(Player player:players) {
			if(player != null && player.getName().equals("name")) {
				player.write("kc -");
				break;
			}
		}
	}
	
	public boolean getPlaying() {
		return playing;
	}
	
	public void roomStart() {
		playing = true;
		new Thread(new GameServerSide(this)).start();
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getMembership() {
		return membership;
	}
	
	public void memberOut(Player player) {
//		System.out.println("member going out");
		for(int i=0; i<4 ; i++) {
			if(players[i] != null && players[i].getName().equals(player.getName())) {
				players[i] = null;
				break;
			}
		}
			
		membership--;
//		System.out.println("mem rmvd " + membership);
		if(membership == 0) {
			allRooms.remove(this);
		}
	}
	
	public void writeToAll(String message) {
		for(Player player:players) {
			if(player != null && !player.getName().equals("")) {
				player.write(message);
				//Ghost task
//				String ghostTask = "";
//				ghostTask += player.getName() + " " + message;
				System.out.println("said to : " + player.getName() + " " + message);
			}
		}
	}
	
	public void updateLeaderboard() {
		for(int i = 0; i< 4; i++) {
			//actually 4
			if(players[i] != null) {
				leaderboard[i] = players[i].getData();				
			}
			else if(players[i] == null) {
				//create ghost
				Player ghost = new PlayerHandler();
				ghost.createData();
				ghost.setName("");
				ghost.setScore("0");
				players[i] = ghost;
				leaderboard[i] = ghost.getData();
			}
		}
		sorter.sort(leaderboard);
		
	}
	
	public String getLeaderboard() {
		updateLeaderboard();
		ldb = "";
		ldb += "[";
		for(Data l:leaderboard) {
			ldb += l.toString() + " ";
		}
		ldb = ldb.substring(0, ldb.length()-1);
		ldb += "]";
		return ldb;
	}
}
