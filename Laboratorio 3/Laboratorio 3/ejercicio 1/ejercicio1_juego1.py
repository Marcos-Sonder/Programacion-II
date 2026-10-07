import random

class Juego:
    def __init__(self, numeroDeVidas):
        self.numeroDeVidas = numeroDeVidas
        self.record = 0
        self.vidasIniciales = numeroDeVidas

    def reiniciaPartida(self):
        self.numeroDeVidas = self.vidasIniciales

    def actualizaRecord(self):
        if self.numeroDeVidas > self.record:
            self.record = self.numeroDeVidas

    def quitaVida(self):
        self.numeroDeVidas -= 1
        return self.numeroDeVidas > 0


class JuegoAdivinaNumero(Juego):
    def __init__(self, numeroDeVidas):
        super().__init__(numeroDeVidas)
        self.numeroAAdivinar = 0

    def juega(self):
        self.reiniciaPartida()
        self.numeroAAdivinar = random.randint(0, 10)

        while True:
            print("Adivina un numero entre 0 y 10")
            numero = int(input("Ingresa un numero: "))

            if numero == self.numeroAAdivinar:
                print("Acertaste!!")
                self.actualizaRecord()
                break

            quedanVidas = self.quitaVida()
            if quedanVidas:
                if numero < self.numeroAAdivinar:
                    print("El numero a adivinar es mayor")
                else:
                    print("El numero a adivinar es menor")
                print("Te quedan", self.numeroDeVidas, "vidas")
            else:
                print("Ya no tienes vidas")
                print("El numero era:", self.numeroAAdivinar)
                break


class Aplicacion:
    def main(self):
        juego = JuegoAdivinaNumero(3)
        juego.juega()


app = Aplicacion()
app.main()
