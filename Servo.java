class Servo {
    public static void main(String args[]) {
        try {			
            Runtime runTime = Runtime.getRuntime();
            runTime.exec("gpio mode 1 pwm");
            runTime.exec("gpio pwm-ms");
            runTime.exec("gpio pwmc 205"); 
            runTime.exec("gpio pwmr 2000"); 
            runTime.exec("gpio pwm 1 152"); // ~center

            /*Thread.sleep(5000);
            runTime.exec("gpio pwm 1" + 30); // turn right
            Thread.sleep(3000);
            runTime.exec("gpio pwm 1" + 270); // turn left
            Thread.sleep(3000);*/

            int i = 100;
            boolean turningLeft = true;
            while(true) {
                runTime.exec("gpio pwm 1 " + i);
                Thread.sleep(1);
                if (turningLeft) {
                    //Tread.sleep(1);
                    i += 1;
                } else {
                    i -= 1;
                }
                if (i > 270) { turningLeft = false; }
                if (i < 30) { turningLeft = true;}
                
            }
			
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }
}
