
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
		String test;	
		//int number1, temp1, number2, temp2;
		Scanner sc=new Scanner(System.in);
		Socket s1 = new Socket("10.55.168.34", 1341);
		Scanner sc1 = new Scanner(s1.getInputStream());
		System.out.println("enter any number(1): ");
		test = "test";
		//number1 = sc.nextInt();
		PrintStream p1= new PrintStream(s1.getOutputStream());
		//p1.println(number1);
		p1.println(test);
		//temp1 = sc1.nextInt();
		//System.out.print(temp1);
		System.out.print(test);
		//Socket s2 = new Socket("10.55.168.34", 1342);
		//Scanner sc2 = new Scanner(s2.getInputStream());
		//System.out.println("enter any number(2): ");
		//number2 = sc.nextInt();
		//PrintStream p2= new PrintStream(s2.getOutputStream());
		//p2.println(number2);
		//temp2 = sc2.nextInt();
		//System.out.print(temp2);
		}
	}

}
