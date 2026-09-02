package practica1;

/*
 * Cronometro
 * -inicia: long
 * -finaliza: long
 * +Cronometro()
 * +inicia(): void
 * +detener(): void
 * +lapsoDeTiempo(): long
 * +getInicia(): long
 * +getFinaliza(): long
 */
public class Cronometro {
	private long inicia;
	private long finaliza;

	public Cronometro() {
		inicia = System.currentTimeMillis();
	}

	public void inicia() {
		inicia = System.currentTimeMillis();
	}

	public void detener() {
		finaliza = System.currentTimeMillis();
	}

	public long lapsoDeTiempo() {
		return finaliza - inicia;
	}

	public long getInicia() {
		return inicia;
	}

	public long getFinaliza() {
		return finaliza;
	}
}
