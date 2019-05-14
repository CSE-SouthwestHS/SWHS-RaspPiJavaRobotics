# Previous Help Request - No Longer Active

This is not the full project code in this branch, the HTTP server, the camera server, all of the working website html/css/javascript, motor control have been left out, just what we are using to test sending variables has been included.

##How to run Java to Java, client and server:
1. After compiling both ser.java to ser.class and cli.java to cli.class, first run ser.class then run cli.class at the same time, wait until the connection establishes(can take about a minute for initial connection)
2. First type a string of characters into server and press enter, then type a string of characters into Client
3. Will send back and forth a string, the way it is set up right now is that it waits for a response before can send another
4. (Optional) To test on two different computers, set ip address in cli.java to whatever the local network ip address of the computer running the server ser.java then re-compile. (If both client and server are on the same computer leave ip as "localhost")

The current problem we are facing is sending and receiving strings from a JavaScript client in a browser to a Java server on a Raspberry Pi 3.
So 'cli.class' needs to be replaced with JavaScript that can run in a web browser.

We can send from the client already but we want the server to send error message strings back to a JavaScript client.

What you see here is a back and forth passing of strings between a Java server and a Java client using socket.io on a single port(see HelpRequestWebsocket/ser.java and HelpRequestWebsocket/cli.java).

We looked at Node.js solutions but that would require Node server on both sides.  So we've been looking at Ajax options...

===========================

We can conceive of a solution in 3 ways. The first is the closest to where we are right now and is probably ideal:
1. Is it possible to use socket.io with a JavaScript client and a socket.io Java server? If it is, could you give a simple working example that passes strings to and from the Java server and JavaScript client?
2. If #1 is impossible or their is a better solution: Is it possible to use socket.io on a Java Server and a JavaScript WebSocket client? What's the deal with the HTTP request "GET / HTTP/1.1"? How is an HTTP request supposed to be handled manually from Java? How is communication continued between server and client in any code after an HTTP request? If it is possible could you give a simple working example of string communication between a socket.io Java server and a WebSocket JavaScript client?
3. If #2 is impossible or their is a better solution: Could you give an example of a simple working example of a WebSocket JavaScript client communicating with strings to and from a Java server?

Thanks for your help in advance, The Southwest Computer Science Team.
