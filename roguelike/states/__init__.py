

__all__ = []


def lib_import(str):
    __all__.append(str)


lib_import("GameState")
from GameState import GameState

lib_import("MenuState")
from MenuState import MenuState

lib_import("LoadState")
from LoadState import LoadState
