package juego.elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * CLASE PARAR CREAR LAS BALAS DEL JUEGO.
 * 
 * */
public class Bala {

	private int x; // COORDENADA X DE LA BALA.
	private int y; // COORDENADA Y DE LA BALA.
	private int dx; // VELOCIDAD DE LA BALA.
	private String direccion; // DIRECCION DE LA BALA. SE PUEDE CAMBIAR POR UNA ENUMERACION.
	
	public Bala(int x, int y, String direccion) {
		super();
		this.x = x;
		this.y = y;
		this.direccion=direccion;
		dx=2;
	}


	// METODO PARA ACTUALIZAR LAS VARIABLES DE LA BALA. COORDENADA.
	public void update(){
		// SI LA BALA TIENE UNA DIRECCION A LA DERECHA INCREMENTAMOS SU COORDENADA X.
		if(direccion.equals("right"))
			x+=dx;
		// SI LA BALA TIENE UNA DIRECCION A LA IZQUIERDA INCREMENTAMOS SU COORDENADA Y.
		else if(direccion.equals("left"))
			x-=dx;
	}
	
	// METODO PARA DIBUJAR LA BALA.
	public void render(Graphics g){
		// PONEMOS UN PINCEL DE COLOR ROJO OBSCURO.
		g.setColor(Color.red.darker());
		// DIBUJAMOS UN RECTANGULO PARA LA FIGURA DE LA BALA.
		g.fillRect(x, y, 2, 2);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// METODO PARA RETORNAR UN RECTANGULO CON LAS DIMENSIONES DE LA BALA, PARA LAS COLISIONES.
	public Rectangle getBounds(){
		return new Rectangle(x,y,2,2);
	}

}
