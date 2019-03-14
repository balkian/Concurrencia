package es.upm.dit.adsw.buffer;

/**
 * Buffer con capacidad para varios elementos
 * 
 * @author jpuente
 * @version 07.04.2016
 */
public class BufferMultiple <E> implements Buffer<E> {

	private E[] almacen;
	private final int N;      // tamaño del buffer
	private int n = 0;        // número de elementos almacenados
	private int in = 0;
	private int out = 0;
	
	
	/**
	 * Crear un nuevo buffer
	 * 
	 * @param n tamaño del buffer
	 */
	@SuppressWarnings("unchecked")
	public BufferMultiple (int n) {
		this.N = n;
		this.almacen = (E[]) new Object[n];
	}

	public synchronized void enviar(E dato) {
		while (n == N) {
			try {
				wait(); // espera que haya sitio
			} catch (InterruptedException ignored) { }
		}
		assert n < N;
		almacen[in] = dato;
		in = ++in % N;
		n++;
		assert n > 0;
		notifyAll(); // avisa de que hay un valor
	}

	public synchronized E recibir() {
		E dato = null;
		while (n == 0) {
			try {
				wait(); // espera que haya un valor
			} catch (InterruptedException ignored) { }
		}
		assert n > 0;
		dato = almacen[out];
		out = ++out % N;
		n--;
		assert n < N;
		notifyAll(); // avisa de que hay sitio
		return dato;
	}

}
