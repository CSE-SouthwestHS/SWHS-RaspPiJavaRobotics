var app = require('http').createServer(handler),
    io = require('socket.io')(app);
var url = require('url'),
    fs = require('fs'),
    net = require('net'); // TCP LISTEN port
//This will open a server at localhost:5000. Navigate to this in your browser.

app.listen(5000);
// Http handler function
function handler (req, res) {
  // Using URL to parse the requested URL
  var path = url.parse(req.url).pathname;
  // Managing the root route
  if (path == '/') {
    index = fs.readFile(__dirname+'/public/index.html',
    function(error,data) {
      if (error) {
        res.writeHead(500);
        return res.end("Error: unable to load index.html");
      }
      res.writeHead(200,{'Content-Type': 'text/html'});
      res.end(data);
    });
    // Managing the route for the javascript or css files
  } else if( /\.(js)$/.test(path) || /\.(css)$/.test(path) ) {
    index = fs.readFile(__dirname+'/public'+path,
      function(error,data) {
        if (error) {
          res.writeHead(500);
          return res.end("Error: unable to load " + path);
        }
        res.writeHead(200,{'Content-Type': 'text/plain'});
        res.end(data);
      });
  } else {
    res.writeHead(404);
    res.end("Error: 404 - File not found.");
  }
}

// Web Socket Connection
io.sockets.on('connection', function (socket) {
    //console.log(app);

    // If we received a command from a client
    socket.on('connection', function(data){
	    transmitterData = data["transmitterData"];
        var coordinates = transmitterData.split(',', 2),
            x = coordinates[0],
            y = coordinates[1];
        console.log(x);
        console.log(y);

        //print (x+','+y);
        // Set a timer for when we should stop
        setTimeout(function(){
            socket.emit('connection');
        }, 0);
    });
});
/*
// Create an instance of the Server and waits for a connection
net.createServer(function(socket){
    // Receives a connection - a socket object is associated to the connection automatically
    console.log('Drone Connected');

    // Add a 'data' - "event handler" in this socket instance
    socket.on('data', function(data){
        socket.setEncoding('utf8');
        data = "variable";
        // replace echo function with return value of x and y
        socket.write(data);
        //return data;
    });
    // Add a 'close' - "event handler" in this socket instance
    socket.on('close', function(){
        // closed connection
        console.log('Drone Disconnected');
        console.log('CLOSED ' + HOST +': '+ PORT);
    });
}).listen(PORT, HOST);
console.log('OPENED ' + HOST +': '+ PORT);*/
