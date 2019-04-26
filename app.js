var app = require('http').createServer(handler),
    io = require('socket.io')(app),
    url = require('url'),
    fs = require('fs'),
    x = 2;
//var startUpCode = Java.type();
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
  // If we recieved a command from a client
  socket.on('connection', function(data){
	  transmitterData = data["transmitterData"];
    var coordinates = transmitterData.split(',', 2),
        x = coordinates[0],
        y = coordinates[1];
    console.log(x);
    console.log(y);
    print (x+','+y);
    // Set a timer for when we should stop
    setTimeout(function(){
      socket.emit('connection');
    }, 0);
  });
});
