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
    public static void initMotorController() {
        motorLeft = new Motor(directionPin1, directionPin2, pwmPin, Maxpwm, "Left");
        motorRight = new Motor(directionPin3, directionPin4, pwmPin2, Maxpwm, "Right");
        motorLeft.init();
        motorRight.init();
    }
    public static void main(String[] args) { //This Is a Temporary Startup Function
        Startup.run();
        // Begin demo code
        speedAdapter(0, 0);
        while (true){
            Scanner speedScanner = new Scanner(System.in);
            System.out.println("Enter x y co-ordiates");
            try {
                float xInput = speedScanner.nextFloat();
                float yInput = speedScanner.nextFloat();
                speedAdapter(xInput, yInput);
            } catch (Exception e) {
                MotorController.errorHandler(e);
            }
        }
    }
    public static void errorHandler(Exception ex) {
        System.out.println("Exception occured: " + ex.getMessage());
    }
    public static void speedAdapter(float x, float y) {
        if(x > 100){
            x = 100f;
        }
        if(x < -100){
            x = -100f;
        }
        if(y > 100){
            y = 100f;
        }
        if(y < -100){
            y = -100f;
        }
        float xInvert = -x;
        float v = (100-Math.abs(xInvert))*(y/100) + y;
        float w = (100-Math.abs(y))*(xInvert/100) + xInvert;
        float r = (v+w)/200;
        float l = (v-w)/200;
        System.out.println(l + " " + r);
        Thread threadL = new Thread(new Runnable() {
           @Override 
           public void run(){
               motorLeft.variableSpeed(l);
            }
        });
        Thread threadR = new Thread(new Runnable() {
            @Override
            public void run(){
                motorRight.variableSpeed(r);
            }
        });
        threadL.start();
        threadR.start();
    }
}
