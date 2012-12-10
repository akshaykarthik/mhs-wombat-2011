import pygame
import sys
sys.path.append("..")
from engine import Game
from states import MenuState
from states import LoadState
from states import GameState
from engine.State import State


class Roguelike(Game):
    def __init__(self):
        super(Roguelike, self).__init__("Roguelike")
        self._add_state(MenuState(self, "menu"))
        self._add_state(LoadState(self, "load"))
        self._add_state(GameState(self, "game"))
        self._switch("menu")

