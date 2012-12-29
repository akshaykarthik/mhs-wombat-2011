from Camera import Camera
from Tile import Tile


class Map(object):
    def __init__(self, l, w, cam=Camera()):
        self.l = l
        self.w = w
        self.data = [[Tile(i, j) for i in xrange(i)] for j in xrange(w)]

