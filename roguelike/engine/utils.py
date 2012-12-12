# base.py
from math import *
import pygame
from pygame.locals import *
import pymunk as pm
from contextlib import contextmanager


class utils(object):

    class math(object):

        @staticmethod
        def sign(val):
            """
            Returns the sign of a number.
            """
            if val > 0:
                return 1
            elif val < 0:
                return -1

            return 0

        @staticmethod
        def clamp(val, low, high):
            return max(min(val, high), low)

        @staticmethod
        def loop(val, low, high):
            return (val % (high - low) + low)

        @staticmethod
        def color(name):
            return pygame.Color(name)

    class draw(object):
        _screen = None
        _font = None

        @staticmethod
        @contextmanager
        def using(scr, font=None):
            utils.draw._setup(scr, font)
            yield
            utils.draw._shutdown()

        @staticmethod
        def _setup(screen, font=None):
            """sets up a screen"""
            utils.draw._screen = screen
            if font is None:
                utils.draw._font = pygame.font.SysFont("monospace", 15)

        @staticmethod
        def _shutdown():
            utils.draw._screen = None
            utils.draw._font = None

        @staticmethod
        def circle(color, point, radius, width=1):
            """color, point (x,y), radius, width"""
            pygame.draw.circle(utils.draw._screen, color, (int(point[0]), int(point[1])),
                int(radius), width)

        @staticmethod
        def text(text, color, position):
            utils.draw._screen.blit(utils.draw._font.render(
                text, 1, color), position)

        @staticmethod
        def line(color, start, end):
            pygame.draw.line(utils.draw._screen, color, start, end)

    class keyboard(object):
        def pressed(key):
            return key

        def released(key):
            return key

        def held(key):
            return key
