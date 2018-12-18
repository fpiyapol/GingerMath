import java.io.*;
import java.net.Socket;

public class Player extends Lobby{
	
	protected Socket socket;
	protected BufferedReader in;

	protected PrintWriter out;
	protected Data data;
	protected String act;
	protected Room room;
	
	public String read() throws IOException {
		return in.readLine();
	}
	
	public void write(String message) {
		out.println(message);
	}
	
	public void createData() {
		data = new Data();
	}
	
	public void setName(String name) {
		data.setName(name);
	}
	
	public String getName() {
		return data.getName();
	}
	
	public void setScore(String score) {
		data.setScore(Integer.parseInt(score));
	}
	
	public Data getData() {
		return data;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public void close() throws IOException {
		socket.close();
	}
	
}
