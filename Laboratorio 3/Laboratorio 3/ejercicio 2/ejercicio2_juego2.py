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

    def validaNumero(self, numero):
        return 0 <= numero <= 10

    def juega(self):
        self.reiniciaPartida()
        self.numeroAAdivinar = random.randint(0, 10)

        while True:
            print("\nAdivina un numero entre 0 y 10")
            numero = int(input("Ingresa un numero: "))

            if not self.validaNumero(numero):
                print("Numero no valido. Debe estar entre 0 y 10")
                continue

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


class JuegoAdivinaPar(JuegoAdivinaNumero):
    def validaNumero(self, numero):
        if 0 <= numero <= 10 and numero % 2 == 0:
            return True
        if 0 <= numero <= 10:
            print("Error: debes ingresar un numero par")
        return False


class JuegoAdivinaImpar(JuegoAdivinaNumero):
    def validaNumero(self, numero):
        if 0 <= numero <= 10 and numero % 2 != 0:
            return True
        if 0 <= numero <= 10:
            print("Error: debes ingresar un numero impar")
        return False


class Aplicacion:
    def main(self):
        juego1 = JuegoAdivinaNumero(3)
        juego1.juega()

        juego2 = JuegoAdivinaPar(3)
        juego2.juega()

        juego3 = JuegoAdivinaImpar(3)
        juego3.juega()


app = Aplicacion()
app.main()
