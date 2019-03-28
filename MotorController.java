import java.util.*;

public class MotorController {
	private static int directionPin1 = 17;
	private static int directionPin2 = 22;
	private static int pwmPin = 19;
	private static int directionPin3 = 2;
	private static int directionPin4 = 4;
	private static int pwmPin2 = 12;
	private static double Maxpwm = 4000.0;
	static Scanner userInputScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		MotorCommand motorLeft = new MotorCommand(directionPin1, directionPin2, pwmPin, Maxpwm, "Left");
	    MotorCommand motorRight = new MotorCommand(directionPin3, directionPin4, pwmPin2, Maxpwm, "Right");
		motorLeft.init();
		motorRight.init();
		while (true){
			motorLeft.speed();
			motorRight.speed();
		}
	}
}
