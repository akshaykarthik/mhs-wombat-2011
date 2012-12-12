
from Item import Item


class Potion(Item):
    def __init__(self, name, value, stat, healamount, charges = 1):
        super(Potion, self).__init__()
        self.name = name
        self.value = value
        self.stat = stat
        self.healamount = healamount

    def heal(self):

