package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * BancoHebras simula un sistema en el que hay varios cajeros 
 * que acceden a una cuenta corriente para extraer/ingresar dinero.
 * 
 * *NOTA*: Ver BancoSecuencial y BancoSecuencial2 antes.
 *  
 * El acceso en esta clase es concurrente mediante hebras. Por tanto, vemos
 * que aparecen condiciones de carrera en función de los tiempos de retraso 
 * y de procesamiento. Eso se ve reflejado en que la cantidad extraída de la 
 * cuenta puede ser superior a la cantidad total. Además, el saldo llega a ser
 * negativo, pese a los controles en Cajero.
 * 
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoHebras {
	
	static void escenario(int retardo) {
		Cuenta cuenta = new Cuenta(100);
		
		List<Cajero> cajeros = new ArrayList<Cajero>();
		List<Thread> hebras = new ArrayList<Thread>();
		// Creamos 10 cajeros 
		for(int i=0; i<10; i++) {
			Cajero c = new Cajero(i, cuenta, retardo);
			cajeros.add(c);

			Thread h = new Thread() {
				@Override
				public void run() {
					c.extraer(20);
					c.info();					
				}
			};
			hebras.add(h);
			h.start();
			// Cada cajero extrae 20 unidades
		}
		for(Thread t: hebras) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int extraido = 0;
		for(Cajero c: cajeros) {
			extraido += c.extraido;
		}

		System.out.println("Extraido: " + extraido);
	}

	static int escenario(int retrasoCola, int retrasoCajero) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100);
		
		List<Cajero> cajeros = new ArrayList<Cajero>();
		
		// Tenemos que guardar una lista con las hebras para poder esperar a que acaben
		List<Thread> hebras = new ArrayList<Thread>();

		// Creamos 10 cajeros 
		for(int i=0; i<10; i++) {
			Cajero c = new Cajero(i, cuenta, retrasoCajero);
			cajeros.add(c);
			
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(retrasoCola);
					} catch (InterruptedException e) {
						System.out.println("Cola interrumpida");
						return;
					}
					
					// Extraemos 20 unidades de cada cajero
					c.extraer(20);
					c.info();
				}
			};
			hebras.add(t);
		}
		
		// Vamos a cada uno de los cajeros a sacar dinero.
		for(Thread t: hebras) {
			t.start();
		}
		// Esperamos a que todas las hebras acaben antes de seguir
		// con la ejecución.
		for(Thread t: hebras) {
			t.join();
		}
		int extraido = 0;
		for(Cajero c: cajeros) {
			extraido += c.extraido;
		}
		System.out.println("Total extraído: " + extraido);
		return extraido;
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		escenario(0, 0);
		escenario(150, 100);
		escenario(100, 100);
	}

}
