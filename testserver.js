var net = require('net');
var client = net.connect(5000, '10.55.160.1');
setTimeout(push, 1000);
function push(){
    client.write("xy");
    client.end();
}