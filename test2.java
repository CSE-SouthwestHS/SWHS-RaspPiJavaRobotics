import java.lang.*;

public class test2
{
    public static void main (String[] args) throws Exception {
         PCA9685 PCAClass = new PCA9685();
        while(true) {
        PCAClass.setPWM(1, 1, 600);
        PCAClass.setPWM(2, 1, 600);
        PCAClass.setPWM(0, 1, 600);
        
        
        try{
                Thread.sleep(3000);
             } catch(Exception e) {
                 System.out.println(e);
             };
        PCAClass.setPWM(1, 1, 150);
        PCAClass.setPWM(2, 1, 150);
        PCAClass.setPWM(0, 1, 150);
        try{
                Thread.sleep(3000);
             } catch(Exception e) {
                 System.out.println(e);
             };
        
}
}
}