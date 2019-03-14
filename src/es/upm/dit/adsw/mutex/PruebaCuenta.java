package es.upm.dit.adsw.mutex;

/**
 * Ejemplo de condiciones de carrera con un contador compartido
 *
 * @author jpuente
 * @versin 2019.03.06
 */
public class PruebaCuenta {

    static long cuenta = 0;                              /* variable compartida */

    private static class Incrementa extends Thread {
        public void run () {
            for (int i = 0; i < 1_000_000; i++) {
                cuenta++;                                /* región crítica */
            }
        }
    }

    public static void main(String[] args) {
        Incrementa i1 = new Incrementa();
        i1.start(); /* hebra 1 */
        Incrementa i2 =new Incrementa();
        i2.start(); /* hebra 2 */
        try {
            i1.join();
            i2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("cuenta = " + cuenta + " (debería ser " + 2*1_000_000 + ")");
    }

}
