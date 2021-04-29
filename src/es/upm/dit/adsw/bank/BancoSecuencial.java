package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * BancoSecuencial simula un sistema en el que hay varios cajeros 
 * que acceden a una cuenta corriente para extraer/ingresar dinero.
 * 
 * Tenemos 2 clases:
 * - Cuenta, que guarda información del saldo disponible.
 * - Cajero, que accede internamente a una cuenta, controla que no se 
 * pueda extraer dinero cuando no hay suficiente saldo en la cuenta, y
 * lleva un conteo de cuánto dinero ha expedido.
 * 
 * Nosotros interactuamos con los cajeros directamente.
 * 
 * Antes de poder extraer dinero del cajero, hay que esperar un tiempo
 * de cola (retrasoCola). Además, los cajeros tardan un cierto tiempo 
 * en procesar las peticiones de extracción de dinero (retrasoCajero).
 * 
 * Tras cada extracción, anotamos cuánto dinero ha sido extraído. Al final de
 * la ejecución, contamos la cantidad total extraída.
 * 
 * El acceso en esta clase es secuencial. Por tanto, no hay condiciones de
 * carrera y el total extraído nunca puede ser superior a la cantidad inicial
 * en la cuenta (100, por defecto).
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoSecuencial {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100);
		// Tiempo que tardo en poder pedir el dinero tras llegar al cajero
		int retrasoCola = 100;
		// Tiempo que tarda cada cajero en procesar las peticiones
		int retrasoCajero = 100;
		
		List<Cajero> cajeros = new ArrayList<Cajero>();
		// Creamos 10 cajeros 
		for(int i=0; i<10; i++) {
			Cajero c = new Cajero(i, cuenta, retrasoCajero);
			cajeros.add(c);
		}
		
		// Vamos a cada uno de los cajeros a sacar dinero.
		for(Cajero c: cajeros) {
			Thread.sleep(retrasoCola);
			// Extraemos 20 unidades de cada cajero
			c.extraer(20);
			c.info();
		}
		int extraido = 0;
		for(Cajero c: cajeros) {
			extraido += c.extraido;
		}
		System.out.println("Total extraído: " + extraido);
	}


}
