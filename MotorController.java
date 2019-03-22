import java.util.*;

public class MotorController {
	private static int directionPin1 = 0;
	private static int directionPin2 = 0;
	private static int pwmPin = 0;
	private static int directionPin3 = 0;
	private static int directionPin4 = 0;
	private static int pwmPin2 = 0;
	private static double Maxpwm = 0.0;
	
	public static void main(String[] args) {
		Scanner userInputScanner = new Scanner(System.in);
		System.out.println("Enter the first directional pin number for your left motor.");
		directionPin1 = userInputScanner.nextInt();
		System.out.println("Enter the second directional pin number.");
		directionPin2 = userInputScanner.nextInt();
		System.out.println("Enter your PWM pin for your left motor.");
		pwmPin = userInputScanner.nextInt();
		System.out.println("Enter the maximum PWM value you'd like to use.");
		Maxpwm = userInputScanner.nextDouble();
		MotorCommand motorLeft = new MotorCommand(directionPin1, directionPin2, pwmPin, Maxpwm, "Left");
		System.out.println("Enter the first directional pin number for your right motor.");
		directionPin3 = userInputScanner.nextInt();
		System.out.println("Enter the second directional pin number.");
		directionPin4 = userInputScanner.nextInt();
		System.out.println("Enter your PWM pin for your right motor.");
		pwmPin2 = userInputScanner.nextInt();
		MotorCommand motorRight = new MotorCommand(directionPin3, directionPin4, pwmPin2, Maxpwm, "Right");
		while (true) {
			motorLeft.speed();
			motorRight.speed();
	}
	}
}
