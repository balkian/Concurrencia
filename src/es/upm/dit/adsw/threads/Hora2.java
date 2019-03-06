package es.upm.dit.adsw.threads;

import java.util.Date;

/**
 * Escribe la hora cada 1 s - versión Runnable
 * 
 * @author jpuente
 * @version 2019.03.06
 */
public class Hora2 implements Runnable {

	private volatile Boolean activo = true;

	@Override
	public void run()  {
		try {
			while (activo) {
				Thread.sleep(1000);
				System.out.println(new Date().toString());
			}
		} catch (InterruptedException e) {
			return;            
		}
	}

	public void detener() {
		activo = false;
	}

}
