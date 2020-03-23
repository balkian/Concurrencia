package es.upm.dit.adsw.mutex;

import java.util.concurrent.locks.*;

/**
 * Contador compartido. Se elimina la condición de carrera
 * forzando la exclusión mutua con un cerrojo.
 * 
 * @author jpuente
 * @version 2020.03.20
 */
public class ContadorCompartidoCerrojo {

	/**
	 * variable compartida.
	 */
	static long contador = 0;
	
	/**
	 * cerrojo
	 */
	static Lock cerrojo = new ReentrantLock();

	static final long nVeces = 1000000;
	static final int nThreads = 2;

	/**
	 * Hebra que incrementa el contador nVeces
	 */
	private static class Cuenta extends Thread {
		public void run() {
			for (long i = 0; i<nVeces; i++) {
				cerrojo.lock();   // bloquear el cerrojo
				contador++; 
				cerrojo.unlock(); // desbloquear el cerrojo
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(nThreads + " hebras incrementando " 
				+ "el contador " + nVeces +" veces cada uno" );
		Cuenta[] hebra = new Cuenta[nThreads];
		
		for (int id = 0; id < nThreads; id++) {
			hebra[id] = new Cuenta();
			hebra[id].start();
		}
		
		for (int id = 0; id < nThreads; id++) {
			try{hebra[id].join();}
			catch (InterruptedException e) {return;}
		}
		
		System.out.print("contador = " + contador);
		System.out.println("; debería ser " + nThreads*nVeces);
	}

}
