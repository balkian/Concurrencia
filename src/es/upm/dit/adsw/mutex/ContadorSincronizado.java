package es.upm.dit.adsw.mutex;

/**
 * Contador sincronizado
 * Seguro en un entorno concurrente
 * 
 * @author jpuente
 * @version 2019.03.06
 */
public class ContadorSincronizado  {
	
	private long cuenta = 0;

	public ContadorSincronizado (long valorInicial){
		cuenta = valorInicial;
	}

	public synchronized void incrementar() {
		cuenta++;                
	}

	public synchronized long valor() {
		return cuenta;
	}
}
