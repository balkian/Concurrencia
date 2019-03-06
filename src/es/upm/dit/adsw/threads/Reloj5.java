package es.upm.dit.adsw.threads;

import java.util.Date;

/**
 * Reloj1 segundero construido con un objeto Thread anónimo.
 * 
 * @author jpuente
 * @version 2019.03.06
 */
public class Reloj5 {

	/**
	 * @param args
	 *            - no arguments
	 */
	public static void main(String[] args) {
		new Thread() {
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
		}.start();
		
		new Thread() {
			public void run () {
				while (true) 
					try {
						sleep(5000);
						System.out.println("Hola");
					} catch (Exception ignore) {}
			}
		}.start();
		
}

}