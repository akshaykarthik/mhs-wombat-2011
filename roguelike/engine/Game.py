# Game.py
import pygame
from pygame.locals import *
from State import State


class Game(object):
    """docstring for Game"""
    def __init__(self,
            name,
            screen_size=(1024, 768),
            fps=120,
            clock=None,
            screen=None):

        super(Game, self).__init__()
        self.name = name
        self.screen_size = screen_size
        self.current_state = None
        self.fps = fps

        self.states = dict()
        self._add_state(State(self, 'null'))

        pygame.init()
        pygame.display.set_caption(name)

        self.clock = clock or pygame.time.Clock()
        self.screen = screen or pygame.display.set_mode(screen_size)
        self.background_color = (0, 0, 0)

    def _add_state(self, st):
        if isinstance(st, State):
            self.states[st.name] = st
            if self.current_state is None:
                self.current_state = st.name
        else:
            raise TypeError("var passed in is not typeof state")

    def _switch(self, st):
        if st in self.states:
            self.current_state = st
            return True
        else:
            return False

    def setup(self):
        pass

    def preupdate(self):
        self.clock.tick(self.fps)
        self.dt = self.clock.get_time() * 0.001

    def update(self):
        self.states[self.current_state].update(self.dt)

    def postupdate(self):
        pass

    def predraw(self):
        self.screen.fill(self.background_color)

    def draw(self):
        self.states[self.current_state].draw(self.screen)

    def postdraw(self):
        pygame.display.flip()
