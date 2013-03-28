# rpgengine.__init__.py


__all__ = []


def lib_import(str):
    __all__.append(str)

lib_import("Actor")
from Actor import Actor
