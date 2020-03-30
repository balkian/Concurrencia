package es.upm.dit.adsw.hogwarts.deadlocks;

/**
 * Hebra para crear un personaje de la casa Gryffindor. Los personajes de esta
 * casa siguen el siguiente protocolo para lanzar un hechizo 
 * primero: se ponen el sombrero 
 * segundo: cogen la varita mágica 
 * tercero: lanzan el hechizo
 * 
 * @author diegomartín
 * @version 2020.03.30
 */

public class Gryffindor extends Thread {

	private String nombre;
	private Sombrero miSombrero;
	private Varita miVarita;
	private int sueño;

	public Gryffindor(String nombre, Sombrero gorro, Varita miVarita, int sueño) {
		this.nombre = "🦁 Gryffindor:\t" + nombre;
		this.miSombrero = gorro;
		this.miVarita = miVarita;
		this.sueño = sueño;
	}

	@Override
	public void run() {
		while (true) {
			miSombrero.poner();
			System.out.println(this.nombre + " se pone el sombrero: " + this.miSombrero.getNombre());

			try {
				Thread.sleep(this.sueño);
			} catch (Exception ignored) {
			}

			miVarita.coger();

			System.out.println(this.nombre + " usa varita: " + this.miVarita.getNombre());

			try {
				System.out.println(this.nombre + " : entra en la sección crítica y LANZA EL HECHIZO!");
				Thread.sleep(this.sueño);
			} catch (Exception ignored) {
			}

			miSombrero.quitar();
			miVarita.soltar();
		}
	}
}
