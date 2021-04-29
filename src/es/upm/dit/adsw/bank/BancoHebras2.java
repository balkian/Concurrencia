package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Vimos en BancoHebras que había condiciones de carrera.
 * 
 * En esta clase, arreglamos ese problema mediante el uso de cerrojos.
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoHebras2 {
	
	static int escenario(int retrasoCola, int retrasoCajero) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100);
		
		// Añadimos un cerrojo para controlar el acceso.
		ReentrantLock cerrojo = new ReentrantLock();

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
					cerrojo.lock();
					try {
						Thread.sleep(retrasoCola);
					} catch (InterruptedException e) {
						System.out.println("Cola interrumpida");
						return;
					}
					
					// Extraemos 20 unidades de cada cajero
					c.extraer(20);
					c.info();
					// Desbloqueamos el cerrojo.
					cerrojo.unlock();
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
