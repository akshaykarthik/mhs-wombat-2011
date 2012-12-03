#BaseBoids.py

import pygame
import pygame.key
from pygame.surface import Surface
from pygame.locals import *
from engine import Vector2
from engine import Game

from Boid import Boid


class BaseBoids(Game):
    """docstring for BaseBoids"""
    def __init__(self):
        super(BaseBoids, self).__init__("BaseBoids")
        self.debug_font = pygame.font.SysFont("monospace", 15)
        self.boids = []
        self.boids.append(Boid(Vector2(100, 100)))

        self.last_pressed = False
        self.pressed = False

        self.pressed_pos = Vector2()
        self.released_pos = Vector2()

        self.alignment_toggle = True
        self.cohesion_toggle = True
        self.separation_toggle = True
        self.random_toggle = True

        self.boidsurface = Surface(self.screen_size)

    def text(self, text, color, position):
        self.screen.blit(self.debug_font.render(
            text, 1, color), position)

    def draw(self):
        pygame.draw.circle(self.screen,
                (255, 255, 255),
                Vector2(100, 100),
                1,
                1)

        alignment_toggle = not pygame.key.get_pressed()[K_a]
        cohesion_toggle = not pygame.key.get_pressed()[K_s]
        separation_toggle = not pygame.key.get_pressed()[K_d]
        random_toggle = not pygame.key.get_pressed()[K_f]

        self.boidsurface.unlock()
        self.boidsurface.fill(self.background_color)
        for boid in self.boids:
            boid.draw(self.dt, self.boidsurface,
                not pygame.key.get_pressed()[K_q])
        self.screen.blit(self.boidsurface.convert(), (0, 0))
        self.boidsurface.lock()

        self.text("fps    : " + str(self.clock.get_fps()),
            (255, 255, 255), (100, 20))
        self.text("dt     : " + str(self.dt), (255, 255, 255), (100, 40))
        self.text("Randomness      (f): " + str(random_toggle),
            (255, 255, 255), (100, 80))
        self.text("Alignment      (a): " + str(alignment_toggle),
            (255, 0, 255), (100, 100))
        self.text("Cohesion       (s): " + str(cohesion_toggle),
            (0, 255, 255), (100, 120))
        self.text("Separation      (d): " + str(separation_toggle),
            (255, 255, 0), (100, 140))

        if self.pressed:
            pygame.draw.line(self.screen, (255, 255, 255),
                self.pressed_pos, pygame.mouse.get_pos())

    def update(self):
        alignment_toggle = not pygame.key.get_pressed()[K_a]
        cohesion_toggle = not pygame.key.get_pressed()[K_s]
        separation_toggle = not pygame.key.get_pressed()[K_d]
        random_toggle = not pygame.key.get_pressed()[K_f]

        if pygame.key.get_pressed()[K_ESCAPE]:
            pygame.event.post(pygame.event.Event(QUIT))

        if pygame.key.get_pressed()[K_r] and len(self.boids) > 0:
            self.boids.pop()

        for boid in self.boids:
            boid.update(self.dt, self.boids,
                alignment_toggle,
                cohesion_toggle,
                separation_toggle,
                random_toggle)

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

        pygame.time.wait(32)
