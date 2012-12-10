from DataStructures import *
from Dice import *


def make_weapon(name, classtype, damage, element_type):
    class wpn(object):
        def __init__(self):
            self.name = name
            self.classtype = classtype
            self.damage = damage
            self.element_type = element_type

        def roll():
            return (damage(), element_type)

hands = make_weapon("hand", E_Class.Wanderer,
    lambda: d2(),
    E_DamageType.Normal)

shiny_iron_axe = make_weapon("shiny iron axe", E_Class.Wanderer,
    lambda: 3 + d4(),
    E_DamageType.Normal)

iron_axe = make_weapon("iron axe", E_Class.Wanderer,
    lambda: 2 + d4(),
    E_DamageType.Normal)

rusty_iron_axe = make_weapon("rusty iron axe", E_Class.Wanderer,
    lambda: 1 + d4(),
    E_DamageType.Normal)
