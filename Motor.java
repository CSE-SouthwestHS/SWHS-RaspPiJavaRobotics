import java.util.*;

public class Motor
{
    //in1 and in2 set direction as follows (1,1 = not used; 1,0 = forward; 0,1 = reverse; 0,0 = stop)
    private int in1 = 2;                    //Sets the number of control pin 1 (controls direction) state is on or off 
    private boolean direction = false;       //Does not set direction, only tracks. Change boolean everytime direction flips
    private int in2 = 4;                    //Sets the number of control pin 2 (controls direction) state is on or off
    private int enA= 12;                    //Sets the number of the pwm pin (controls motor speed)
    private float pwmMax = 4000;            //Sets the maximum value the pwm pin can be set to
    private float pwmCurrent = 0;           //Sets the initial value of the pwm pin
    private float rampRequire = .3f;
    private String MotorSide = "Not set!";
    private Runtime runTime = Runtime.getRuntime();

    //Creates a new instance of the Motor class with the input values set to their respective variables
    public Motor(int pin1, int pin2, int setpwmPin, float Maxpwm, String side){
        in1 = pin1;
        in2 = pin2;
        enA = setpwmPin;
        pwmMax = Maxpwm;
        MotorSide = side;
    }
    //Initializes the Motor based on the variables provided above
    public void init() {
        try {
            runTime.exec("gpio -g mode " + enA + " pwm");
            runTime.exec("gpio -g pwm-ms");
            runTime.exec("gpio -g pwmc 205"); 
            runTime.exec("gpio -g pwmr " + pwmMax);
            runTime.exec("gpio -g pwm " + enA + " " + pwmCurrent);
            runTime.exec("gpio -g mode " + in1 + " out");
            runTime.exec("gpio -g mode " + in2 + " out");
            runTime.exec("gpio -g write " + in1 + " 1");
            runTime.exec("gpio -g write " + in2 + " 0");
            System.out.println(MotorSide + " " + in1 + " " + in2 + " " + enA);
        } catch (Exception e) {
            MotorController.errorHandler(e);
        }
    }
    //Takes in the speed variable and then determines the ramping method to ensure stable change in speed
    public void variableSpeed(float speed) {
        float finalSpeed = 0.0f;
        float absoluteValueSpeed = 0.0f;
        try {
            if (speed > 1 || speed < -1) {
                System.out.println("Please enter a value between 1 and -1");    //Add throw error
            } else {
                finalSpeed = (float) speed*pwmMax;
                absoluteValueSpeed = (float) Math.abs(finalSpeed);
                System.out.println("DEBUG: finalSpeed " + finalSpeed + ", " + absoluteValueSpeed);
                if ((!direction && finalSpeed < 0) || (direction && finalSpeed > 0)) {      //checks to see the state of the direction boolean and the nature of the finalSpeed variable relative to zero to ensure that ramping from one direction to another is possible
                    if (finalSpeed < 0) {
                        System.out.println("DEBUG: variableSpeed A");
                        ramp(0.0f);
                        pwmCurrent = 0;
                        reverse();
                        ramp(absoluteValueSpeed);
                        pwmCurrent = absoluteValueSpeed;
                        runTime.exec("gpio -g pwm " + enA + " " + pwmCurrent);
                        Thread.sleep(250);
                    } else {
                        System.out.println("DEBUG: variableSpeed B");
                        ramp(0.0f);
                        pwmCurrent = 0;
                        forward();
                        ramp(absoluteValueSpeed);
                        pwmCurrent = absoluteValueSpeed;
                        runTime.exec("gpio -g pwm " + enA + " " + pwmCurrent);
                        Thread.sleep(250);
                    }
                } else {
                    if (finalSpeed < 0) {
                        System.out.println("DEBUG: variableSpeed C");
                        ramp(absoluteValueSpeed);
                        pwmCurrent = absoluteValueSpeed;
                        runTime.exec("gpio -g pwm " + enA + " " + pwmCurrent);
                        Thread.sleep(250);
                    } else {
                        System.out.println("DEBUG: variableSpeed D");
                        ramp(absoluteValueSpeed);
                        pwmCurrent = absoluteValueSpeed;
                        runTime.exec("gpio -g pwm " + enA + " " + pwmCurrent);
                        Thread.sleep(250);
                    }
            }
        }
        } catch (Exception e) {
            MotorController.errorHandler(e);
        }
    }
    //Uses the directionalSpeed variable to change the speed of the motor in an arbitrary fifty steps
    public void ramp(float directionalSpeed) {
        float difference = 0.0f, calculating = 0.0f, stepNumber = 10.0f;
        try {
            difference = directionalSpeed - pwmCurrent;
            if(Math.abs(difference) > (rampRequire*pwmMax)){
                calculating = difference/stepNumber;
                boolean valueIncreasing = calculating > 0;
                System.out.println("Starting Move From " + pwmCurrent + " To " + directionalSpeed + ", Step Size: " + calculating);
                while(pwmCurrent != directionalSpeed) {
                    if(valueIncreasing) {
                        if(pwmCurrent >= directionalSpeed) {
                            runTime.exec("gpio -g pwm "+ enA + " " + directionalSpeed);
                            break;
                        }
                    } else {
                        if(pwmCurrent <= directionalSpeed) {
                            runTime.exec("gpio -g pwm "+ enA + " " + directionalSpeed);
                            break;
                        }
                    }
                    runTime.exec("gpio -g pwm "+ enA + " " + (pwmCurrent + calculating));
                    System.out.println(pwmCurrent);
                    pwmCurrent += calculating;
                    Thread.sleep(20);
                }
            } else{
                System.out.println("Set " + directionalSpeed);
                //Ramping not required
            }
        } catch (Exception e) {
             MotorController.errorHandler(e);
        }
    }
    //Sets the Motor's directional pins for the forwards direction
    public void forward() {
        try {
            runTime.exec("gpio -g write " + in1 + " 1");
            runTime.exec("gpio -g write " + in2 + " 0");
            direction = false;
            Thread.sleep(250);
        } catch (Exception e) {
            MotorController.errorHandler(e);
        }
    }
    //Sets the Motor's directional pins as the opposite of the forward direction
    public void reverse() {
        try {
            runTime.exec("gpio -g write " + in1 + " 0");
            runTime.exec("gpio -g write " + in2 + " 1");
            direction = true;
            Thread.sleep(250);
        } catch (Exception e) {
            MotorController.errorHandler(e);
        }
    }
}
