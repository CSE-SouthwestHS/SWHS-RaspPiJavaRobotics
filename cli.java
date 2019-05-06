import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.SocketException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class cli {
	public static void main(String args[]) throws SocketException, UnknownHostException, IOException {

		String message;
		Socket s1 = null;
		try {
			while(true) {
				s1 = new Socket("127.0.0.1", 5000);
				Scanner sc = new Scanner(System.in);
				BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
				String messageFromServer = in.readLine();
				//System.out(messageFromServer);
				if (messageFromServer == null){
					System.out.println("Server Closed");
					System.exit(0);
				} else {
					System.out.println("enter any string: " + messageFromServer + "\n");
				}
				message = sc.nextLine();
				PrintStream p1 = new PrintStream(s1.getOutputStream());
				p1.println(message);
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
				if (s1 != null)
					s1.close();
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.print("Connection Lost \n");
			}
		}
  	}
}