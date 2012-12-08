from Vector2 import Vector2


class BaseObject(object):
    __object_number = 0

    """docstring for BaseObject"""
    def __init__(self, update_order=0):
        super(BaseObject, self).__init__()
        self.update_order = update_order

        self.obj_id = self.__object_number
        BaseObject.__object_number += 1

        self.is_enabled = True
        self.is_static = True

        self.pos = Vector2()

        self.tags = ["object"]

    def __str__(self):
        return ("{ Object: \n\tEnabled:" + str(self.is_enabled) +
            "\n\t Tags:" + str(self.tags) + "\n}")

    def update(self, dt):
        pass
