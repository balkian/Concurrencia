package es.upm.dit.adsw.threads;

import java.util.Date;

/**
 * Escribe la hora cada 1 s
 * 
 * @author jpuente
 * @version 2019.03.06
 */
public class Hora1 extends Thread {
	
	private volatile Boolean activo = true;

	@Override
	public void run()  {              /* código concurrente */
		try {
			while (activo) {
				sleep(1000);           /* esperar 1000 ms */
				System.out.println(new Date().toString());
			}
		} catch (InterruptedException e) {
			return;                  /* terminar esta hebra */
		} 
	}
	
	public void detener() {
		activo = false;
	}
}
