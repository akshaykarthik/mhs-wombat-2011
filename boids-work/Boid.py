from engine import Vector2
from engine import DrawableObject
from engine import utils
import pygame
from pygame.locals import *


class Boid(DrawableObject):
    _colors = {
        'boidcolor': pygame.Color('white'),
        'neighborcolor': pygame.Color('grey'),
        'acolor': pygame.Color('purple'),
        'ccolor': pygame.Color('cyan'),
        'scolor': pygame.Color('yellow'),
        'srangecolor': pygame.Color('lightyellow'),
        'mousecolor': pygame.Color('lightblue')
    }

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
        self.fleeforce = Vector2()

    def subupdate(self, boid):
        diff = self.pos - boid.pos
        dist = diff.length()
        if (0 < dist < self.neighborDist):
            self.cohesion_point += (boid.pos)
            self.alignforce += (boid.vel)
            if dist > self.minsep:
                diff /= dist
            self.separateforce += diff * 2
            self.neighborcount += 1

    def update(self, dt, boids, at, ct, st, fm):
        self.pos.x = utils.math.loop(self.pos.x, 0, 1024)
        self.pos.y = utils.math.loop(self.pos.y, 0, 768)
        self.alignforce.reset()
        self.cohesionforce.reset()
        self.separateforce.reset()
        self.cohesion_point.reset()
        self.fleeforce.reset()
        self.neighborcount = 0

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
        if fm:
            mos = pygame.mouse.get_pos()
            mos = Vector2(mos[0], mos[1])
            if Vector2.dist(mos, self.pos) < self.neighborDist:
                self.fleeforce = (mos - self.pos)
                self.vel += self.fleeforce

        self.vel.x = utils.math.clamp(self.vel.x, -self.maxvel, self.maxvel)
        self.vel.y = utils.math.clamp(self.vel.y, -self.maxvel, self.maxvel)
        self.pos += (self.vel * dt)

    def draw(self, dt, screen, debug=True):
        draw = utils.draw
        pos = self.pos.to_int()

        draw.circle(self._colors["boidcolor"], pos, 5, 5)

        if debug:
            draw.circle(self._colors["neighborcolor"], pos, self.neighborDist)

            draw.circle(self._colors["srangecolor"], pos, self.minsep)

            draw.line(self._colors["boidcolor"], pos, pos + self.vel)
            draw.line(self._colors["acolor"], pos, pos + self.alignforce)
            draw.line(self._colors["ccolor"], pos, pos + self.cohesionforce)
            draw.line(self._colors["scolor"], pos, pos + self.separateforce)
            draw.line(self._colors["mousecolor"], pos, pos + self.fleeforce)

            draw.circle(self._colors["ccolor"], self.cohesion_point, 2, 2)


