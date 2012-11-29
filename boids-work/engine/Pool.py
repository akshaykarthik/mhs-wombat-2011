



class Pool(object):
    """ creates a pool of reusable objects.
    This allows there to only be a limited number of instances of an object,
    thus allowing better memory usage"""
    def __init__(self, object_count=0, create_func=0):
        self.pool = []
        self.objects = 0
        self.active_objects = 0
        self.create_func = create_func

        for i in xrange(object_count):
            self.__add_object()

    def __add(self, obj):
        self.objects += 1
        self.pool.append(obj)

    def __remove(self):
        self.objects -= 1
        return self.pool.pop()

    def __add_object(self):
        if self.create_func is not None:
            self.__add(self.create_func())

    def take(self):
        if self.pool.count <= 0:
            self.__add_object()
            return self.__remove()

        self.active_objects += 1
        return self.__remove()

    def put_back(self, obj):
        self.__add(obj)
        self.active_objects -= 1
