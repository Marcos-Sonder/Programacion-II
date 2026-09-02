package practica1;

import java.util.Random;

public class TestCronometro {
	public static void main(String[] args) {
		int n = 100000;
		int[] numeros = new int[n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			numeros[i] = rand.nextInt(1000000);
		}

		Cronometro cronometro = new Cronometro();
		cronometro.inicia();
		ordenarPorSeleccion(numeros);
		cronometro.detener();

		System.out.println("Tiempo de ordenación: " + cronometro.lapsoDeTiempo() + " ms");
	}

	public static void ordenarPorSeleccion(int[] arreglo) {
		for (int i = 0; i < arreglo.length - 1; i++) {
			int menor = i;
			for (int j = i + 1; j < arreglo.length; j++) {
				if (arreglo[j] < arreglo[menor]) {
					menor = j;
				}
			}
			int temp = arreglo[i];
			arreglo[i] = arreglo[menor];
			arreglo[menor] = temp;
		}
	}
}
