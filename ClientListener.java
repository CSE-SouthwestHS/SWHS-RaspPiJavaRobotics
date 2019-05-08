/*
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public class ClientListener {

    public static void ClientListener() {
        //Establishes server objects
        String ip = "10.55.162.1";
        int port = 5000;
        //ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader datainputstream = null;
        boolean ServerOpen;
        //--NEW--//
        System.out.println("Server Started");
        ServerOpen = true;
        //-------//
        /*try{
            //Creates new server
            //serverSocket = new ServerSocket(5000);
            System.out.println("Server: OPEN");
            // Opens Listener
            ServerOpen = true;
        }catch(IOException e){
            e.printStackTrace();
        }*//*


        while(ServerOpen == true) {
            if(socket == null) {
                try {
                    //socket = serverSocket.accept();
                    socket = new Socket(ip, port);
                    System.out.println("Connected");
                } catch (IOException e) {
                    System.out.println("Server Connection Failed \n");
                    System.exit(-1);
                }
            }
            System.out.println("running");
            try {
                datainputstream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.out.println("Signal not received \n");
                System.exit(-1);
            }

            StringBuilder sb = new StringBuilder();
            String line = null;

            try {
                if ((line = datainputstream.readLine()) != null) {
                    sb.append(line);
                } else{
                    System.out.println("No Reception!");
                }
            } catch (IOException e) {
                System.out.println("Bad data \n");
            }

            if (line != null){
                System.out.println(sb.toString());
                socket = null;
            }
        }
    }
}*/


/*
public class NodeJsEcho {
    // socket object
    private Socket socket = null;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        // class instance
        NodeJsEcho client = new NodeJsEcho();
        // socket tcp connection
        String ip = "1.1.1.1";
        int port = 6969;
        client.socketConnect(ip, port);
        // writes and receives the message
        String message = "message123";
        System.out.println("Sending: " + message);
        String returnStr = client.echo(message);
        System.out.println("Receiving: " + returnStr);
    }

    // make the connection with the socket
    private void socketConnect(String ip, int port) throws UnknownHostException, IOException {
        System.out.println("[Connecting to socket...]");
        this.socket = new Socket(ip, port);
    }

    // writes and receives the full message int the socket (String)
    public String echo(String message) {
        try {
            // out & in
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            // writes str in the socket and read
            out.println(message);
            String returnStr = in .readLine();
            return returnStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // get the socket instance
    private Socket getSocket() {
        return socket;
    }
}*/



                                        //---ORIGINAL--//



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ClientListener {

    public static void ClientListener() {
        //Establishes server objects
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader datainputstream = null;
        boolean ServerOpen = false;

        try{
            //Creates new server
            serverSocket = new ServerSocket(5000);
            System.out.println("Server: OPEN");
            // Opens Listener
            ServerOpen = true;
        }catch(IOException e){
            e.printStackTrace();
        }

        while(ServerOpen == true) {
            if(socket == null) {
                try {
                    socket = serverSocket.accept();
                    //System.out.println("Connected");
                } catch (IOException e) {
                    System.out.println("Failed \n");
                    System.exit(-1);
                }
            }
            try {
                datainputstream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.out.println("Signal not received \n");
                System.exit(-1);
            }
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                if ((line = datainputstream.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                System.out.println("Bad data \n");
            }
            if (line != null){
                System.out.println(sb.toString());
                socket = null;
            }
        }
    }
}