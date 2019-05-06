
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class cli {
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		while(true) {
		String bundle;	
		Scanner sc=new Scanner(System.in);
		Socket s1 = new Socket("10.55.170.99", 1341);
		Scanner sc1 = new Scanner(s1.getInputStream());
		System.out.println("enter any string: ");
		bundle = sc.nextLine();
		PrintStream p1= new PrintStream(s1.getOutputStream());
		p1.println(bundle);
		System.out.print(bundle);
		
		/*String error;
		Socket s3 = new Socket("192.168.101.90", 1343);
		Scanner sc3 = new Scanner(s3.getInputStream());
		System.out.println("enter any string(2): ");
		error = sc.nextLine();
		PrintStream p3= new PrintStream(s3.getOutputStream());
		p1.println(error);
		System.out.print(error);*/
		}
	}

}
