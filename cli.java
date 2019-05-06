
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
public class cli {
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		while(true) {
		String bundle;	
		Scanner sc=new Scanner(System.in);
		Socket s1 = new Socket("10.55.168.34", 1341);
		Scanner sc1 = new Scanner(s1.getInputStream());
		System.out.println("enter any string: ");
		bundle = sc.nextLine();
		PrintStream p1= new PrintStream(s1.getOutputStream());
		p1.println(bundle);
		System.out.print(bundle); 
		}    //fully working one way
		
		/*while(true) {
			
			String bundle;	
			Scanner sc=new Scanner(System.in);
			Socket s1 = new Socket("10.55.170.99", 1341);
			BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            String messageFromServer = in.readLine();
            //System.out(messageFromServer);
			System.out.println("enter any string: " + messageFromServer + "\n");
			bundle = sc.nextLine();
			PrintStream p1= new PrintStream(s1.getOutputStream());
			p1.println(bundle);
			System.out.print(bundle); 
		}*/
		
		/*try {
	        socket = new Socket("192.168.160.1", 1341);
	    } catch (UnknownHostException e1) {
	        // TODO Auto-generated catch block
	        System.out.println(e1);
	    } catch (IOException e1) {
	        // TODO Auto-generated catch block
	    	System.out.println(e1);
	    }

	    Thread clientThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                // attach to socket's output stream with auto flush turned on
	                //Send message to the server
	                out = new PrintWriter(socket.getOutputStream(), true);

	                //Get return message from server
	                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                messageFromServer = in.readLine();
	                whileChatting(messageFromServer);
	            } catch (Exception e) {
	            	System.out.println(e);
	            }
	        }
	    });
	    clientThread.start();
	

	private void whileChatting(String messageFromServer) {
		System.out.println(messageFromServer);
	    System.out.println("Message from server to client " + messageFromServer);
	}

	public static void Send(String msg) {
	    out.println(name + " : " + msg);
	    System.out.println(name +  " : " + msg + "\n");
	}
	protected static void showMessage(final String message) {
	    SwingUtilities.invokeLater( 
	    new Runnable(){
	        public void run(){
	        	System.out.println(message);
	        }
	    });	
		
	};*/
  }
}
