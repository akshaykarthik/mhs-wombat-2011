## main.py
try:
    import sys
    import random
    import math
    import os
    import getopt
    import pygame
    from socket import *
    from pygame.locals import *
except ImportError, err:
    print "couldn't load module. %s" % (err)
    sys.exit(2)

from engine import *
from engine.statemanager import *

import base_boids


def main():
    pygame.init()
    clock = pygame.time.Clock()

    screen = pygame.display.set_mode((1024, 768))
    pygame.display.set_caption('boids work')

    sm = statemanager()
    sm.add_state(base_boids.base_boids(sm))
    sm.switch("base_boids")

    while True:
        for event in pygame.event.get():
            if event.type == QUIT:
                return
            elif event.type == KEYDOWN:  # unicode, key, mod
                sm.keychange("keydown", event.key, event.mod, event.unicode)
            elif event.type == KEYUP:  # key, mod
                sm.keychange("keyup", event.key, event.mod)
            elif event.type == MOUSEMOTION:  # pos, rel, buttons
                sm.mousechange(event.pos, event.rel, event.buttons)
            elif event.type == MOUSEBUTTONUP:  # pos, button
                sm.mousebuttonchange("up", event.pos, event.buttons)
            elif event.type == MOUSEBUTTONDOWN:  # pos, button
                sm.mousebuttonchange("down", event.pos, event.buttons)
            else:
                sm.event(event)
        clock.tick()
        sm.update(clock.get_time())
        screen.fill((0,0,0))
        sm.draw(screen)
        pygame.display.flip()


if __name__ == '__main__':
    main()
