var socket = io.connect('http://' + document.domain + ':' + location.port);


var joystick	= new VirtualJoystick({
	container	: document.getElementById('container'),
	mouseSupport	: true,
	limitStickTravel: true,
	stick_radius: 100
});

var lastX = 0;
var lastY = 0;

setInterval(function(){
    var outputEl = document.getElementById('result');
    var x = joystick.deltaX();
    var y = joystick.deltaY();
    if ((lastX != x) || (lastY != y)) {
        socket.emit('stick_movement', {x: parseInt(x), y: parseInt(y)});
        lastX = x;
        lastY = y;
    }
}, 1/60 * 1000);


socket.on('connect', function() {
    console.log('Websocket connected!');
});
