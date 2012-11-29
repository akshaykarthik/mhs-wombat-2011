## main.py

import pygame
from pygame.locals import *
from BaseBoids import BaseBoids


def main():
    game = BaseBoids()
    game.setup()
    while True:
        for event in pygame.event.get():
            if event.type == QUIT:
                return

        game.preupdate()
        game.update()
        game.postupdate()

        game.predraw()
        game.draw()
        game.postdraw()


if __name__ == '__main__':
    main()
