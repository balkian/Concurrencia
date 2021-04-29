package es.upm.dit.adsw.bank;

class Cuenta {
	int saldo;
	public Cuenta(int saldo) {
		this.saldo = saldo;
	}
	public int getSaldo() {
		return this.saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
}