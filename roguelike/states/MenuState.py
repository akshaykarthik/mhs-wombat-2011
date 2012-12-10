import sys
sys.path.append("..")
from engine import State
from engine import utils
import pygame
from pygame import Surface
from pygame.locals import *


class MenuState(State):
    def __init__(self, game, name):
        super(MenuState, self).__init__(game, name)

    def draw(self, scr):
        draw = utils.draw
        draw.setup(scr)
        draw.text(str(self.game.clock.get_fps()), (255, 255, 255), (0, 0))
        draw.shutdown()

