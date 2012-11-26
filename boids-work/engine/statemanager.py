# statemanager.py

from state import state


class statemanager(object):
    """docstring for statemanager"""
    def __init__(self):
        super(statemanager, self).__init__()
        self.states = dict()
        self.cur = None

        self.states['null'] = state(self, 'null')
        self.cur = 'null'

    def add_state(self, st):
        if isinstance(st, state):
            self.states[st.name] = st
            if self.cur is None:
                self.cur = st.name
        else:
            raise TypeError("var passed in is not typeof state")

    def switch(self, str):
        if str in self.states:
            self.cur = str

    def update(self, dt):
        self.states[self.cur].update(dt)

    def draw(self, screen):
        self.states[self.cur].draw(screen)

    def keychange(self, itype, key, mod, unicode=u""):
        self.states[self.cur].keychange(itype, key, mod, unicode=u"")

    def mousechange(self, pos, rel, buttons):
        self.states[self.cur].mousechange(pos, rel, buttons)

    def mousebuttonchange(self, itype, pos, button):
        self.states[self.cur].mousebuttonchange(itype, pos, button)

    def event(self, ev):
        self.states[self.cur].event(ev)
