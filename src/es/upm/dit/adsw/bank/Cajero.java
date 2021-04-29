package es.upm.dit.adsw.bank;

class Cajero {
	Cuenta cuenta;
	int identificador;
	int extraido;
	int retardo;
	
	public Cajero(int identificador, Cuenta cuenta) {
		this.identificador = identificador;
		this.cuenta = cuenta;
		this.extraido = 0;
		this.retardo = 0;
	}
	/**
	 * 
	 * @param identificador
	 * @param cuenta
	 * @param retardo
	 */
	public Cajero(int identificador, Cuenta cuenta, int retardo) {
		this(identificador, cuenta);
		this.retardo = retardo;
	}
			
	int extraer(int cantidad) {
		// No puedo devolver dinero porque no hay suficiente
		if(cuenta.getSaldo() < cantidad) {
			return 0;
		}
		// Realiza otras operaciones que no conocemos.
		this.operacionesSecretas();
		
		// Retira fondos de la cuenta y devuélvelos
		this.cuenta.setSaldo(this.cuenta.getSaldo() - cantidad);
		this.extraido += cantidad;
		return cantidad;
	}
	
	void operacionesSecretas() {
		// De momento las simulamos con un sleep.
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			System.out.println("Interrumpidas las operaciones secretas");
		}

	}
	void info() {
		System.out.println("Cajero " + this.identificador + ". Saldo:" + cuenta.getSaldo());
	}
}
