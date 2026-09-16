import math

class AlgebraVectorial:
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def __add__(self, otro):
        return AlgebraVectorial(self.x + otro.x, self.y + otro.y)

    def __sub__(self, otro):
        return AlgebraVectorial(self.x - otro.x, self.y - otro.y)

    def __mul__(self, otro):
        if isinstance(otro, AlgebraVectorial):
            return self.x * otro.x + self.y * otro.y
        return AlgebraVectorial(self.x * otro, self.y * otro)

    def __str__(self):
        return "({}, {})".format(self.x, self.y)

    def modulo(self):
        return math.sqrt(self.x**2 + self.y**2)

    def perpendicular_a(self, b):
        return abs((self + b).modulo() - (self - b).modulo()) < 0.000001

    def perpendicular_b(self, b):
        return abs((self - b).modulo() - (b - self).modulo()) < 0.000001

    def perpendicular_c(self, b):
        return self * b == 0

    def perpendicular_d(self, b):
        return abs((self + b).modulo()**2 -
                   (self.modulo()**2 + b.modulo()**2)) < 0.000001

    def paralelo_e(self, b):
        if b.x == 0 and b.y == 0:
            return False

        if b.x != 0:
            r = self.x / b.x
            return abs(self.y - r * b.y) < 0.000001

        return self.x == 0

    def paralelo_f(self, b):
        return self.x * b.y - self.y * b.x == 0

    def proyeccion(self, b):
        if b.modulo() == 0:
            return AlgebraVectorial()

        factor = (self * b) / (b.modulo()**2)
        return b * factor

    def componente(self, b):
        if b.modulo() == 0:
            return 0

        return (self * b) / b.modulo()


a = AlgebraVectorial(3, 4)
b = AlgebraVectorial(-4, 3)

print("Vector a:", a)
print("Vector b:", b)

print("a) Perpendicular:", a.perpendicular_a(b))
print("b) Perpendicular:", a.perpendicular_b(b))
print("c) Perpendicular:", a.perpendicular_c(b))
print("d) Perpendicular:", a.perpendicular_d(b))

print("e) Paralelo:", a.paralelo_e(b))
print("f) Paralelo:", a.paralelo_f(b))

print("g) Proyeccion de a sobre b:", a.proyeccion(b))
print("h) Componente de a en b:", a.componente(b))
