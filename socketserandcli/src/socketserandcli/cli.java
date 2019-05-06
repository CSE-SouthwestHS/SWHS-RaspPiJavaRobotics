package socketserandcli;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;


public class cli {
	private void connectToServer() {

	    try {
	        socket = new Socket("localhost", 4444);
	    } catch (UnknownHostException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    } catch (IOException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }

	    Thread clientThread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                // attach to socket's output stream with auto flush turned on
	                //Send message to the server
	                out = new PrintWriter(socket.getOutputStream(),
	                        true);

	                //Get return message from server
	                in = new BufferedReader(new InputStreamReader(
	                        socket.getInputStream()));
	                messageFromServer = in.readLine();
	                whileChatting(messageFromServer);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	    clientThread.start();
	}

	private void whileChatting(String messageFromServer) {
	    showMessage(messageFromServer);
	    System.out.println("Message from server to client " + messageFromServer);
	}

	public static void Send(String msg) {
	    out.println(name + " : " + msg);
	    showMessage(name +  " : " + msg + "\n");
	}
	protected static void showMessage(final String message) {
	    SwingUtilities.invokeLater( 
	    new Runnable(){
	        public void run(){
	            Gui.consoleTextArea.append(message);
	        }
	    });
	}
}
