package es.upm.dit.adsw.buffer;

/**
 * Buffer con capacidad para 1 dato
 * 
 * @author jpuente
 * @version 07.04.2016
 */
public class BufferSimple <E> implements Buffer<E> {

	private E almacen;
	private boolean lleno = false;

	public synchronized void enviar(E dato) {
		while (lleno) {
			try {
				wait(); // espera que haya sitio
			} catch (InterruptedException ignored) { }
		}
		assert !lleno;
		almacen = dato;
		lleno = true;
		notifyAll(); // avisa de que hay un valor
	}

	public synchronized E recibir() {
		E dato = null;

		while (!lleno) {
			try {
				wait(); // espera que haya un valor
			} catch (InterruptedException ignored) { }
		}
		assert lleno;
		dato = almacen;
		lleno = false;
		notifyAll(); // avisa de que hay sitio
		return dato;
	}

}
