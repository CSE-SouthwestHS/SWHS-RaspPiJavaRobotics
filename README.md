The current problem we are facing is sending a message (a string with no more than 8 characters) from a JavaScript client in a browser to a Java server on a Raspberry Pi 3. We then want the server to send error message strings back to the browser JavaScript client.
At the moment we have a back and forth passing of strings between a Java server and a Java client using socket.io on a single port(see HelpRequestWebsocket/ser.java and HelpRequestWebsocket/cli.java).
When we run a JavaScript client (HelpRequestWebsocket/push.js) through Node.js it communicates with WebSocket to the Java server (HelpRequestWebsocket/ser.java), but not in a way that we fully understand how to handle. The communication sent comes in the form of an HTTP request, which is received by the server as "GET / HTTP/1.1" (if we are attempting to send a string from client or not, whenever we run "connection.onopen" in HelpRequestWebsocket/push.js). Probably socket.io and WebSocket are incompatible, but the fact that we are getting something makes us think that once we figure out a response we could open up string pushing and listening on both sides.

We are can conceive of a solution in 3 ways.  The first is the closest to where we are right not and is probaly ideal.:
1. Is it possible to use socket.io with a JavaScript client and a socket.io Java server? If it is, could you give a simple working example that passes strings to and from the Java server and JavaScript client?
2. If #1 is impossible or their is a better solution: Is it possible to use socket.io on a Java Server and a JavaScript WebSocket client? What's the deal with the HTTP request "GET / HTTP/1.1"? How is an HTTP request supposed to be handled manually from Java? How is communication continued between server and client in any code after an HTTP request? If it is possible could you give a simple working example of string communication between a socket.io Java server and a WebSocket JavaScript client?
3. If #2 is impossible or their is a better solution: Could you give an example of a simple working example of a WebSocket JavaScript client communicating with strings to and from a Java server?

Thanks for your help in advance, the Southwest Computer Science Team.
