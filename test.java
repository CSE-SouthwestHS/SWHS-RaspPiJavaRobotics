import java.lang.*;
//This is a test showing how to interface with the PCA9685 Class
public class test //first we initiallize the class test, which will then control PCA9685
{
    public static void main (String[] args) throws Exception { //simple declaration making this executable
         PCA9685 PCAClass = new PCA9685(); 
         //In order to be able to call PCA9685 functions such as setPWM or setPWMFreq, we need to be able to reference 
         //them from within "test". We can do this with the above line
        PCAClass.setPWMFreq(60); //This sets the PWM frequency with which the PCA9685 will operate. 60hz is standard and works
        //for both the motors and the servos. PCAClass references our above line which references the seperate class PCA9685.
        // This in turn looks for "setPWMFreq" and then we set the value to 60
        while(true) {//this is our loop 
        PCAClass.setPWM(8, 1, 1);
        //The above line sets the PWM duty cycle on pin 8. set PWM uses (Pin#, Start of duty cycle, End of duty cycle) 
        //We start the duty cycle in the first tick (from 4095 available) and then stop it at 1 as well. This is basically 
        //setting pin 8 to a digital OFF
        PCAClass.setPWM(9, 1, 4095);
        //in this case we set pin 9 to a digital high, by having it on for the maximum possible time, namely from 1, 4095
        //One quick note: The L298N Driver controls direction of the motors by having 2 input lines High or Low. 
        //For example, In1 set to High and In2 set to Low could be counterclockwise while In2 - Low In1 - High is clockwise
        PCAClass.setPWM(6, 1, 1);
        //sets pin 6 low
        PCAClass.setPWM(7, 1, 4095);
        //sets pin 7 high
        PCAClass.setPWM(4, 1, 2000);
        //this function controls the motor which is at 4. We set the value of the duty cycle to 2000, which coresponds to 
        //the highest possible speed of these motors
        PCAClass.setPWM(5, 1, 2000);
        PCAClass.setPWM(0, 1, 150);
        //The line above controls servo (plugged into port 0) and sets the end of the cycle at 150. For these servos 
        //the range for the 180 degrees of motion coresponds to a duty cycle between 150 and 600
        PCAClass.setPWM(1, 1, 150);
        PCAClass.setPWM(2, 1, 150);
        
        try{
                Thread.sleep(3000);
             } catch(Exception e) {
                 System.out.println(e);
             };
             //the above waits 3 seconds before continuing
        PCAClass.setPWM(4, 1, 1);
        PCAClass.setPWM(5, 1, 1);
        PCAClass.setPWM(0, 1, 600);
        PCAClass.setPWM(1, 1, 600);
        PCAClass.setPWM(2, 1, 600);
        try{
                Thread.sleep(3000);
             } catch(Exception e) {
                 System.out.println(e);
             };
        PCAClass.setPWM(8, 1, 4095);
        PCAClass.setPWM(9, 1, 1);
        PCAClass.setPWM(6, 1, 4095);
        PCAClass.setPWM(7, 1, 1);
        PCAClass.setPWM(4, 1, 1000);
        PCAClass.setPWM(5, 1, 1000);
        PCAClass.setPWM(0, 1, 150);
        PCAClass.setPWM(1, 1, 150);
        PCAClass.setPWM(2, 1, 150);
        try{
                Thread.sleep(3000);
             } catch(Exception e) {
                 System.out.println(e);
             };
        PCAClass.setPWM(4, 1, 1);
        PCAClass.setPWM(5, 1, 1);
        PCAClass.setPWM(0, 1, 600);
        PCAClass.setPWM(1, 1, 600);
        PCAClass.setPWM(2, 1, 600);
        try{
                Thread.sleep(3000);
             } catch(Exception e) {
                 System.out.println(e);
             };
}
}
}