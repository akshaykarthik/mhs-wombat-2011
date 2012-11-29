# base boids simulation
# baseboids.py
try:
    import sys
    import random
    import math
    import pygame
    from socket import *
    from pygame.locals import *
except ImportError, err:
    print "couldn't load module. %s" % (err)
    sys.exit(2)

from engine import *


class base_boids(State):
    def __init__(self, sm):
        super(state, self).__init__()
        self.name = "base_boids"
        self.boids = []
        for i in range(10):
            for j in range(10):
                self.boids.append(Boid(200 + i * 100, 200 + j * 100))

    def update(self, dt):
        pygame.display.set_caption(self.name)
        for boid in self.boids:
            boid.update(dt, self.boids)

    def draw(self, screen):
        for boid in self.boids:
            boid.draw(screen)


class Boid:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.velocityX = random.randint(1, 10) / 10.0
        self.velocityY = random.randint(1, 10) / 10.0

    "Return the distance from another boid"
    def distance(self, boid):
        distX = self.x - boid.x
        distY = self.y - boid.y
        return math.sqrt(distX * distX + distY * distY)

    "Move closer to a set of boids"
    def moveCloser(self, boids):
        if len(boids) < 1:
            return

        # calculate the average distances from the other boids
        avgX = 0
        avgY = 0
        for boid in boids:
            if boid.x == self.x and boid.y == self.y:
                continue

            avgX += (self.x - boid.x)
            avgY += (self.y - boid.y)

        avgX /= len(boids)
        avgY /= len(boids)

        self.velocityX -= (avgX / 100)
        self.velocityY -= (avgY / 100)

    "Move with a set of boids"
    def moveWith(self, boids):
        if len(boids) < 1:
            return
        # calculate the average velocities of the other boids
        avgX = 0
        avgY = 0

        for boid in boids:
            avgX += boid.velocityX
            avgY += boid.velocityY

        avgX /= len(boids)
        avgY /= len(boids)

        # set our velocity towards the others
        self.velocityX += (avgX / 40)
        self.velocityY += (avgY / 40)

    def moveMouse(self):
        pos = pygame.mouse.get_pos()
        self.velocityX += (pos[0] - self.x)/200
        self.velocityY += (pos[1] - self.y)/200

    "Move away from a set of boids. This avoids crowding"
    def moveAway(self, boids, minDistance):
        if len(boids) < 1:
            return

        distanceX = 0
        distanceY = 0
        numClose = 0

        for boid in boids:
            distance = self.distance(boid)
            if distance < minDistance:
                numClose += 1
                xdiff = (self.x - boid.x)
                ydiff = (self.y - boid.y)

                if xdiff >= 0:
                    xdiff = math.sqrt(minDistance) - xdiff
                elif xdiff < 0:
                    xdiff = -math.sqrt(minDistance) - xdiff

                if ydiff >= 0:
                    ydiff = math.sqrt(minDistance) - ydiff
                elif ydiff < 0:
                    ydiff = -math.sqrt(minDistance) - ydiff

                distanceX += xdiff
                distanceY += ydiff

        if numClose == 0:
            return

        self.velocityX -= distanceX / 5
        self.velocityY -= distanceY / 5

    "Perform actual movement based on our velocity"
    def move(self):
        maxVelocity = 10
        if abs(self.velocityX) > maxVelocity or abs(self.velocityY) > maxVelocity:
            scaleFactor = maxVelocity / max(abs(self.velocityX), abs(self.velocityY))
            self.velocityX *= scaleFactor
            self.velocityY *= scaleFactor
        self.ox = self.clamp(self.x, 1024, 0)
        self.oy = self.clamp(self.y, 768, 0)
        self.x += self.velocityX
        self.y += self.velocityY



    def update(self, dt, boids):
        self.moveCloser(boids)
        self.moveWith(boids)
        self.moveAway(boids, 20)
        self.moveMouse()

        self.move()

    def clamp(self, val, max, min):
        return val%max + min

    def draw(self, screen):
        pygame.draw.circle(screen,
                (255, 255, 255),
                (int(self.clamp(self.x, 1024, 0)), int(self.clamp(self.y, 768, 0))),
                1, 1)
