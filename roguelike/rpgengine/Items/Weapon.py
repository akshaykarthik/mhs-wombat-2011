from DataStructures import E_DamageType
from Dice import *
from collections import namedtuple

Weapon = namedtuple('Weapon', ['name', 'value', 'minlvl', 'roll', 'description'])


hand = Weapon('hand', -1, 0, lambda: d2() - 1,
    "your hands do {1d2} damage")

worn_wooden_staff = Weapon('worn wooden staff', 100, 0, lambda: 1 + d4(),
    "a worn wooden staff, broken by time, does {1 + 1d4} damage")

wooden_staff = Weapon('wooden staff', 150, 0, lambda: 2 + d4(),
    "a wooden staff made from twigs, does {2 + 1d4} damage")

rusty_iron_blade = Weapon('rusty iron blade', 150, 0, lambda: 2 + d3(),
    "a rusty iron blade used by children to practice their swordsmanship, does {2 + 1d3} damage")

iron_blade = Weapon('iron blade', 175, 0, lambda: 3 + d3(),
    "a new iron blade used to train army recruits, does {3 + 1d3} damage"),

shiny_iron_blade = Weapon('shiny iron blade', 200, 0, lambda: 4 + d3(),
    "a shiny iron blade which hasnt been used yet, it has that new sword smell, does {4 + 1d3} damage")

rusty_steel_blade = Weapon('rusty steel blade', 225, 1, lambda: 4 + d3(),
    "a rusty steel blade used by rich children to practice their swordsmanship, does {4 + 1d3} damage")

steel_blade = Weapon('steel blade', 250, 1, lambda: 5 + d3(),
    "a new steel blade used to train army officers, does {5 + 1d3} damage")

shiny_steel_blade = Weapon('shiny steel blade', 275, 1, lambda: 6 + d3(),
    "a shiny steel blade which hasnt been used yet, it has that new sword smell, does {6 + 1d3} damage")
