package es.upm.dit.adsw.threads;

/**
 * Reloj1 que escribe la hora cada segundo
 * y produce un sonido cuando se pulsa INTRO
 * 
 * @author jpuente
 * @version 24.09.2013
 */
public class Reloj1 {

	/**
	 * @param args -- no hay argumentos
	 */
	public static void main(String[] args) {

		Hora1 hora   = new Hora1() ;          // crea las hebras
		Sonido1 sonido = new Sonido1();
		hora.start();                        // ... y las arranca
		sonido.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		hora.detener();
		sonido.detener();
	}

}


