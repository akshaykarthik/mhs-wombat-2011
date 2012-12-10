from DataStructures import *
import Weapon

import Dice

class Actor(object):
    def __init__(self,
                position,
                health,
                attributes=Attributes(50, 50, 50, 50, 50, 50),
                align=E_Alignment.Neutral,
                resist=Resist(0, 0, 0, 0, 0, 0)):
        super(Actor, self).__init__()
        self.x = position[0]
        self.y = position[1]
        self.maxhealth = health
        self.health = health
        self.align = align
        self.resist = resist
        self.race = 0
        self.weapon = Weapon.hands
        self.lvl = 0


    def isDead(self):
        return health < 0

    def damage(self, damage, damagetype):
        self.health -= (damage * (100 - self.resist[damagetype]) / 100)


    def attack(self):
        return self.weapon.roll()

