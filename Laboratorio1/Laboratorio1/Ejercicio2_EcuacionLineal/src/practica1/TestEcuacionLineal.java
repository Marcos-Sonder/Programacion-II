package practica1;

import java.util.Scanner;

public class TestEcuacionLineal {
	public static void main(String[] args) {
		Scanner lee = new Scanner(System.in);
		System.out.print("Ingrese a, b, c, d, e, f: ");
		double a = lee.nextDouble();
		double b = lee.nextDouble();
		double c = lee.nextDouble();
		double d = lee.nextDouble();
		double e = lee.nextDouble();
		double f = lee.nextDouble();

		EcuacionLineal ecuacion = new EcuacionLineal(a, b, c, d, e, f);

		if (ecuacion.tieneSolucion()) {
			System.out.println("x = " + ecuacion.getX() + ", y = " + ecuacion.getY());
		} else {
			System.out.println("La ecuación no tiene solución");
		}

		lee.close();
	}
}
