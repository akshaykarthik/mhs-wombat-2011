from collections import namedtuple


class Item(namedtuple('Item', ['name', 'value'])):
    def is_item(obj):
        return hasattr(obj, 'name') and hasattr(obj, 'value')
