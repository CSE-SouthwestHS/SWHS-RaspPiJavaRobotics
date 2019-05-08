var net = require('net'),
    PORT = 5000,
    IP = '10.55.162.1',
    Connected = false,
    client = net.connect(PORT, IP);

if (Connected == false){
    client.on('connect', function() {
        Connected = true;
        client.write('Client Connected');
        client.end();
    });
}
client.on('data', function(data) {
        console.log(data.toString());
});
client.on('close', function() {
        console.log('Connection Closed');
});
client.on('error', function(err)
{
    console.log("Connection Failed");
});

function push(){
    client.write('X,Y');
    client.end();
}
