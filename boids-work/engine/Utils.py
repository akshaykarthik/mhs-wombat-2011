# base.py
from math import *


class Utils(object):

    @staticmethod
    def sign(val):
        """
        Returns the sign of a number.
        """
        if val > 0:
            return 1
        elif val < 0:
            return -1

        return 0

    @staticmethod
    def clamp(val, low, high):
        return max(min(val, high), low)

    @staticmethod
    def loop(val, low, high):
        return (val % (high - low) + low)
