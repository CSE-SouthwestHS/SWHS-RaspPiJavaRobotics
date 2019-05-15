import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class serMotorDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(new SimpleServer()).start();
    }

    static class SimpleServer implements Runnable {
        public void run(){
            ServerSocket socketServer = null;
            Socket serverSession = null;
            //String str = null;
            //String message = null;
            BufferedReader in = null;
            PrintWriter out = null;
            int PORT = 5000;
            boolean ServerOpen = false;
            boolean Reported = false;

            String xValue, yValue;
            Float xFinal, yFinal;
            MotorController.initMotorController();

            try {
                socketServer = new ServerSocket(PORT);
                System.out.println("Listening on Port: " + PORT);
                ServerOpen = true;
            } catch (IOException e1) {
                //e1.printStackTrace();
                System.out.print("Server Active \n");
            }
            while(true){
                if (serverSession == null){
                    try {
                        serverSession = socketServer.accept();
                        out = new PrintWriter(serverSession.getOutputStream(), true);
                        if (Reported == false){
                            out.println("Server Connected on Port: " + PORT);
                            Reported = false;
                        }
                    } catch (IOException e) {
                        System.out.println("Failed \n");
                        System.exit(-1);
                    }
                }
                try {
                    in = new BufferedReader(new InputStreamReader(serverSession.getInputStream()));
                } catch (IOException e) {
                    out.println("Signal not received");
                    System.exit(-1);
                }
                StringBuilder sb = new      StringBuilder();
                String line = null;
                try {
                    if ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    System.out.println("Bad data \n");
                    System.exit(-1);
                }
                if (line != null){
                    String coordinateData = sb.toString();
                    String filteredonce = coordinateData.replace("HTTP/1.1","");
                    String filteredTwice = filteredonce.replace("GET /?","");
                    System.out.println(filteredTwice);
                    //serverSession = null;

                    xValue = filteredTwice.substring(0,filteredTwice.indexOf(","));
                    yValue = filteredTwice.substring((filteredTwice.indexOf(",") + 1));
                    xFinal = Float.parseFloat(xValue);
                    yFinal = Float.parseFloat(yValue);
                    //MotorController.speedAdapter(xFinal, yFinal); //this is what connects to motor controller, only works on raspberry pi with this code running
                    System.out.println("X value: " + xFinal + ", Y value: " + yFinal);
                }
                try {
                    if (in.readLine() == null){
                        //System.out.println("Client Disconected"+serverSession);
                        serverSession = null;
                    }
                } catch (IOException e) {
                    System.out.println("Bad data \n");
                    System.exit(-1);
                }
            }
        }
    }
}
/*
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
            String str = null;
            String message = null;
            BufferedReader in = null;
            PrintWriter out = null;
            int PORT = 5000;
            boolean Connected = false;
            boolean Reported = false;

            try {
                socketServer = new ServerSocket(PORT);
                System.out.println("Listening on Port: " + PORT);
                Connected = true;

                while(true){
                    try {
                        if (str == null){
                            Socket serverSession = socketServer.accept();
                            Scanner socketScanner = new Scanner(System.in);
                            in = new BufferedReader(new InputStreamReader(serverSession.getInputStream()));
                            out = new PrintWriter(serverSession.getOutputStream(), true);
                            if (Connected == true && Reported == false) {
                                System.out.println("Client Connected \n");
                                Reported = true;
                            }
                            System.out.println("Enter any string: ");
                            message = socketScanner.nextLine();
                            out.println(message);
                            System.out.println("\nWaiting for client...");
                            str = in.readLine();
                            System.out.println(str + "\n");
                            str = null;
                        }
                    } catch(IOException e){
                        //e.printStackTrace();
                        System.out.print("Session Ended - - - - - - - - - -\n");
                        Reported = false;
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

 */
