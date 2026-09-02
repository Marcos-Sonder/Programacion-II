package practica1;

import java.util.Scanner;

public class TestEstadistica {
	public static void main(String[] args) {
		Scanner lee = new Scanner(System.in);
		double[] datos = new double[10];
		System.out.print("Ingrese 10 números: ");
		for (int i = 0; i < 10; i++) {
			datos[i] = lee.nextDouble();
		}

		Estadistica estadistica = new Estadistica(datos);
		System.out.printf("El promedio es %.2f\n", estadistica.promedio());
		System.out.printf("La desviación estándar es %.5f\n", estadistica.desviacion());

		lee.close();
	}
}
