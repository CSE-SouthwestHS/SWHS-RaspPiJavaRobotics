import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class cli {
	public static void main(String args[]) throws SocketException, UnknownHostException, IOException {

		Socket socket = null;
		SocketAddress socketAddress = null;
		BufferedReader in = null;
		PrintStream out = null;
		int PORT = 5000;
		String IP = "10.55.162.1";
		int x = 0;
		int y = 0;
		boolean ServerOpen = false;
		boolean Reported = false;
		boolean EmittingCoordinates = false;

		try {
			socketAddress = new InetSocketAddress(IP, PORT);
			socket = new Socket(IP, PORT);
			System.out.print("Connecting to Server: " + IP + ":" + PORT + "\n");
			ServerOpen = true;
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.print("Connection Failed \n");
		}
		while(ServerOpen == true){
			try {
				out = new PrintStream(socket.getOutputStream());
				if (Reported == false){
					out.println("Client Connected");
					Reported = true;
				}
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.print("Server Failed \n ");
				System.exit(-1);
			}
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				EmittingCoordinates = true;
			} catch (IOException e) {
				out.println("Signal not received");
				System.exit(-1);
			}
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				if ((line = in.readLine()) != null) {
					sb.append(line);
				}
			} catch (IOException e) {
				System.out.println("Server Closed \n");
				System.exit(-1);
			}
			if (line != null){
				System.out.println(sb.toString());
			}
			while (EmittingCoordinates == true){
				try {
					Thread.sleep(30);
					out.println(x + "," + y);
					x++;
					y++;
				} catch (Exception e) {
					System.out.println("Variable Failed");
				}
			}
		}

		/*try {
			while(true) {
				socket = new Socket(IP, PORT);
				Scanner sc = new Scanner(System.in);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String messageFromServer = in.readLine();
				//System.out(messageFromServer);
				if (messageFromServer == null){
					System.out.println("Server Closed");
					System.exit(0);
				} else {
					System.out.println("enter any string: " + messageFromServer + "\n");
				}
				message = sc.nextLine();
				PrintStream out = new PrintStream(socket.getOutputStream());
				out.println(message);
				System.out.print(message);
			}
		} catch (SocketException e) {
			//e.printStackTrace();
			System.out.print("Connection Failed \n");
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.print("Connection Failed \n");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.print("Server Failed \n ");
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.print("Connection Lost \n");
			}
		}*/
  	}
}
