package es.upm.dit.adsw.mutex;

/**
 * Ejemplo elemental: condiciones de carrera con variables compartidas.
 * 
 * @author jpuente
 * @version 2020.03.19
 */
public class ContadorCompartidoVolatil {

	/**
	 * variable compartida.
	 */
	static volatile long contador = 0;

	static final long nVeces = 1000000;
	static final int nThreads = 2;

	/**
	 * Hebra que incrementa el contador nVeces
	 */
	private static class Cuenta extends Thread {
		@Override
		public void run() {
			for (long i = 0; i<nVeces; i++) {
				contador++; 
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
