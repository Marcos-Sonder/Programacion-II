class Sensor:
    tipo = "Genérico"

    def __init__(self, id):
        self.id = id
        self.encendido = False

    def activar(self):
        self.encendido = True

    def desactivar(self):
        self.encendido = False


class Camara(Sensor):
    tipo = "Cámara"

    def __init__(self, id, resolucion):
        Sensor.__init__(self, id)
        self.resolucion = resolucion


class SensorLuzLaser(Sensor):
    tipo = "Luz Laser"

    def __init__(self, id, alcance):
        Sensor.__init__(self, id)
        self.alcance = alcance


class VehiculoAutonomo(Camara, SensorLuzLaser):
    tipo = "Vehículo Autónomo"

    def __init__(self, id, resolucion, alcance):
        Camara.__init__(self, id, resolucion)
        SensorLuzLaser.__init__(self, id, alcance)



vehiculo = VehiculoAutonomo("V001", "1080p", 200)

print("Tipo:", vehiculo.tipo)
print("ID:", vehiculo.id)
print("Resolución:", vehiculo.resolucion)
print("Alcance:", vehiculo.alcance)
print("Encendido:", vehiculo.encendido)

vehiculo.activar()
print("Después de activar:", vehiculo.encendido)

vehiculo.desactivar()
print("Después de desactivar:", vehiculo.encendido)
