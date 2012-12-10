import pygame
from pygame.locals import *
from Roguelike import Roguelike

import cProfile
import pstats


def main():
    game = Roguelike()
    game.setup()
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == QUIT:
                running = False

        game.preupdate()
        game.update()
        game.postupdate()
        game.predraw()
        game.draw()
        game.postdraw()

if __name__ == '__main__':
    cProfile.run('main()', "perf.log")
    p = pstats.Stats("perf.log")
    p.sort_stats("time").print_stats()
