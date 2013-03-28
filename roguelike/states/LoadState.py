import sys
sys.path.append("..")
from engine import State


class LoadState(State):
    """docstring for LoadState"""
    def __init__(self, game, name):
        super(LoadState, self).__init__(game, name)
