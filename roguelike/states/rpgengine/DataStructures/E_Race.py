from Race import Race
from E_Alignment import E_Alignment
from Attributes import Attributes
from Resist import Resist


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
