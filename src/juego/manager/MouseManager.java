package juego.manager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseManager extends MouseAdapter{

	private boolean left; // VARIABLE PARA CONTROLAR ACCION MOVIMIENTO IZQUIERDO.
	private boolean right; // VARIABLE PARA CONTROLAR ACCION MOVIMIENTO DERECHA.
	private boolean fire; // VARIABLE PARA CONTROLAR ACCION DE DISPARO.
	
	public MouseManager() {
		left=false;
		right=false;
		fire=false;
	}
	
	
	// METODO QUE SE EJECUTA CUANDO EL CONTROL DETECTA UN CLICK SOBRE EL.
	public void mousePressed(MouseEvent e){
		mover(e); // ACTUALIZA VARIABLES.
	}
	
	// METODO PARA ACTUALIZAR VARIABLES.
	private void mover(MouseEvent e){
		
		String action=((JButton)e.getSource()).getActionCommand(); // OBTENEMOS LA ACCION DEL BOTON
		
		// INICIALIZAMOS VARIABLES EN FALSE.
		left=false;
		right=false;
		fire=false;
		
		switch(action){
			case "left":
				left=true; // SI LA ACCION ES MOVER IZQUIERDA LA VARIABLE LEFT CAMBIA A VERDADERO.
				break;
			case "right":
				right=true; // SI LA ACCION ES MOVER DERECHA LA VARIABLE RIGHT CAMBIA A VERDADERO.
				break;
			case "fire":
				fire=true; // SI LA ACCION ES DISPARAR LA VARIABLE FIRE CAMBIA A VERDADERO.
				break;
		}
	}

	
	// METODO QUE SE EJECUTA CUANDO EL CONTROL DETECTA QUE SE SUELTA EL CLICK DEL BOTON.
	public void mouseReleased(MouseEvent e){
		left=false;
		right=false;
		fire=false;
		
	}
	
	// METODO QUE SE EJECUTA CUANDO EL CONTROL DETECTA QUE EL MOUSE SE ENCUENTRA ENCIMA DE EL.
	public void mouseEntered(MouseEvent e){
		//mover(e);
	}
	
	// METODO QUE SE EJECUTA CUANDO EL CONTROL DETECTA QUE EL MOUSE SE ENCUENTRA FUERA DE EL.
	public void mouseExited(MouseEvent e){
		left=false;
		right=false;
	}


	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isFire() {
		return fire;
	}


	public void setFire(boolean fire) {
		this.fire = fire;
	}
	
	
	
	
	
	
	
	

}
