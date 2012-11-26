# state.py


class state(object):
    """docstring for state"""

    def __init__(self, statemanager, name):
        super(state, self).__init__()
        self.sm = statemanager
        self.name = name

    def update(self, dt):
        pass

    def draw(self, screen):
        pass

    def keychange(self, itype, key, mod, unicode=u""):
        pass

    def mousechange(self, pos, rel, buttons):
        pass

    def mousebuttonchange(self, itype, pos, button):
        pass

    def event(self, ev):
        pass
