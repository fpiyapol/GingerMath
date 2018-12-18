import java.io.*;
import java.net.Socket;

public class PlayerHandler extends Player implements Runnable{
	
	private String packet;
	private String head;
	private String tail;
	
	public PlayerHandler() { }
	
	public PlayerHandler(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			data = new Data();
			System.out.println("new Player connect : " + socket.toString());
		}catch (Exception e) {
			System.out.println("PlayerHandler error : " + e);
		}
	}
	
	public void run() {
		try {
			while(true) {
				packet = read();
				head = packet.split(" ")[0];
				tail = packet.split(" ")[1];
				System.out.println("what's coming : " + head + " " + tail);
				if(head.equals("name")) {
//					setName(tail);
					if(playersName.contains(tail)) {
						write("dup");
					}else {
						playersName.add(tail);
						setName(tail);
						write("acc");
					}
				}else if(head.equals("cr")) {
					createRoom(this, tail);
				}else if(head.equals("upr")) {
					updateRank(tail);
//					for(String[] str:playerRank) {
//						if(str[0].equals(getName())){
//							out.println("yrr " + str[1]);
//							break;
//						}
//					}
				}
				else if(head.equals("rqr")) {
					for(String[] str:playerRank) {
						if(str[0].equals(getName())){
							out.println("yrr " + playerRank.indexOf(str));
							break;
						}
					}
				}
				else if(head.equals("jn")) {
					for(Room r:allRooms) {
						if(r.getRoomName().equals(tail)) {
							r.playerJoin(this);
							setRoom(r);
							break;
						}
					}
				}
				else if(head.equals("ru")) {
					for(Room r:allRooms) {
						if(r.getRoomName().equals(tail)) {
							String names = "lp ";
							for(Player player:r.getPlayers()) {
								if(player != null) {
//									System.out.println(player.getName());
									names += player.getName() + "-";									
								}
							}
							names = names.substring(0, names.length()-1);
							System.out.println(names);
							out.println(names);
							break;
						}
					}
				}else if(head.equals("oldname")){
					setName(tail);
					if(!used.contains(getName())) {
						used.add(getName());
						setRank(getName(), "0");
					}
				}
				else if(head.equals("bk")) {
					room.memberOut(this);
					setRoom(null);
				}else if(head.equals("kc")) {
					room.kickPlayer(tail);
				}
				else if(head.equals("ps")) {
						room.writeToAll("ps -");
				}else if(head.equals("st")) {
//					System.out.println("host push start");
				//	room.writeToAll("st -");
					room.roomStart();
				}
				else if(head.equals("score")) {
					setScore(tail);
				}
				else if(head.equals("on")) {
					String listRoom = "";
					for(Room r:allRooms) {
						if(!r.getPlaying()) {
							listRoom += r.getRoomName() + "-";							
						}
					}
					if(!listRoom.equals("")) {
						listRoom = listRoom.substring(0, listRoom.length()-1);
					}
					write(listRoom);						
				}else if(head.equals("done")) {
					break;
				}
			}
		}catch (Exception e) {
			System.out.println("PlayerHandler run error : " + e);
		}finally {
			try {
//				out();
				if(room != null) {
					room.memberOut(this);					
				}
//				allPlayers.remove(this);
				close();
			}catch (Exception e) {
				System.out.println("PlayerHandler run close error : " + e);
			}
		}
	}
}
