import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Lobby {
	
	protected static ArrayList<Player> allPlayers = new ArrayList<>();
	protected static ArrayList<Room> allRooms = new ArrayList<>();
	protected static HashSet<String> playersName = new HashSet<>();
	protected static HashSet<String > used = new HashSet<>();
	protected static ArrayList<String[]> playerRank = new ArrayList<>(); // beware race-condition
	
	private Room room;
	
	public Lobby() {
	}
	
	public void welcomePlayer(Socket socket) {
		new Thread(new PlayerHandler(socket)).start();
	}
	
	public void createRoom(Player player, String roomName) {
//		System.out.println("create room");
		room = new Room();
		room.setRoomName(roomName);
		player.setRoom(room);
		allRooms.add(room);
		room.playerJoin(player);
//		System.out.println("array allRooms size : " + allRooms.size());
	}
	
	public void removeRoom(Room r) {
		allRooms.remove(r);
	}
	
	public void setRank(String name, String sc) {
		//set init
		String [] tmp = {name, sc};
		playerRank.add(tmp);
	}
	
	public void updateRank(String inp) {
		//update and sort global rank
		String pn = inp.split("-")[0];
		String ps = inp.split("-")[1];
		for(String[] str:playerRank) {
			if(str[0].equals(pn)) {
				str[1] = ps;
				break;
			}
		}
		Collections.sort(playerRank, new Comparator<String[]>() {

			@Override
			public int compare(String[] arg0, String[] arg1) {
				return Integer.compare(Integer.parseInt(arg0[1]), Integer.parseInt(arg1[1]));
			}
		});
		
		Collections.reverse(playerRank);
	}
	
	public void checkStatus(String command) {
		if(command.equals("!st")) {
			System.out.println(allRooms.size() + " rooms.");
			System.out.println(allPlayers.size() + " / " + playersName.size() + " players.");
			for(int n = 0; n < allRooms.size(); n++) {
				String sta = "";
				if(allRooms.get(n).getPlaying()) {
					sta = "playing";
				}else {
					sta = "waiting";
				}
				System.out.println("------------------------------");
				System.out.println("#" + n + " " + allRooms.get(n).getRoomName());
				System.out.println("Playing status : " + sta);
				System.out.println("Players : ");
				for(Player pp:allRooms.get(n).getPlayers()) {
					if(pp != null) {
						System.out.println(" * " + pp.getName());						
					}
				}
				System.out.println("------------------------------");
			}
		}else if(command.equals("!ap")) {
			for(int n = 0; n < allPlayers.size(); n++) {
				System.out.println("#" + n + " " + allPlayers.get(n).getName());
			}
		}else if(command.equals("!ar")) {
			for(int n = 0; n < allRooms.size(); n++) {
				String sta = "";
				if(allRooms.get(n).getPlaying()) {
					sta = "playing";
				}else {
					sta = "waiting";
				}
				System.out.println("#" + n + " " + allRooms.get(n) + " | status : " + sta);
			}
		}else if(command.equals("!ex")) {
			try(FileOutputStream fout = new FileOutputStream("data.txt", false);
					PrintWriter pout = new PrintWriter(fout, true);){
				for(String str:playersName) {
					pout.println(str);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			File file = new File("data.txt");
			file.setReadOnly();
			System.exit(0);
		}
		else if(command.equals("!help")) {
			System.out.println("!st : for fully status (Show all rooms and player in the room.)");
			System.out.println("!ar : show all rooms and playing status.");
			System.out.println("!ap : show all player.");
			System.out.println("!ex : exit.");
		}else {
			System.out.println("!help : for help");
		}
	}
	
}
