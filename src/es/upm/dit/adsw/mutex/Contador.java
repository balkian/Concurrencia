package es.upm.dit.adsw.mutex;

/**
 * Contador simple
 * No es seguro en un entorno concurrente

 * @author jpuente
 * @version 2019.03.06
 */
public class Contador {

	private long cuenta = 0;

	public Contador(long valorInicial){
		cuenta = valorInicial;
	}

	public void incrementar() {
		cuenta++;                
	}

	public long valor() {
		return cuenta;
	}
}
