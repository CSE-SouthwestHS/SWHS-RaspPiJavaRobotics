

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


public class simplereceiveser {
	
	public static void main(String[] args) throws IOException  {
		ServerSocket s = new ServerSocket(1341);
		
		while(true){
		Socket ss=s.accept();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		//PrintWriter out = new PrintWriter(ss.getOutputStream());
		String str = in.readLine();
		System.out.println(str); }
		
		/*String str;
		String bundle;
		ServerSocket s; 
		s = new ServerSocket(1341);
		Socket ss;
		
		while(true){
			
		
		PrintWriter out;
		ss = s.accept();
		System.out.println("connected \n");
		BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
		Scanner sc=new Scanner(System.in);
		out = new PrintWriter(ss.getOutputStream(), true);
		System.out.println("enter any string: ");
		bundle = sc.nextLine();
		out.println(bundle);
		str = in.readLine();
		System.out.println(str);
		}*/
			/*try {
		        serverSocket = new ServerSocket(1341);
		        System.out.println("Waiting for Clients " + " \n");
	
		        //Reading message from the client
		        socket = serverSocket.accept();
	
		        textArea.append("Client Connected " + "\n");
	
		        //Send message to client 
		        //out = new PrintWriter(socket.getOutputStream());
		        out = new PrintWriter(socket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        while (true)
		        {        
	
		            messageFromClient = in.readLine();
		            whileChat(messageFromClient); 
		        }
		    } catch(IOException ioExecption) {
		        ioExecption.printStackTrace();
		    }
		
	
		private void whileChat(String messageFromClient) {
		    showMessage(messageFromClient);
		    System.out.println("Message from client : " + messageFromClient);
		}
	
		protected static void showMessage(final String message) {
		    SwingUtilities.invokeLater( 
		    new Runnable(){
		        public void run()
		        {
		            System.out.println(message + "\n");
		        }
		    });
		}
	
		public static void sendMessage(String message) {
		    out.println(message);
		    System.out.println(name +  " : " + message + "\n");
		}*/		
		
		
		
		
	}

	

}
