from E_ClassType import E_ClassType
from E_Alignment import E_Alignment
from Class import Class
from Attributes import Attributes


class E_Class:
    Wanderer = Class(E_ClassType.NoType,
        E_Alignment.Neutral, Attributes(0, 0, 0, 0, 0, 0))

    Berzerker = Class(E_ClassType.Melee,
        E_Alignment.Evil, Attributes(70, 70, 0, 0, 0, 0))
    Warrior = Class(E_ClassType.Melee,
        E_Alignment.Neutral, Attributes(70, 70, 0, 0, 0, 0))
    Paladin = Class(E_ClassType.Melee,
        E_Alignment.Good, Attributes(70, 70, 0, 0, 0, 0))

    Warlock = Class(E_ClassType.Magic,
        E_Alignment.Evil, Attributes(0, 0, 0, 0, 70, 70))
    Mage = Class(E_ClassType.Magic,
        E_Alignment.Neutral, Attributes(0, 0, 0, 0, 70, 70))
    Wizard = Class(E_ClassType.Magic,
        E_Alignment.Good, Attributes(0, 0, 0, 0, 70, 70))

    Assasin = Class(E_ClassType.Sneak,
        E_Alignment.Evil, Attributes(0, 0, 70, 70, 0, 0))
    Rogue = Class(E_ClassType.Sneak,
        E_Alignment.Neutral, Attributes(0, 0, 70, 70, 0, 0))
    Archer = Class(E_ClassType.Sneak,
        E_Alignment.Good, Attributes(0, 0, 70, 70, 0, 0))
