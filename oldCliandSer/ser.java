
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
		String str1 /*,str3*/;
		ServerSocket s1 = new ServerSocket(1341);
		//ServerSocket s3 = new ServerSocket(1343);
		
		while(true){
		Socket ss1=s1.accept();
		//Socket ss3=s3.accept();
		BufferedReader in1 = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
		str1 = in1.readLine();
		System.out.println(str1);
		
		
		//BufferedReader in3 = new BufferedReader(new InputStreamReader(ss3.getInputStream()));
		//str3 = in3.readLine();
		//System.out.println(str3);
		}
	}

	

}
