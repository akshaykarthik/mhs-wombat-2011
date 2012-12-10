import random
import math


def dice(maxnum):
    def ndice():
        return random.choice(range(1, maxnum))

d2 = dice(2)
d4 = dice(4)
d6 = dice(6)
d8 = dice(8)
d10 = dice(10)
d12 = dice(12)
d20 = dice(20)
d100 = dice(100)
