package es.upm.dit.adsw.threads;

import java.awt.*;
import java.util.Scanner;

/**
 * Emite un sonido cuando se pulsa la tecla "intro"
 * 
 * @author jpuente
 * @version 2019.03.06
 */
public class Sonido1 extends Thread {

	private volatile Boolean activo = true;

	@Override
	public void run() {                           /* código concurrente */
		Scanner sc = new Scanner(System.in);
		while(activo) {
			sc.nextLine();                       /* espera fin de línea */
			Toolkit.getDefaultToolkit().beep();  /* emite sonido */
		}
	}

	public void detener() {
		activo = false;
	}

}
