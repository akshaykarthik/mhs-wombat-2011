class Vector2(object):
    def __version__():
        return '1.01'

    """basic vector2 class that can be used in place of tuples"""
    def __init__(self, ix=0.0, iy=0.0):
        super(Vector2, self).__init__()
        self.x = ix
        self.y = iy

    def __add__(self, other):
        if isinstance(other, Vector2):
            new_vec = Vector2()
            new_vec.x = self.x + other.x
            new_vec.y = self.y + other.y
            return new_vec
        else:
            raise TypeError("other must be of type Vector2")

    def __radd__(self, other):
        return self.__add__(other)

    def __sub__(self, other):
        if isinstance(other, Vector2):
            new_vec = Vector2()
            new_vec.x = self.x - other.x
            new_vec.y = self.y - other.y
            return new_vec
        else:
            raise TypeError("other must be of type Vector2")

    def __rsub__(self, other):
        return self.__sub__(other)

    def __mul__(self, value):
        if isinstance(value, numbers.Number):
            new_vec = self.copy()
            new_vec.x = new_vec.x * value
            new_vec.y = new_vec.y * value
            return new_vec
        else:
            raise TypeError("value must be a number.")

    def __rmul__(self, value):
        return self.__mul__(value)

    def __div__(self, value):
        if isinstance(value, numbers.Number):
            if not(value == 0):
                new_vec = self.copy()
                new_vec.x /= value
                new_vec.y /= value
                return new_vec
            else:
                raise ZeroDivisionError("Cannot divide by zero.")
        else:
            raise TypeError("value must be a number.")

    def __rdiv__(self, value):
        return self.__div__(value)

    def __eq__(self, other):
        """Check to see if two Vector2 objects are equal"""
        if isinstance(other, Vector2):
            if self.x == other.x    \
           and self.y == other.y:
                return True
        else:
            raise TypeError("other must be of type Vector2")

        return False

    def __neg__(self):
        return Vector2(-self.x, -self.y)

    def __getitem__(self, index):
        if index > 1:
            raise IndexError("Index must be less than 2")

        if index == 0:
            return self.x
        else:
            return self.y

    def __setitem__(self, index, value):
        if index > 1:
            raise IndexError("Index must be less than 2")

        if index == 0:
            self.x = value
        else:
            self.y = value

    def __str__(self):
        return "<Vector2> [ " + str(self.x) + ", " + str(self.y) + " ]"

    def __len__(self):
        return 2

    @staticmethod
    def zero():
        """Returns a Vector2 with all attributes set to 0"""
        return Vector2(0, 0)

    @staticmethod
    def one():
        """Returns a Vector2 with all attribures set to 1"""
        return Vector2(1, 1)

    def copy(self):
        """Create a copy of this Vector"""
        new_vec = Vector2()
        new_vec.x = self.x
        new_vec.y = self.y
        return new_vec

    def length(self):
        """Gets the length of this Vector"""
        return math.sqrt((self.x * self.x) + (self.y * self.y))

    def normalize(self):
        """Gets the normalized Vector"""
        length = self.length()
        if length > 0:
            self.x /= length
            self.y /= length
        else:
            print "Length 0, cannot normalize."

    def normalize_copy(self):
        """Create a copy of this Vector, normalize it, and return it."""
        vec = self.copy()
        vec.normalize()
        return vec

    @staticmethod
    def distance(vec1, vec2):
        """Calculate the distance between two Vectors"""
        if isinstance(vec1, Vector2)   \
        and isinstance(vec2, Vector2):
            dist_vec = vec2 - vec1
            return dist_vec.length()
        else:
            raise TypeError("vec1 and vec2 must be Vector2's")

    @staticmethod
    def dot(vec1, vec2):
        """Calculate the dot product between two Vectors"""
        if isinstance(vec1, Vector2)   \
        and isinstance(vec2, Vector2):
            return ((vec1.x * vec2.x) + (vec1.y * vec2.y))
        else:
            raise TypeError("vec1 and vec2 must be Vector2's")

    @staticmethod
    def angle(vec1, vec2):
        """Calculate the angle between two Vector2's"""
        dotp = Vector2.dot(vec1, vec2)
        mag1 = vec1.length()
        mag2 = vec2.length()
        result = dotp / (mag1 * mag2)
        return math.acos(result)

    @staticmethod
    def lerp(vec1, vec2, time):
        """Lerp between vec1 to vec2 based on time. Time is clamped between
            0 and 1."""
        if isinstance(vec1, Vector2)    \
        and isinstance(vec2, Vector2):
            #Clamp the time value into the 0-1 range.
            if time < 0:
                time = 0
            elif time > 1:
                time = 1

            x_lerp = vec1[0] + time * (vec2[0] - vec1[0])
            y_lerp = vec1[1] + time * (vec2[1] - vec1[1])
            return Vector2(x_lerp, y_lerp)
        else:
            raise TypeError("Objects must be of type Vector2")

    @staticmethod
    def from_polar(degrees, magnitude):
        """Convert polar coordinates to Carteasian Coordinates"""
        vec = Vector2()
        vec.x = math.cos(math.radians(degrees)) * magnitude

        #Negate because y in screen coordinates points down, oppisite from what is
        #expected in traditional mathematics.
        vec.y = -math.sin(math.radians(degrees)) * magnitude
        return vec

    @staticmethod
    def component_mul(vec1, vec2):
        """Multiply the components of the vectors and return the result."""
        new_vec = Vector2()
        new_vec.x = vec1.x * vec2.x
        new_vec.y = vec1.y * vec2.y
        return new_vec

    @staticmethod
    def component_div(vec1, vec2):
        """Divide the components of the vectors and return the result."""
        new_vec = Vector2()
        new_vec.x = vec1.x / vec2.x
        new_vec.y = vec1.y / vec2.y
        return new_vec
