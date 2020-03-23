
package es.upm.dit.adsw.mutex;

/**
 * Contador compartido No es seguro en un entorno concurrente
 * 
 * @author jpuente
 * @version 2019.03.23
 */
public class Contador {

	private long cuenta = 0;

	/**
	 * Constructor
	 * 
	 * @param valorInicial valor inicial de la cuenta
	 */
	public Contador(long valorInicial) {
		cuenta = valorInicial;
	}

	/**
	 * Aumentar en 1 el valor de la cuenta
	 */
	public void incrementa() {
		cuenta++;
	}

	/**
	 * Devuelve el valor
	 * 
	 * @return valor actual de la cuenta
	 */
	public long valor() {
		return cuenta;
	}

	/**
	 * Smoke test
	 */
	public static void main(String[] args) {

		final long nVeces = 1000000;
		final int nThreads = 2;

		Contador contador = new Contador(0);

		/**
		 * Hebra que incrementa el contador nVeces
		 */
		class Cuenta extends Thread {
			@Override
			public void run() {
				for (long i = 0; i < nVeces; i++) {
					contador.incrementa();
				}
			}
		}

		Cuenta[] hebra = new Cuenta[nThreads];

		System.out.println(nThreads + " contadores incrementando " + "la cuenta " + nVeces + " veces cada uno");

		for (int id = 0; id < nThreads; id++) {
			hebra[id] = new Cuenta();
			hebra[id].start();
		}

		for (int id = 0; id < nThreads; id++) {
			try {
				hebra[id].join();
			} catch (InterruptedException e) {
				return;
			}
		}

		System.out.print("cuenta = " + contador.valor());
		System.out.println("; debería ser " + nThreads * nVeces);
	}
}
