import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class MainServer {
	private static ServerSocket serverSocket;
	private static InetAddress serverAddress;
	private static Lobby lobby;
	private static Scanner sc;
	private static String cmd;
	
	public static void main(String[] args) {
		try {
//			serverAddress = InetAddress.getByName("104.248.154.68");
			serverAddress = InetAddress.getByName(args[0]);
			serverSocket = new ServerSocket(8910, 10, serverAddress);
			
			System.out.println("\r\n" + 
					"  /$$$$$$  /$$                                               /$$      /$$             /$$     /$$      \r\n" + 
					" /$$__  $$|__/                                              | $$$    /$$$            | $$    | $$      \r\n" + 
					"| $$  \\__/ /$$ /$$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$       | $$$$  /$$$$  /$$$$$$  /$$$$$$  | $$$$$$$ \r\n" + 
					"| $$ /$$$$| $$| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$      | $$ $$/$$ $$ |____  $$|_  $$_/  | $$__  $$\r\n" + 
					"| $$|_  $$| $$| $$  \\ $$| $$  \\ $$| $$$$$$$$| $$  \\__/      | $$  $$$| $$  /$$$$$$$  | $$    | $$  \\ $$\r\n" + 
					"| $$  \\ $$| $$| $$  | $$| $$  | $$| $$_____/| $$            | $$\\  $ | $$ /$$__  $$  | $$ /$$| $$  | $$\r\n" + 
					"|  $$$$$$/| $$| $$  | $$|  $$$$$$$|  $$$$$$$| $$            | $$ \\/  | $$|  $$$$$$$  |  $$$$/| $$  | $$\r\n" + 
					" \\______/ |__/|__/  |__/ \\____  $$ \\_______/|__/            |__/     |__/ \\_______/   \\___/  |__/  |__/\r\n" + 
					"                         /$$  \\ $$                                                                     \r\n" + 
					"                        |  $$$$$$/                                                                     \r\n" + 
					"                         \\______/                                                                      \r\n" + 
					"                    /$$$$$$  /$$$$$$$$ /$$$$$$$  /$$    /$$ /$$$$$$$$ /$$$$$$$                         \r\n" + 
					"                   /$$__  $$| $$_____/| $$__  $$| $$   | $$| $$_____/| $$__  $$                        \r\n" + 
					"                  | $$  \\__/| $$      | $$  \\ $$| $$   | $$| $$      | $$  \\ $$                        \r\n" + 
					"                  |  $$$$$$ | $$$$$   | $$$$$$$/|  $$ / $$/| $$$$$   | $$$$$$$/                        \r\n" + 
					"                   \\____  $$| $$__/   | $$__  $$ \\  $$ $$/ | $$__/   | $$__  $$                        \r\n" + 
					"                   /$$  \\ $$| $$      | $$  \\ $$  \\  $$$/  | $$      | $$  \\ $$                        \r\n" + 
					"                  |  $$$$$$/| $$$$$$$$| $$  | $$   \\  $/   | $$$$$$$$| $$  | $$                        \r\n" + 
					"                   \\______/ |________/|__/  |__/    \\_/    |________/|__/  |__/                        \r\n" + 
					"                                                                                                       \r\n" + 
					"                                                                                                       \r\n" + 
					"                                                                                                       \r\n" + 
					"");
			
			System.out.println("SERVER RUNNING...  " + serverSocket.toString());
			lobby = new Lobby();
			
			sc = new Scanner(System.in);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							lobby.welcomePlayer(serverSocket.accept());
						} catch (IOException e) {
							System.out.println("Server running error : " + e);
						}
					}					
				}
			}).start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						String command = sc.nextLine();
						lobby.checkStatus(command);
					}
					
				}
			}).start();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
