# Getting Started
## Running the Robot
On the computer you are controlling the raspi from, open your SSH software and type "pi@ followed by the IP address of your Raspberry pi (pi being the username you are logging into SSH with. Find the IP of your Raspberry pi by typing "ifconfig" into the raspi terminal)

When prompted to enter a password, use "raspberry" (unless changed this is the default Raspberry pi password).

Once inside the SSH server of your raspi navigate to the file containing all of the java files using terminal (should be in root of raspi). Type this command into terminal(simply typing "cd" brings you back all the way to the beginning of the folder tree of your raspi):

- cd

Compile (Motor.java, MotorController.java, MotorHttpServer.java, SiteHTTPServer.java) by running the java compile command for each java file:

- javac Motor.java
- javac MotorController.java
- javac SimpleHttpServer.java
- javac JavaHTTPServer.java

Run this command in one tab (servers run on port 80 need to be run under sudo):

- sudo java JavaHTTPServer

Run this command in another tab at the same time:

- java SimpleHttpServer

Connect to drone and control by typing in the ip of the raspi into your browser. Touch and move your finger around the screen if on a mobile device, or click on the window and drag your mouse around if on a computer.
