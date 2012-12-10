from collections import namedtuple


_race = namedtuple("_race", [
    "exp",
    "str",
    "int",
    "wis",
    "con",
    "cha",

    ])


class E_Alignment:
    Good = 0
    Neutral = 1
    Evil = 2


class E_DamageType:
    Normal = 0
    Fire = 1
    Water = 2
    Earth = 4
    Air = 8
    Poison = 16
    Shock = 32
