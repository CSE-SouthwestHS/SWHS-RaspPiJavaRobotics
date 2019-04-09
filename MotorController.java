import java.util.*;

public class MotorController {
    //MotorController import and read
    private static int directionPin1 = 17;      //Sets up the first directional pin for the first motor
    private static int directionPin2 = 22;      //Sets up the second directional pin for the first motor
    private static int pwmPin = 19;             //Sets up the pwm pin for the first motor
    private static int directionPin3 = 2;       //Sets up the first directional pin for the second motor
    private static int directionPin4 = 4;       //Sets up the second directional pin for the second motor
    private static int pwmPin2 = 12;            //Sets up the pwm pin for the second motor
    private static float Maxpwm = 4000;           //Sets the maximum pwm value for both motors
    private static Motor motorLeft;
    private static Motor motorRight;
    public static void initMotorController(){
        motorLeft = new Motor(directionPin1, directionPin2, pwmPin, Maxpwm, "Left");
        motorRight = new Motor(directionPin3, directionPin4, pwmPin2, Maxpwm, "Right");
        motorLeft.init();
        motorRight.init();
    }
    public static void main(String[] args) { //This Is a Temporary Startup Function
        Startup.run();
        // Begin demo code
        speedAdapter(50, 25);
        while (true){
            motorLeft.speed();
            motorRight.speed();
        }
    }
    public static void errorHandler(Exception ex) {
        System.out.println("Exception occured: " + ex.getMessage());
    }
    public static void speedAdapter(float x, float y){
        x = 0 - x;
        float v = (50-Math.abs(x))*(y/50) + y;
        float w = (50-Math.abs(y))*(x/50) + x; 
        float r = (v+w)/100;
        float l = (v-w)/100;
        motorLeft.variableSpeed(l);
        motorRight.variableSpeed(r);
    }
}
