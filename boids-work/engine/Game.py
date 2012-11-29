# Game.py
import pygame
from pygame.locals import *


class Game(object):
    """docstring for Game"""
    def __init__(self,
            name,
            screen_size=(1024, 768),
            clock=None,
            screen=None):

        super(Game, self).__init__()
        self.name = name
        self.screen_size = screen_size

        pygame.init()
        pygame.display.set_caption(name)

        if clock is None:
            self.clock = pygame.time.Clock()
        else:
            self.clock = clock

        if screen is None:
            self.screen = pygame.display.set_mode(screen_size)
        else:
            self.screen = screen

        self.states = []
        self.current_state = None

        self.background_color = (0, 0, 0)

    def setup(self):
        pass

    def preupdate(self):
        self.clock.tick()
        self.dt = self.clock.get_time()

    def update(self):
        if self.current_state is not None:
            if self.current_state in self.states:
                self.states[self.current_state].update(self.dt)

    def postupdate(self):
        pass

    def draw(self):
        self.__setup_draw()
        if self.current_state is not None:
            if self.current_state in self.states:
                self.states[self.current_state].update(self.dt)
        self.__finish_draw()

    def predraw(self):
        self.screen.fill(self.background_color)

    def postdraw(self):
        pygame.display.flip()
