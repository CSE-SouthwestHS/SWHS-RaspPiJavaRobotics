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
		new Thread(new SimpleServer()).start();
	}
	static class SimpleServer implements Runnable {
		public void run(){
			ServerSocket socketServer = null;
			Socket socket = null;
			String str;
			String message;
			BufferedReader in = null;
			PrintWriter out = null;
			int PORT = 5000;
			int USERS = 0;
			try {
				socketServer = new ServerSocket(PORT);
				System.out.println("Listening on Port: " + PORT);

				while(true){
					try{
						Socket serverSession = socketServer.accept();
						Scanner socketScanner = new Scanner(System.in);
						in = new BufferedReader(new InputStreamReader(serverSession.getInputStream()));
						out = new PrintWriter(serverSession.getOutputStream(), true);
						System.out.println("Client Connected");
						System.out.println("Enter any string: ");
						message = socketScanner.nextLine();
						out.println(message);
						str = in.readLine();
						System.out.println(str);
					} catch(IOException e){
						//e.printStackTrace();
						System.out.print("Session Ended\n");
						USERS = 0;
					}

				}
			} catch (IOException e1) {
				//e1.printStackTrace();
				System.out.print("Server Active \n");
			} finally {
				try {
					if (socketServer != null) {
						socketServer.close();
					}
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.print("Can't Create Server \n");
				}
			}
		}
	}
}
