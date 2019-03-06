package es.upm.dit.adsw.mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Contador con cerrojo
 * Es seguro en un entorno concurrente

 * @author jpuente
 * @version 2019.03.06
 */
public class ContadorCerrojo {

	private long cuenta = 0;
	private Lock cerrojo = new ReentrantLock();

	public ContadorCerrojo(long valorInicial){
		cuenta = valorInicial;
	}

	public void incrementar() {
		cerrojo.lock();
		cuenta++;
		cerrojo.unlock();
	}

	public long valor() {
		return cuenta;
	}
}
