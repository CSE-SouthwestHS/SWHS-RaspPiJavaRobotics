var net = require('net');
var client = net.connect(5000, 'localhost');
setTimeout(push, 1000);
function push(){
    client.write("xy");
    client.end();
}