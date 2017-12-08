package juego.manager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * CLASE PARA DETECTAS LOS EVENTOS DEL TECLADO.
 * 
 * */
public class KeyManager extends KeyAdapter{

	private boolean[] keys; // ARREGLO PARA VER QUE TECLAS HAN SIDO ACTIVADAS.
	private boolean izquierda, derecha, disparo; // VARIABLES PARA CONTROLAR EL MOVIMIENTO O ACCIONES DEL JUGADOR.
	
	public KeyManager() {
		keys=new boolean[256];
	}
	
	public boolean[] getKeys() {
		return keys;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}
	
	

	public boolean isDisparo() {
		return disparo;
	}

	
	public void setDisparo(boolean disparo) {
		this.disparo = disparo;
	}

	public void update(){
		izquierda=keys[KeyEvent.VK_LEFT]; // OBTENEMOS DEL ARREGLO SI SE HA PRESIONADO LA FLECHA IZQUIERDA.
		derecha=keys[KeyEvent.VK_RIGHT]; // OBTENEMOS DEL ARREGLO SI SE HA PRESIONADO LA FLECHA DERECHA.
		disparo=keys[KeyEvent.VK_A]; // OBTENEMOS DEL ARREGLO SI SE HA PRESIONADO LA TECLA A.
	}

	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()]=true; // SI LA TECLA ES PRESIONADA LO GUARDAMOS EN EL ARREGLO
		update(); // ACTUALIZAMOS VARIABLES
	}
	
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()]=false; // SI LA TECLA SE SUELTA LO PONEMOS EN FALSE EN EL ARREGLO.
		update(); // ACTUALIZAMOS VARIABLES.
	}
	
	
}
