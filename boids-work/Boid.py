import math
from engine import Vector2
from engine import DrawableObject
from engine import Utils
import random
import pygame
from pygame.locals import *


class Boid(DrawableObject):

    def __init__(self, pos=Vector2(), vel=Vector2()):
        super(Boid, self).__init__()
        self.pos = pos
        self.vel = vel

        self.neighborDist = 200
        self.minsep = 50
        self.maxvel = 100

        self.neighborcount = 0
        self.cohesion_point = Vector2()
        self.alignforce = Vector2()
        self.cohesionforce = Vector2()
        self.separateforce = Vector2()

    def subupdate(self, boid):
        diff = self.pos - boid.pos
        dist = diff.length()
        if (0 < dist < self.neighborDist):
            self.cohesion_point += (boid.pos)
            self.alignforce += (boid.vel)
            if dist > self.minsep:
                diff /= dist
            self.separateforce += diff
            self.neighborcount += 1

    def update(self, dt, boids, at, ct, st, rt):
        self.pos.x = Utils.loop(self.pos.x, 0, 1024)
        self.pos.y = Utils.loop(self.pos.y, 0, 768)
        self.alignforce.reset()
        self.cohesionforce.reset()
        self.separateforce.reset()
        self.cohesion_point.reset()
        self.neighborcount = 0
        loopcount = 0

        map(self.subupdate, boids)

        if self.neighborcount > 0:
            self.cohesion_point /= self.neighborcount
            self.alignforce.normalize()
            self.alignforce *= self.neighborcount
            self.cohesionforce = self.cohesion_point - self.pos

        if at:
            self.vel += self.alignforce
        if ct:
            self.vel += self.cohesionforce
        if st:
            self.vel += self.separateforce

        self.vel.x = Utils.clamp(self.vel.x, -self.maxvel, self.maxvel)
        self.vel.y = Utils.clamp(self.vel.y, -self.maxvel, self.maxvel)
        if rt:
            self.vel.x += random.randint(-1, 1)
            self.vel.y += random.randint(-1, 1)

        self.pos += (self.vel * dt)

        return loopcount

    def draw(self, dt, screen, debug=True):
        pos = self.pos.to_int()
        color = (255, 255, 255)

        pygame.draw.circle(screen, color, pos, 5, 5)
        #pygame.draw.circle(screen, (0, 255, 255), self.cohesion.avg.to_int(), 20, 5)

        if debug:
            pygame.draw.circle(screen, (128, 128, 128), pos,
                    self.neighborDist, 1)

            pygame.draw.circle(screen, (128, 128, 0), pos,
                    self.minsep, 1)

            pygame.draw.line(screen, (255, 255, 255), pos, pos + self.vel)

            pygame.draw.line(screen, (255, 0, 255), pos,
                pos + self.alignforce)

            pygame.draw.line(screen, (0, 255, 255), pos,
                pos + self.cohesionforce)

            pygame.draw.circle(screen, (0, 255, 255), self.cohesion_point.to_int(),
                5, 5)
            pygame.draw.line(screen, (255, 255, 0), pos,
                pos + self.separateforce)

        # pygame.draw.line(screen, (128, 128, 128), pos,
        #     pos + self.alignforce)
        # pygame.draw.line(screen, (128, 128, 128), pos + self.alignforce,
        #     pos + self.alignforce + self.cohesionforce)
        # pygame.draw.line(screen, (128, 128, 128),
        #     pos + self.alignforce + self.cohesionforce,
        #     pos + self.alignforce + self.cohesionforce + self.separateforce
        #     )


