from collections import namedtuple


Attributes = namedtuple("Attributes", ["str", "con", "dex", "cha", "int", "wis"])
Race = namedtuple("Race", ["name", "alignment", "base_stats", "resist"])
Resist = namedtuple("Resist", ["normal", "fire", "water", "earth", "poison", "shock"])
Class = namedtuple("Class", ["type", "alignment", "attributes"])


class E_Alignment:
    Good = 0
    Neutral = 1
    Evil = 2


class E_Race:
    Human = Race("human", E_Alignment.Neutral,
        Attributes(50, 50, 50, 50, 50, 50),
        Resist(0, 0, 0, 0, 0, 0)
        )
    Dwarf = Race("dwarf", E_Alignment.Neutral,
        Attributes(50, 40, 80, 70, 40, 20),
        Resist(0, 0, 0, 0, 0, 0)
        )

    Elf = Race("elf", E_Alignment.Good,
        Attributes(30, 40, 30,  60, 70, 70),
        Resist(0, 0, 0, 0, 0, 0)
        )
    Gnome = Race("gnome", E_Alignment.Good,
        Attributes(30, 30, 50,  50, 70, 70),
        Resist(0, 0, 0, 0, 0, 0)
        )

    Giant = Race("giant", E_Alignment.Evil,
        Attributes(70, 70, 50, 50, 30, 30),
        Resist(0, 0, 0, 0, 0, 0)
        )
    Orc = Race("orc", E_Alignment.Evil,
        Attributes(70, 80, 40, 30, 60, 20),
        Resist(0, 0, 0, 0, 0, 0)
        )


class E_DamageType:
    Normal = 0
    Fire = 1
    Water = 2
    Earth = 3
    Poison = 4
    Shock = 5


class E_ClassType:
    NoType = 0
    Melee = 1
    Magic = 2
    Sneak = 4


class E_Class:
    Wanderer = Class(E_ClassType.NoType, E_Alignment.Neutral, Attributes(0, 0, 0, 0, 0, 0))

    Berzerker = Class(E_ClassType.Melee, E_Alignment.Evil, Attributes(70, 70, 0, 0, 0, 0))
    Warrior = Class(E_ClassType.Melee, E_Alignment.Neutral, Attributes(70, 70, 0, 0, 0, 0))
    Paladin = Class(E_ClassType.Melee, E_Alignment.Good, Attributes(70, 70, 0, 0, 0, 0))

    Warlock = Class(E_ClassType.Mage, E_Alignment.Evil, Attributes(0, 0, 0, 0, 70, 70))
    Mage = Class(E_ClassType.Mage, E_Alignment.Neutral, Attributes(0, 0, 0, 0, 70, 70))
    Wizard = Class(E_ClassType.Mage, E_Alignment.Good, Attributes(0, 0, 0, 0, 70, 70))

    Assasin = Class(E_ClassType.Sneak, E_Alignment.Evil, Attributes(0, 0, 70, 70, 0, 0))
    Rogue = Class(E_ClassType.Sneak, E_Alignment.Neutral, Attributes(0, 0, 70, 70, 0, 0))
    Archer = Class(E_ClassType.Sneak, E_Alignment.Good, Attributes(0, 0, 70, 70, 0, 0))
