import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ser {

	public static void main(String[] args) throws IOException, InterruptedException {
		new Thread(new SimpleServer()).start(); //starts server
	}
	static class SimpleServer implements Runnable {

		public void run(){

			ServerSocket socketServer = null;
			Socket socket = null;
			String str = null;
			String message = null;
			BufferedReader in = null;
			PrintWriter out = null;
			int PORT = 5000;
			boolean Connected = false;
			boolean Reported = false;

			try { //if cant make server doesnt break, just logs errors, must be in server
				socketServer = new ServerSocket(PORT); //set up server
				System.out.println("Listening on Port: " + PORT);
				Connected = true;

				while(true){
					try {
						if (str == null){
							Socket serverSession = socketServer.accept();
							Scanner socketScanner = new Scanner(System.in); //scanner reads input typed into terminal
							in = new BufferedReader(new InputStreamReader(serverSession.getInputStream())); //handles input stream from client
							out = new PrintWriter(serverSession.getOutputStream(), true); // sets up sending string to client
							if (Connected == true && Reported == false) {
								System.out.println("Client Connected \n");
								Reported = true;
							}
							System.out.println("Enter any string: ");
							message = socketScanner.nextLine();
							out.println(message); //pushes string out to client
							System.out.println("\nWaiting for client...");
							str = in.readLine();
							System.out.println(str + "\n");
							str = null;
						}
			//the rest handles errors
					} catch(IOException e){
						System.out.print("Session Ended - - - - - - - - - -\n");
						Reported = false;
					}


				}
			} catch (IOException e1) {
				System.out.print("Server Active \n");
			} finally {
				try {
					if (socketServer != null) {
						socketServer.close();
					}
				} catch (IOException e) {
					System.out.print("Can't Create Server \n");
				}
			}
		}
	}
}
