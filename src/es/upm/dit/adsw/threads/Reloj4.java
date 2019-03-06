package es.upm.dit.adsw.threads;

import java.util.Date;

/**
 * Reloj1 que escribe la hora cada segundo. Construido con un objeto Thread con
 * un método run interno.
 * 
 * @author jpuente
 * @version 2019.03.06
 */
public class Reloj4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread hora = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(1000);
						System.out.println(new Date().toString());
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		};
		hora.start();
	}

}