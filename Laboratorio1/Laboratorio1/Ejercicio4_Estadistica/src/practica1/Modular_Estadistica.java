package practica1;

import java.util.Scanner;

public class Modular_Estadistica {
	static double[] datos;

	public static double promedio() {
		double suma = 0;
		for (int i = 0; i < datos.length; i++) {
			suma += datos[i];
		}
		return suma / datos.length;
	}

	public static double desviacion() {
		double media = promedio();
		double suma = 0;
		for (int i = 0; i < datos.length; i++) {
			suma += Math.pow(datos[i] - media, 2);
		}
		return Math.sqrt(suma / (datos.length - 1));
	}

	public static void main(String[] args) {
		Scanner lee = new Scanner(System.in);
		datos = new double[10];
		System.out.print("Ingrese 10 números: ");
		for (int i = 0; i < 10; i++) {
			datos[i] = lee.nextDouble();
		}

		System.out.printf("El promedio es %.2f\n", promedio());
		System.out.printf("La desviación estándar es %.5f\n", desviacion());

		lee.close();
	}
}
