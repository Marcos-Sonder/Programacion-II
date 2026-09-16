import math

class MiPunto:
    def __init__(self, x=0, y=0):
        self.__x = x
        self.__y = y

    def getX(self):
        return self.__x

    def getY(self):
        return self.__y

    def distancia(self, x, y=None):
        if isinstance(x, MiPunto):
            dx = self.__x - x.getX()
            dy = self.__y - x.getY()
        else:
            dx = self.__x - x
            dy = self.__y - y

        return math.sqrt(dx**2 + dy**2)


punto1 = MiPunto()
punto2 = MiPunto(10, 30.5)

print("Punto 1:", punto1.getX(), punto1.getY())
print("Punto 2:", punto2.getX(), punto2.getY())
print("Distancia:", punto1.distancia(punto2))
