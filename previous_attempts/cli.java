//we hope to replace this with javascript

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

		String IP = "localhost"; //enter the ip of the computer where the server is hosted, can use localhost
		String message; //initialization of string that will be set from client, then printed
		Socket s1 = null; //initialization of socket variable
		try {
			while(true) {
				s1 = new Socket(IP, 5000); //set ip and port for socket connection
				Scanner sc = new Scanner(System.in); //scanner reads input typed into terminal
				BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream())); //handles input stream from server
				String messageFromServer = in.readLine();

				//the rest handles errors
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
			System.out.print("Connection Failed \n");
		} catch (UnknownHostException e) {
			System.out.print("Connection Failed \n");
		} catch (IOException e) {
			System.out.print("Server Failed \n ");
		} finally {
			try {
				if (s1 != null)
					s1.close();//closes socket connection
			} catch (IOException e) {
				System.out.print("Connection Lost \n");
			}
		}
  	}
}
