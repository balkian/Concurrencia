package es.upm.dit.adsw.bank;

class CajeroCuentaSincronizada extends Cajero {
	CuentaSincronizada cuenta;

	public CajeroCuentaSincronizada(int identificador, CuentaSincronizada cuenta, int retardo) {
		super(identificador, cuenta, retardo);
		this.cuenta = cuenta;
	}

	@Override	
	int extraer(int cantidad) {
		synchronized(cuenta) {
			int saldo = cuenta.getSaldo();	
			// Realiza otras operaciones que no conocemos.
			this.operacionesSecretas();
			
			
			// Retira fondos de la cuenta y devuélvelos
			try {
				this.cuenta.setSaldo(saldo - cantidad);
			}catch(IllegalArgumentException ex) {
					return 0;
			}
			this.extraido += cantidad;
		}
		return cantidad;

	}

}
