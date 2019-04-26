import java.util.*;

public class Startup
{
    public static void main(String[] args){ //Call this to initialize the program
        MotorController.initMotorController();
        //Webserver CurrentWebserver = new Webserver();
        Webserver CurrentWebserver = new Webserver();
    }
}
