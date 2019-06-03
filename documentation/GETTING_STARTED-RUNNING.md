# Getting Started
## Running the Robot
Navigate to the file containing all of the java files.
Compile all java files(Motor.java, MotorController.java, MotorHttpServer.java, SiteHTTPServer.java) by running this command for each:

- javac file.javac

Run this command in one tab(servers run on port 80 need to be run under sudo):

- sudo java SiteHTTPServer

run this command in another tab at the same time:

- java MotorHttpServer

connect to drone and control by typing in the ip of the raspi to your browser. Touch and move finger around screen if on phone or click on window and drag mouse around if on computer.
