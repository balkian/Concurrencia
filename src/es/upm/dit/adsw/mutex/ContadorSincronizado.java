package es.upm.dit.adsw.mutex;

/**
 * Contador sincronizado
 * Seguro en un entorno concurrente
 * 
 * @author jpuente
 * @version 2020.03.23
 */
public class ContadorSincronizado  {
	
	private long cuenta = 0;

	/**
	 * Constructor
	 * @param valorInicial valor inicial
	 */
	public ContadorSincronizado (long valorInicial){
		cuenta = valorInicial;
	}

	/**
	 * Aumenta en 1 el valor de la cuenta
	 */
	public synchronized void incrementa() {
		cuenta++;                
	}

	/**
	 * Devuelve el valor de la cuenta
	 * @return valor actual de la cuenta
	 */
	public synchronized long valor() {
		return cuenta;
	}
	
	/**
	 * Smoke test
	 */
	public static void main(String[] args) {

		final long nVeces = 1000000;
		final int nThreads = 2;

		ContadorSincronizado contador = new ContadorSincronizado(0);

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
