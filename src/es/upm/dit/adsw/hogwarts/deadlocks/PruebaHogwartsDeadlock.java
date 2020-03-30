package es.upm.dit.adsw.hogwarts.deadlocks;

/**
 * Clase de prueba para generar un deadlock
 * 
 * @author diegomartín
 * @version 2020.03.30
 */

public class PruebaHogwartsDeadlock {
	public static void main(String[] args) {
		Varita varitaDumble = new Varita("de Dumbledore");
		//Varita varitaSnape = new Varita("de Snape");
		
		Sombrero seleccionador = new Sombrero("Seleccionador 🎓");
		//Sombrero cups = new Sombrero("Cups 🧢");

		Gryffindor harryPotter = new Gryffindor("Harry Potter", seleccionador, varitaDumble, 100);
		Slytherin dracoMalfoy = new Slytherin("Draco Malfoy", seleccionador, varitaDumble, 100);
		//Slytherin volde = new Slytherin("Lord Voldemort", cups, varitaDumble, 100);

		harryPotter.start();
		dracoMalfoy.start();
		//volde.start();
	}
}