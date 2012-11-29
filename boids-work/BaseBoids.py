#BaseBoids.py

import pygame
import pygame.key
from pygame.locals import *
from engine import Vector2
from engine import Game


class BaseBoids(Game):
    """docstring for BaseBoids"""
    def __init__(self):
        super(BaseBoids, self).__init__("BaseBoids")

    def draw(self):
        pygame.draw.circle(self.screen,
                (255, 255, 255),
                Vector2(100, 100),
                1,
                1)

    def update(self):
        if pygame.key.get_pressed()[K_q]:
            pygame.event.post(pygame.event.Event(QUIT))
