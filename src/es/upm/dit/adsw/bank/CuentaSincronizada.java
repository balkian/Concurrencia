package es.upm.dit.adsw.bank;

class CuentaSincronizada extends Cuenta {
	
	public CuentaSincronizada(int saldo) {
		super(saldo);
	}
	synchronized public boolean extraer(int cantidad) {
		if(cantidad < 0) {
			return false;
		}
		this.setSaldo(this.getSaldo()-cantidad);
		return true;
	}
	synchronized public void setSaldo(int saldo) {
		if(saldo < 0) {
			throw new IllegalArgumentException("Saldo negativo");
		}
		this.saldo = saldo;
	}
}