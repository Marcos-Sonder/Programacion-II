import math

class Vector3D:
    def __init__(self, a1=0, a2=0, a3=0):
        self.a1 = a1
        self.a2 = a2
        self.a3 = a3

    def __add__(self, otro):
        return Vector3D(
            self.a1 + otro.a1,
            self.a2 + otro.a2,
            self.a3 + otro.a3
        )

    def __mul__(self, otro):
        if isinstance(otro, Vector3D):
            return self.a1 * otro.a1 + self.a2 * otro.a2 + self.a3 * otro.a3
        return Vector3D(
            self.a1 * otro,
            self.a2 * otro,
            self.a3 * otro
        )

    def __truediv__(self, numero):
        return Vector3D(
            self.a1 / numero,
            self.a2 / numero,
            self.a3 / numero
        )

    def __xor__(self, otro):
        return Vector3D(
            self.a2 * otro.a3 - self.a3 * otro.a2,
            self.a3 * otro.a1 - self.a1 * otro.a3,
            self.a1 * otro.a2 - self.a2 * otro.a1
        )

    def longitud(self):
        return math.sqrt(
            self.a1**2 + self.a2**2 + self.a3**2
        )

    def normal(self):
        return self / self.longitud()

    def __str__(self):
        return "({}, {}, {})".format(self.a1, self.a2, self.a3)


a = Vector3D(1, 2, 3)
b = Vector3D(4, 5, 6)

print("Vector a:", a)
print("Vector b:", b)

print("a) Suma:", a + b)
print("b) Escalar por vector:", a * 2)
print("c) Longitud de a:", a.longitud())
print("d) Normal de a:", a.normal())
print("e) Producto escalar:", a * b)
print("f) Producto vectorial:", a ^ b)
