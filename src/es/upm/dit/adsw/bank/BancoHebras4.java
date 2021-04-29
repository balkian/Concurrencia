package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * En este caso, modificamos el escenario BancoHebras3 para que funcione.
 * Usamos un solo cajero, al que se puede acceder de forma concurrente.
 * Al haber solo un cajero que se reutiliza desde varias hebras, no debería
 * haber acceso concurrente a los datos.
 * 
 * Funciona, y el resultado es análogo al que teníamos en BancoHebras2, pero no es 
 * exactamente lo que queríamos: tener varios objetos cajero.
 * 
 * ¿Hay alguna forma de arreglar BancoHebras3 manteniendo varios cajeros independientes?.
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoHebras4 {
	
	static int escenario(int retrasoCola, int retrasoCajero) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100);
		
		List<Cajero> cajeros = new ArrayList<Cajero>();
		
		// Tenemos que guardar una lista con las hebras para poder esperar a que acaben
		List<Thread> hebras = new ArrayList<Thread>();

		Cajero cajero = new CajeroSincronizado(0, cuenta, retrasoCajero);
		cajeros.add(cajero);
		
		// Creamos 10 cajeros 
		for(int i=0; i<10; i++) {
			Cajero c = cajero;
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
