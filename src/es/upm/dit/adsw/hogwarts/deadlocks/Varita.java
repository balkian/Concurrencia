package es.upm.dit.adsw.hogwarts.deadlocks;

/**
 * Varita es clase hija de ObjetoMágico
 * 
 * @author diegomartín
 * @version 2020.03.20
 */
public class Varita extends ObjetoMágico {

	/**
	 * Constructor de la clase Varita
	 * @param nombre String con el nombre de la varita
	 */
	public Varita(String nombre) {
		super(nombre);
	}

	/**
	 * Coger varita
	 */
	public synchronized void coger() {
		try {
			while (this.estaOcupado())
				wait();
		} catch (InterruptedException ignored) {
		}
		System.out.println("Varita " + this.getNombre() + " ha sido cogida.");
		this.ocupar();
		;
	}

	/**
	 * Soltar varita
	 */
	public synchronized void soltar() {
		this.desocupar();
		;
		System.out.println("Varita " + this.getNombre() + " ha sido soltada.");
		notifyAll();
	}
}