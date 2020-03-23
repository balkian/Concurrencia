package es.upm.dit.adsw.mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Contador con cerrojo
 * Es seguro en un entorno concurrente

 * @author jpuente
 * @version 2020.03.23
 */
public class ContadorCerrojo {

	private long cuenta = 0;
	private Lock cerrojo = new ReentrantLock();

	public ContadorCerrojo(long valorInicial){
		cuenta = valorInicial;
	}

	public void incrementa() {
		cerrojo.lock();
		cuenta++;
		cerrojo.unlock();
	}

	public long valor() {
		return cuenta;
	}
	
	/**
	 * Smoke test
	 */
	public static void main(String[] args) {

		final long nVeces = 1000000;
		final int nThreads = 2;

		ContadorCerrojo contador = new ContadorCerrojo(0);

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
