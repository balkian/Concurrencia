package es.upm.dit.adsw.bank;

class CajeroSincronizado extends Cajero {

	public CajeroSincronizado(int identificador, Cuenta cuenta, int retardo) {
		super(identificador, cuenta, retardo);
	}

	@Override	
	synchronized int extraer(int cantidad) {
		return super.extraer(cantidad);
	}

	synchronized void info() {
		super.info();
	}
}
