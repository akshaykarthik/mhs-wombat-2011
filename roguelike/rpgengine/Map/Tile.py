import sys
sys.path.append('..')
from engine import utils

class TileType:
    Null = 0
    Wall = 1
    Floor = 2


class Tile(object):
    def __init__(self, x, y, wall=False, itype=TileType.Null):
        super(Tile, self).__init__()
        self.x = x
        self.y = y
        self.wall = wall

    def draw(camera):
        pass
