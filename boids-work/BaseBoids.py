#BaseBoids.py

import pygame
import pygame.key
from pygame.surface import Surface
from pygame.locals import *
from engine import Vector2
from engine import Game
from engine import utils

from Boid import Boid


class BaseBoids(Game):
    """docstring for BaseBoids"""

    def __init__(self):
        super(BaseBoids, self).__init__("BaseBoids")
        self.boids = []
        self.boids.append(Boid(Vector2(100, 100)))

        self.last_pressed = False
        self.pressed = False

        self.pressed_pos = Vector2()
        self.released_pos = Vector2()

        self.alignment_toggle = True
        self.cohesion_toggle = True
        self.separation_toggle = True
        self.follow_toggle = True

        self.boidsurface = Surface(self.screen_size)

    def draw(self):

        draw = utils.draw

        self.boidsurface.unlock()

        draw.setup(self.boidsurface)
        self.boidsurface.fill(self.background_color)
        for boid in self.boids:
            boid.draw(self.dt, self.boidsurface,
                not pygame.key.get_pressed()[K_q])
        self.screen.blit(self.boidsurface, (0, 0))
        draw.shutdown()

        self.boidsurface.lock()

        draw.setup(self.screen)
        draw.text("fps  : " + str(self.clock.get_fps()), (255, 255, 255), (100, 20))
        draw.text("dt   : " + str(self.dt), (255, 255, 255), (100, 40))

        draw.text("Alignment    (a): " + str(self.alignment_toggle), Boid._colors["acolor"], (100, 100))
        draw.text("Cohesion     (s): " + str(self.cohesion_toggle), Boid._colors["ccolor"], (100, 120))
        draw.text("Separation   (d): " + str(self.separation_toggle), Boid._colors["scolor"], (100, 140))
        draw.text("Follow Mouse (g): " + str(self.follow_toggle), Boid._colors["mousecolor"], (100, 160))

        if self.pressed:
            pygame.draw.line(self.screen, (255, 255, 255),
                self.pressed_pos, pygame.mouse.get_pos())

    def update(self):
        self.alignment_toggle = not pygame.key.get_pressed()[K_a]
        self.cohesion_toggle = not pygame.key.get_pressed()[K_s]
        self.separation_toggle = not pygame.key.get_pressed()[K_d]
        self.follow_toggle = not pygame.key.get_pressed()[K_g]

        if pygame.key.get_pressed()[K_ESCAPE]:
            pygame.event.post(pygame.event.Event(QUIT))

        if pygame.key.get_pressed()[K_r] and len(self.boids) > 0:
            self.boids.pop()

        if not pygame.key.get_pressed()[K_SPACE]:
            for boid in self.boids:
                boid.update(self.dt, self.boids,
                    self.alignment_toggle,
                    self.cohesion_toggle,
                    self.separation_toggle,
                    self.follow_toggle)

        if pygame.mouse.get_pressed()[0]:
            if self.pressed == False:
                self.pressed_pos = Vector2(pygame.mouse.get_pos()[0],
                    pygame.mouse.get_pos()[1])
                self.pressed = True
        else:
            if self.pressed:
                self.released_pos = Vector2(pygame.mouse.get_pos()[0],
                    pygame.mouse.get_pos()[1])
                self.boids.append(Boid(self.pressed_pos,
                    self.released_pos - self.pressed_pos))
            self.pressed = False
