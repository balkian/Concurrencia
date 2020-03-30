package es.upm.dit.adsw.hogwarts.livelock;

import java.util.concurrent.locks.Lock;

/**
 * Hebra para crear un personaje de la casa Gryffindor. Los personajes de esta
 * casa siguen el siguiente protocolo para lanzar un hechizo 
 * primero: se ponen el sombrero (obtiene el cerrojo) 
 * segundo: cogen la varita mágica (obtiene el cerrojo) 
 * tercero: lanzan el hechizo 
 * cuarto: devuelve los cerrojos
 * 
 * @author diegomartín
 * @version 2020.03.30
 */

public class Gryffindor extends Thread {

	private String nombre;
	private Lock cerrojoSombrero;
	private Lock cerrojoVarita;
	private int sueño;

	public Gryffindor(String nombre, Lock cerrojoSombrero, Lock cerrojoVarita, int sueño) {
		this.nombre = "🦁 Gryffindor:\t" + nombre;
		this.cerrojoSombrero = cerrojoSombrero;
		this.cerrojoVarita = cerrojoVarita;
		this.sueño = sueño;
	}

	@Override
	public void run() {
		while (true) { // se puede quitar porque es posible que se atasque a la
						// primera
			try {
				while (!this.cerrojoSombrero.tryLock()) {
					System.out.println(this.nombre + " : \t espera al cerrojo del sombrero.");
					Thread.sleep(this.sueño);
				}
				System.out.println(this.nombre + " : \t ha obtenido el cerrojo del sombrero.");

				while (!this.cerrojoVarita.tryLock()) {
					System.out.println(this.nombre + " : \t espera al cerrojo de la varita.");
					Thread.sleep(this.sueño);
				}
				System.out.println(this.nombre + " : \t ha obtenido el cerrojo de la varita.");

				System.out.println(this.nombre + " : \t entra en la sección crítica y LANZA EL HECHIZO!");
				this.cerrojoVarita.unlock();
				System.out.println(this.nombre + " : \t devuelve el cerrojo de la varita.");
				this.cerrojoSombrero.unlock();
				System.out.println(this.nombre + " : \t devuelve el cerrojo del sombrero.");
			} catch (InterruptedException e) {
				// Se puede ignorar para este ejemplo. No debería haber
				// interrupciones.
			}
		}
	}
}
