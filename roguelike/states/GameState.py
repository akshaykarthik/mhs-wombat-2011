import sys
sys.path.append("..")
from engine import State


class GameState(State):
    """docstring for GameState"""
    def __init__(self, game, name):
        super(GameState, self).__init__(game, name)
