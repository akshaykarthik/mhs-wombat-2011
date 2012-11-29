# state.py


class State(object):
    """docstring for state"""

    def __init__(self, statemanager, name):
        super(State, self).__init__()
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


class StateManager(object):
    """docstring for statemanager"""
    def __init__(self):
        super(StateManager, self).__init__()
        self.states = dict()
        self.cur = None

        self.states['null'] = State(self, 'null')
        self.cur = 'null'

    def add_state(self, st):
        if isinstance(st, State):
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
