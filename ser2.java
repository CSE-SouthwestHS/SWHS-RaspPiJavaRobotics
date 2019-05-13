import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;


public class ser {
    public static void main(String[] args) throws IOException  {
        String str1 /*str3*/;
        String xValue;
        String yValue;
        Float xFinal = 0f, yFinal;
        ServerSocket s1 = new ServerSocket(1341);
        Startup.run();
        //ServerSocket s3 = new Serv    erSocket(1343);
        
        while(true){
            Socket ss1=s1.accept();
            //Socket ss3=s3.accept();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
            str1 = in1.readLine();
            System.out.println(str1);
            try{
                xValue = str1.substring(0,str1.indexOf(","));
                System.out.println(xValue);
                yValue = str1.substring((str1.indexOf(",") + 1));
                System.out.println(yValue);
                xFinal = Float.parseFloat(xValue);
                yFinal = Float.parseFloat(yValue);
                System.out.println(xFinal + " " + yFinal);
                MotorController.speedAdapter(xFinal, yFinal);
            }catch(Exception e) {
                MotorController.errorHandler(e);
            }
            
        
        
            //BufferedReader in3 = new BufferedReader(new InputStreamReader(ss3.getInputStream()));
            //str3 = in3.readLine();
            //System.out.println(str3);
        }
    }
}
