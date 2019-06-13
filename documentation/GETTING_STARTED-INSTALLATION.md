# Getting Started
##Installation

Install Raspbian OS image onto raspberry pi:

Install latest version of java onto raspberry pi:  

- sudo apt-get install openjdk-8-jdk

Now you need to change the JAVA_HOME so that the latest version runs in terminal.
Navigate to the folder where the jdk you just installed is, should be in "/usr/lib/jvm/jdk-8-oracle-arm32-vpm32-vpf-hflt".

Open terminal and navigate to root by typing the command(typing "cd" brings you all the way back to the base of the folder tree no matter what folder you were located in previously):

- cd

Then navigate the folder "etc":

- cd /etc

Open the document "envirpnment" (sudo is required otherwise changes cannot be saved):

- sudo leafpad /etc/environment

you are now inside the document "environment", type the filepath of the jdk:
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
*note*
Most school networks will block at least a portion of these installs so its best to be installing under a different  network or vpn from here on. If an instillation is ending by saying such and such was not installed then it means certain parts were blocked.

Run command:

- sudo apt-get install motion

This will take a while. Run this command(make sure you type a lowercase "L" not a "1"):

- sudo modprobe bcm2835-v4l2

Navigate to root:

- cd

Activate drivers by opening file "modules":

- sudo leafpad /etc/modules

Append the following line to the very end of the file:

- bcm2835-v4l2

Save file and close. Then open the document "motion":

- sudo leafpad /etc/default/motion

search for start_motion_daemon and change it from no to yes. Should look like this:

- start_motion_daemon=yes

The rest is configuring motion

Run command to create backup:

- sudo cp /etc/motion/motion.conf /etc/motion/motion.conf.bak

run command:

- sudo nano /etc/motion/motion.conf

Change all of the lines in the configuration file to match the lines written below:

Allow motion to run the daemon we've set earlier:
- daemon on

Set the logfile (important to debug motion if you webservers crashes):
- logfile /tmp/motion.log

We want to be able to access the stream outside off the Pi's localhost:
- stream_localhost off

Set the framerate of the stream (100 for higher quality):
- framerate 100

Set the width and height of your video:
- width 640
- height 480

Control de port 8080 by default:
- webcontrol_port 8081

Be careful not to set the stream_port to be just like the webcontrol port.

Save and exit.

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

Save and exit.

### Setting up the motor control and website server code
[download zip file of our code](https://github.com/CSE-SouthwestHS/SWHS-RaspPiJavaRobotics)
Unzip the file and move it to the root of the system.

Find the ip of the raspberry pi by typing the command:

- ifconfig

inside the file SWHS_RaspiJavaRobotics/public_html/index.html search, for "ip=", change the ip after "http://" to be the ip of your raspi on your wifi.

Save and exit.

## Setting up SSH for remote access and control
Download SSH software with a GUI (I like the software "PuTTY") onto the computer you are using to connect to your Raspberry pi.

Navigate to the configuration menu of your Raspberry pi by typing the following command into your terminal:

- sudo rapi-config

Then navigate to "Interfacing Options" using your arrow keys, press enter.

Select SSH.

When prompted with "Would you like the SSH server to be enabled", select yes.
