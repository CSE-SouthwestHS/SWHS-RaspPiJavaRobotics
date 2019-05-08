//src="/socket.io/socket.io.js"
var WebSocket = require('ws');
const connection = new WebSocket('ws://localhost:5000');

connection.onopen = function () {
  connection.emit('generic');
  connection.send('generic');
  connection.send('generic');
};
