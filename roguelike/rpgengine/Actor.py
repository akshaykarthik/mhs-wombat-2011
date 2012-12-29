from DataStructures import *


class Actor(object):

    max_str = 100
    max_dex = 100
    max_int = 100
    max_end = 100

    def __init__(self,
                position,
                health,
                istr,
                dex,
                iint,
                end):
        self.position = position
        self.health = health
        self.str = istr
        self.dex = dex
        self.int = iint
        self.end = end

    def isDead(self):
        return health < 0

    def damage(self, damage, damagetype):
        return damage

    def crit_damage(self, critdmg_mult=1.5):
        return (self.str / self.max_str) * critdmg_mult

    def strong_damage(self, damage_mult=1.5):
        return self.str * damage_mult

    def dodge_chance(self):
        return (self.dex) / self.max_dex

    def max_health(self, base, class_bonus=0, str_mult=1, end_mult=2):
        return (str_mult * self.str) + (end_mult * self.end) + class_bonus + base


