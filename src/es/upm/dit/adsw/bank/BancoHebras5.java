package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A diferencia de BancoHebras4, en esta clase usamos varios cajeros que tienen acceso
 * a una instancia sincronizada (CuentaSincronizada).
 * 
 * Cada cajero también tiene que realizar una serie de operaciones (secretas) de forma 
 * concurrente. Los métodos de la clase CuentaSincronizada no son suficientes, porque sólo
 * garantizan la atomicidad de get y set. El valor podría cambiar entre la lectura y la escritura.
 * 
 * Tenemos varias opciones. Una de ellas sería mover las operaciones (secretas) a CuentaSincronizada.
 * Esto no siempre es posible.
 * 
 * En lugar de eso, la alternativa que tomamos es usar bloques sincronizados. El resultado es
 * CajeroCuentaSincronizada.
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoHebras5 {
	
	static int escenario(int retrasoCola, int retrasoCajero) throws InterruptedException {
		CuentaSincronizada cuenta = new CuentaSincronizada(100);
		
		List<Cajero> cajeros = new ArrayList<Cajero>();
		
		// Tenemos que guardar una lista con las hebras para poder esperar a que acaben
		List<Thread> hebras = new ArrayList<Thread>();

		// Creamos 10 cajeros 
		for(int i=0; i<10; i++) {
			Cajero c = new CajeroCuentaSincronizada(i, cuenta, retrasoCajero);
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
