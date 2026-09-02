package practica1;

/*
 * EcuacionCuadratica
 * -a: double
 * -b: double
 * -c: double
 * +EcuacionCuadratica(a, b, c)
 * +getDiscriminante(): double
 * +getRaiz1(): double
 * +getRaiz2(): double
 */
public class EcuacionCuadratica {
	private double a;
	private double b;
	private double c;

	public EcuacionCuadratica(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double getDiscriminante() {
		return b * b - 4 * a * c;
	}

	public double getRaiz1() {
		double discriminante = getDiscriminante();
		if (discriminante < 0) {
			return 0;
		}
		return (-b + Math.sqrt(discriminante)) / (2 * a);
	}

	public double getRaiz2() {
		double discriminante = getDiscriminante();
		if (discriminante < 0) {
			return 0;
		}
		return (-b - Math.sqrt(discriminante)) / (2 * a);
	}
}
