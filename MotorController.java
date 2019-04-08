import java.util.*;

public class MotorController {
    private static int directionPin1 = 17;      //Sets up the first directional pin for the first motor
    private static int directionPin2 = 22;      //Sets up the second directional pin for the first motor
    private static int pwmPin = 19;             //Sets up the pwm pin for the first motor
    private static int directionPin3 = 2;       //Sets up the first directional pin for the second motor
    private static int directionPin4 = 4;       //Sets up the second directional pin for the second motor
    private static int pwmPin2 = 12;            //Sets up the pwm pin for the second motor
    private static double Maxpwm = 4000.0;      //Sets the maximum pwm value for both motors
    //static Scanner userInputScanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Motor motorLeft = new Motor(directionPin1, directionPin2, pwmPin, Maxpwm, "Left");
        Motor motorRight = new Motor(directionPin3, directionPin4, pwmPin2, Maxpwm, "Right");
        motorLeft.init();
        motorRight.init();
        while (true){
            motorLeft.speed();
            motorRight.speed();
        }
    }
}
