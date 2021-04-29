package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * En esta clase e intentamos eliminar las condiciones de carrera usando
 * métodos sincronizados en la clase CajeroSincronizado.
 * 
 * Spoiler: NO FUNCIONA. La sincronización con CajeroSincronizado se hace
 * sobre los objetos cajero.
 * Eso hace que un mismo cajero no se va a poder ejecutar el método extraer
 * de forma concurrente. Pero en este caso tenemos varios cajeros trabajando
 * a la vez, con lo que no hay sincronización entre ellos.
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoHebras3 {
	
	static int escenario(int retrasoCola, int retrasoCajero) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100);
		
		List<Cajero> cajeros = new ArrayList<Cajero>();
		
		// Tenemos que guardar una lista con las hebras para poder esperar a que acaben
		List<Thread> hebras = new ArrayList<Thread>();

		// Creamos 10 cajeros 
		for(int i=0; i<10; i++) {
			Cajero c = new CajeroSincronizado(i, cuenta, retrasoCajero);
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
