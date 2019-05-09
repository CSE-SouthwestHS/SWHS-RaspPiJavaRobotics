//this is not the full front end, just a part of what we hope to implement for communication
var WebSocket = require('ws');
const connection = new WebSocket('ws://localhost:5000');
connection.onopen = function () {
  connection.emit('generic');
  connection.send('generic');
  connection.send('generic');
};
