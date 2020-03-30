package es.upm.dit.adsw.hogwarts.deadlocks;

/**
 * Clase madre de todos los objetos mágicos. Habrá dos clases hijas: Sombrero y
 * Varita
 * 
 * @author diegomartín
 * @version 2020.03.30
 */

public class ObjetoMágico {

	private boolean ocupado;
	private String nombre;

	/**
	 * Constructor de un objeto mágico. Inicializa ocupado a false
	 * 
	 * @param nombre
	 *            String con el nombre del objeto mágico.
	 */
	public ObjetoMágico(String nombre) {
		this.ocupado = false;
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean estaOcupado() {
		return this.ocupado;
	}

	public void ocupar() {
		this.ocupado = true;
	}

	public void desocupar() {
		this.ocupado = false;
	}

}