from flask import Flask, render_template, request
from flask_socketio import SocketIO, emit
from motor import Motor

app = Flask(__name__)
socketio = SocketIO(app)
left_motor = Motor(1)
right_motor = Motor(2)


@app.route('/')
def index():
    return render_template('index.html')


@socketio.on('stick_movement')
def stick_movement(data):
    print(data)
    # left_motor.set_speed(int(data['x'] + data['y']))
    # right_motor.set_speed(int(-1 * data['x'] + data['y']))


if __name__ == '__main__':
    socketio.run(app, debug=True)
