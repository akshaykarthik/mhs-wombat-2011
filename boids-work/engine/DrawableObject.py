from BaseObject import BaseObject


class DrawableObject(BaseObject):
    """docstring for DrawableObject"""
    def __init__(self, update_order=0, draw_order=0):
        super(DrawableObject, self).__init__()
        BaseObject.__init__(self, update_order)
        self.draw_order = draw_order
        self.visible = True

    def show(self):
        self.is_enabled = True
        self.is_visible = True

    def hide(self):
        self.is_enabled = True
        self.is_visible = True

    def is_camera_visible(self, camera):
        return True

    def draw(self, dt, surface):
        pass
