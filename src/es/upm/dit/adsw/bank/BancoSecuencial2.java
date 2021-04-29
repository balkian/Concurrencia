package es.upm.dit.adsw.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * BancoSecuencial2 simula un sistema en el que hay varios cajeros 
 * que acceden a una cuenta corriente para extraer/ingresar dinero.
 * 
 * A diferencia de BancoSecuencial, en esta clase creamos un método para
 * poder ver el efecto del retraso del cajero y del acceso al cajero. Esto
 * será útil cuando empecemos a usar hebras.
 * 
 * El acceso en esta clase es secuencial.
 * 
 * @author jfsanchez
 * @version 2021.04.29
 */
public class BancoSecuencial2 {
	
	static int escenario(int retrasoCola, int retrasoCajero) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100);
		
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
		return extraido;
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		escenario(0, 0);
		escenario(100, 0);
		escenario(1000, 0);
	}


}
