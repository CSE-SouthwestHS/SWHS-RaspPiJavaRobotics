# SWHS-RaspPiJavaRobotics

### Presented by [CSE-SouthwestHS](https://github.com/CSE-SouthwestHS)

To our knowledge, this is the first ever complete, open-source curriculum project addressing both AP CSA (Computer Science Applications) and Robotics.  
**Our Mission** to provide an accessible entry point to Robotics programming for students and instructors of College Level Java.

_Our first beta version driving across campus_|_Robot Chassis Beta 2 Screenshot_
:-----:|:------:
![robot-beta1](https://cse-southwesths.github.io/SWHS-RaspPiJavaRobotics/documentation/robot-beta1.jpg)|![robot-beta2-screenshot](https://cse-southwesths.github.io/SWHS-RaspPiJavaRobotics/documentation/robot-beta2-screenshot.jpg)

**What Makes This Project Unique**
- Completely self contained.  There are no materials, code, products, or research that are not provided or listed in this repository.
- Low cost.  The robotics hardware in the price list can be purchased for approximately $250.
- Ready for use for students and instructors.  All hardware interface programming is already complete with well documented APIs for higher abstraction development.

# HELP REQUEST
This code is (and probably always will be) under development.  It is fully functional but we invite anyone who would like to offer valuable improvements to please join us.  Fork the repository and send us your Pull Requests!
Currently we are working to improve latency between the Front-end GUI, server, and PWM hardward interface.  Please see the issues posted on the
**[HELP REQUEST BRANCH](https://github.com/CSE-SouthwestHS/SWHS-RaspPiJavaRobotics/blob/HelpRequestWebsocket/README.md)**.
Contributors will be recognized and appreciated!!!

# Credits
### The project concept and materials where developed by the following Southwest High School Computer Science students:

>- William @williammunnich - Control System Server Back-end Developer
>- Seay @Cephi495 - Control System GUI and Front end Developer
>- David @dhocken32 - Motor Control Hardware Interface Developer
>- @smue1901 - Robot Hardware Developer & PCA9685 Interface
>- [Aidan Dostal](http://dostal.tech/) @mynusplus - Project & Quality Assurance Manager
>- Themis @Themis3000 - Web Development / Rasp Pi OS Configuration and Deployment

____________________________________________________________________________

**GETTING STARTED GUIDE**
Install latest version of java onto raspberry pi:  

- sudo apt-get install openjdk-8-jdk

Now you need to change the JAVA_HOME so that the latest version runs in terminal.
Navigate to the folder where the jdk you just installed is, should be in "/usr/lib/jvm/jdk-8-oracle-arm32-vpm32-vpf-hflt".

Open terminal and navigate to /etc by typing the command:

- cd

then:

- sudo leafpad /etc/environment

you are now inside the document environment, type the filepath of the jdk:
JAVA_HOME="/usr/lib/jvm/jdk-8-oracle-arm32-vpm32-vpf-hflt"
Save and exit the document.

Check to make sure the development kit is set up right by typing the command:

- java -version

should print out as something that starts with 1.8


###Install and configure Motion camera server
enter command:

- sudo raspi-config

choose Camera and select "Enable support for Raspberry Pi camera"

reboot raspi:

- sudo shutdown -h now

test camera command:

- raspistill -o cam.jpg

Run command:

- sudo apt-get update

Then run command:

- sudo apt-get upgrade

Upgrade will take more than twenty minutes to execute.
*note* Most school networks will block at least a portion of these installs so its best to be installing under a different  network or vpn from here on. If an instillation is ending by saying such and such was not installed then it means certain parts were blocked.

Run command:

- sudo apt-get install motion

This will take a while. Run this command:

- sudo modprobe bcm2835-v4l2

Activate drivers by navigating into /etc/modules:

- sudo leafpad /etc/modules

Append the following line to the end of the file:

- bcm2835-v4l2

Save file and close. Then open /etc/default/motion:

- sudo leafpad /etc/default/motion

search for start_motion_daemon and change it from no to yes. Should look like this:

- start_motion_daemon=yes

The rest is configuring motion

Run command to create backup:

- sudo cp /etc/motion/motion.conf /etc/motion/motion.conf.bak

run command:

- sudo nano /etc/motion/motion.conf

Change all of the  lines in the configuration file to be what is writtin below:
Allow motion to run the daemon we've set earlier
- daemon on

Set the logfile (important to debug motion if you webservers crashes)
- logfile /tmp/motion.log

we want to be able to access the stream outside off the Pi's localhost
- stream_localhost off

set the framerate of the stream (100 for higher quality)
- framerate 100

set the width and height of your video
- width 640
- height 480

control de port 8080 by default
- webcontrol_port 8081

careful ! don't set the stream_port just like the webcontrol port
- Save and exit

Open /etc/init.d/motion by:

- sudo leafpad /etc/init.d/motion

Change everything from "start)" to ";;" so it is exactly as follows:

    start)
        if check_daemon_enabled ; then
            if ! [ -d /var/run/motion ]; then
                    mkdir /var/run/motion
            fi
            chown motion:motion /var/run/motion

            #export LD_PRELOAD=/usr/lib/uv4l/uv4lext/armv6l/libuv4lext.so
            sudo modprobe bcm2835-v4l2

            chmod 777 /var/run/motion
            # this is the fix we've added to allow the network share to be connected first before we try to start motion:#

            sleep 30

            log_daemon_msg "Starting $DESC" "$NAME"
            if start-stop-daemon --start --oknodo --exec $DAEMON -b --chuid motion ; then
                log_end_msg 0
             else
                log_end_msg 1
                RET=1
            fi
        fi
        ;;

Save and exit

### Setting up the motor control and website server code
[download zip file of our code](https://github.com/CSE-SouthwestHS/SWHS-RaspPiJavaRobotics)
Unzip the file and move it to the root of the system
find the ip of the raspberry pi by typing the command:

- ifconfig

inside the file SWHS_RaspiJavaRobotics/public_html/index.html search, for "ip=", change the ip after "http://" to be the ip of your raspi on your wifi. Save and exit.

### Running the Program
Navigate to the file containing all of the java files.
Compile all java files(Motor.java, MotorController.java, MotorHttpServer.java, SiteHTTPServer.java) by running this command for each:

- javac file.javac

Run this command in one tab(servers run on port 80 need to be run under sudo):

- sudo java SiteHTTPServer

run this command in another tab at the same time:

- java MotorHttpServer

connect to drone and control by typing in the ip of the raspi to your browser. Touch and move finger around screen if on phone or click on window and drag mouse around if on computer.


### Parts List - ([Click to view on Amazon](http://a.co/cpzM4p2))

Item | Price
-----|------:
[Raspberry Pi 3 B+](https://www.amazon.com/ELEMENT-Element14-Raspberry-Pi-Motherboard/dp/B07BDR5PDW/ref=sr_1_3?s=electronics&ie=UTF8&qid=1547473528&sr=1-3&keywords=raspberry+pi+3B%2B)|$37.98
[Miuzei Raspberry Pi 3 B+ Case with Fan Cooling and 3× Heat-Sinks, 5V 2.5A Power Supply for RPi 3 B+, 3B, 2b](https://www.amazon.com/dp/B07BTHNW9W/?ref=idea_lv_dp_ov_d)|$16.59
[Raspberry Pi Camera Module V2-8 Megapixel,1080p](https://www.amazon.com/Raspberry-Pi-Camera-Module-Megapixel/dp/B01ER2SKFS/ref=sr_1_3?ie=UTF8&qid=1547474475&sr=8-3&keywords=Raspberry+Pi+Camera+Board+v2)|$25.00
[Matek V3.1 PDB Power Distribution Board](https://www.amazon.com/Matek-Power-Distribution-Multicopter-Quadcopter/dp/B071CFKFY1/ref=sr_1_6?ie=UTF8&qid=1540928993&sr=8-6&keywords=power+distribution+board)|$10.99
[PCA9685 16 Channel 12 Bit PWM Servo Driver](https://www.amazon.com/gp/product/B014KTSMLA/ref=ppx_yo_dt_b_asin_title_o06_s00?ie=UTF8&psc=1)|$9.99
[L298N Motor Drive Controller Board Module Dual H Bridge DC Stepper](https://www.amazon.com/Qunqi-Controller-Module-Stepper-Arduino/dp/B014KMHSW6/ref=sr_1_3?keywords=L298N+Motor+Driver+Controller&qid=1558735297&s=gateway&sr=8-3)|$6.89
[SunFounder 9g Metal Servo Gear RC Robot SF180M (Digital Semi-Metal Micro Servo Motor) 2pack  2x](https://www.amazon.com/SunFounder-Digital-Helicopter-Fix-Wing-Airplane/dp/B078Y312YP/ref=sr_1_7?ie=UTF8&qid=1540157831&sr=8-7&keywords=servo%2Bfor&th=1)|$25.98
[Uxcell 12V DC 200RPM Gear Motor Micro Speed Reduction Geared Motor (Choose your Motor Speed)](https://www.amazon.com/uxcell-550RPM-Electric-Reduction-Centric/dp/B01KTXRB90/ref=sr_1_12?crid=64JD3K7GT3Z1&keywords=geared%2Bdc%2Bmotor&qid=1549919794&s=gateway&sprefix=geared%2Bdc%2B%2Caps%2C190&sr=8-12&th=1)|15.98
[Pololu 1083 Universal Aluminum MOUNTING HUB for 6mm Shaft Pair](https://www.amazon.com/gp/product/B00B887FX8/ref=ppx_yo_dt_b_asin_title_o03_s01?ie=UTF8&psc=1)|13.89
[BQLZR Black Front Rear Pentagram Plastic Wheel Rims + High Grip Rubber Tires Pack of 4](https://www.amazon.com/BQLZR-Black-Pentagram-Plastic-Rubber/dp/B00ID51M9W/ref=sr_1_3?ie=UTF8&qid=1540165190&sr=8-3&keywords=rc+car+wheel)|$14.30
[Bayite 4 Pack 1" Low Profile Casters Wheels](https://www.amazon.com/gp/product/B071GTK6NZ/ref=ppx_yo_dt_b_asin_title_o03_s01?ie=UTF8&psc=1)|9.99
[Ovonic 11.1V 2200mAh 3S 50C Lipo Battery with Deans Plug](https://www.amazon.com/2200mAh-Airplane-Quadcopter-Helicopter-Multi-Motor/dp/B077P73SDS/ref=sr_1_1_sspa?ie=UTF8&qid=1547240425&sr=8-1-spons&keywords=3s+2200mah&psc=1)|$17.99
[Male T-Plug / T-Connectors Deans Style with 10cm 14awg Wire](https://www.amazon.com/T-Plug-T-Connectors-Deans-Style-BDHI-28/dp/B07MDGT5C1/ref=sr_1_3?keywords=T-Plug+Deans+Male&qid=1558733399&s=gateway&sr=8-3)|$9.98
[C23212 LiPo Voltage Checker + Warning Buzzer ](https://www.amazon.com/RioRand-Hop-ups-C23212-Voltage-Checker/dp/B003Y6E6IE/ref=pd_sbs_21_4/136-1253198-7157437?_encoding=UTF8&pd_rd_i=B003Y6E6IE&pd_rd_r=e6c9a38f-7e6d-11e9-8ec8-f37f2ab7fd5a&pd_rd_w=2tDjT&pd_rd_wg=lZN8q&pf_rd_p=588939de-d3f8-42f1-a3d8-d556eae5797d&pf_rd_r=AAZPFMYKHB1214F0W4Z5&psc=1&refRID=AAZPFMYKHB1214F0W4Z5)|$4.99
[iMAX B6 Lipo RC Battery Balance Digital Charger](https://www.amazon.com/Battery-Balance-Digital-Charger-Discharger/dp/B07P3J4656/ref=sr_1_3?keywords=iMAX+B6+Lipo&qid=1558734141&s=gateway&sr=8-3)|$25.98
[Female to Female 4 and 8 Inch Solderless Ribbon Dupont-Compatible Jumper Wires for Breadboard](https://www.amazon.com/GenBasic-Solderless-Dupont-Compatible-Breadboard-Prototyping/dp/B01L5ULRUA/ref=sr_1_3?keywords=Breadboard+Jumper+Wire+female+to+female&qid=1558736832&s=gateway&sr=8-3)|$5.99
Total|$252.62

*Price list Notes:
The items in this list are the best options for each product that we have found to build a single robot.  There are a few items in this list which are packs containing more items than required for just one robot.  Also, if you plan to build multiple robots, in many cases, larger more cost effective packs can be found by searching for products.  This can make the cost of building multiple robots significantly lower.*
