package socketserandcli;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;


public class ser {
	private void serverStart(){
	    textArea.append("Starting server " + " \n");

	    try {
	        ServerSocket serverSocket = new ServerSocket(4444);
	        textArea.append("Waiting for Clients " + " \n");

	        //Reading message from the client
	        socket = serverSocket.accept();

	        textArea.append("Client Connected " + "\n");

	        //Send message to client 
	        out = new PrintWriter(socket.getOutputStream());
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        while (true)
	        {        

	            messageFromClient = in.readLine();
	            whileChat(messageFromClient); 
	        }
	    } catch(IOException ioExecption) {
	        ioExecption.printStackTrace();
	    }
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
	            Gui.consoleTextArea.append(message + "\n");
	        }
	    });
	}

	public static void sendMessage(String message) {
	    out.println(message);
	    showMessage(name +  " : " + message + "\n");
	}	
}
