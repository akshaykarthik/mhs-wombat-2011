import sys
sys.path.append("..")
from engine import State
from rpgengine.Map.Map import Map


class GameState(State):
    """docstring for GameState"""
    def __init__(self, game, name):
        super(GameState, self).__init__(game, name)
        self.map = Map(100, 100)

    def draw(screen):
        self.map.draw(screen)


