
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
		//int number1, temp1, number2, temp2;
		ServerSocket s1 = new ServerSocket(1341);
		//ServerSocket s2 = new ServerSocket(1342);
		
		while(true){
		Socket ss1=s1.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
		String str = in.readLine();
		//log.d(str);
		System.out.println(str);
		//Scanner sc1 = new Scanner(ss1.getInputStream());
		//number1 = sc1.nextInt();
		
		/*temp1 = number1;
		
		PrintStream p1 = new PrintStream(ss1.getOutputStream());
		p1.println(temp1);
		System.out.println(number1);
		
		Socket ss2=s2.accept();
		Scanner sc2 = new Scanner(ss2.getInputStream());
		number2 = sc2.nextInt();
		
		temp2 = number2;
		
		PrintStream p2 = new PrintStream(ss2.getOutputStream());
		p2.println(temp2);
		System.out.println(number2);*/
		}
	}

	

}
