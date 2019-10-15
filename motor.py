class Motor(object):
    def __init__(self, port):
        self.port = port

    def set_speed(self, speed: int):
        # make motor move at speed
        # if 100 >= speed >= -100:
        print(f"motor {self.port} moving at {speed}")
