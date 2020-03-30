package es.upm.dit.adsw.hogwarts.livelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class PruebaHogwartsLivelock {  
	
    private static final Lock cerrojoSombreroSeleccionador = new ReentrantLock();
    private static final Lock cerrojoVaritaSnape = new ReentrantLock();
    
	  public static void main(String[] args) {  
	    
	    Gryffindor harryPotter = new Gryffindor("Harry Potter", cerrojoSombreroSeleccionador, cerrojoVaritaSnape,100);  
	    //Gryffindor hermione = new Gryffindor("Hermione Granger", cerrojoSombreroSeleccionador, cerrojoVaritaSnape,100);
	    
	    Slytherin dracoMalfoy = new Slytherin("Draco Malfoy", cerrojoSombreroSeleccionador, cerrojoVaritaSnape,100);
	    	    
	    harryPotter.start();  
	    dracoMalfoy.start();
	    //hermione.start();

	  }  
	}  