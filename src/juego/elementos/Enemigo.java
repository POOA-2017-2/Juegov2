package juego.elementos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import juego.manager.Recursos;

/*
 * CLASE PARA CONTROLAR LOS ENEMIGOS DEL JUEGO.
 * 
 * */
public class Enemigo {

	private int x; // COORDENADA X DEL ENEMIGO.
	private int y; // COORDENADA Y DEL ENEMIGO.
	private int dx; // VELOCIDAD DEL ENEMIGO.
	private BufferedImage imagen; // IMAGEN DEL ENEMIGO.
	
	public Enemigo(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		dx=2;
		imagen=Recursos.enemigo; // CARGAMOS LA IMAGEN DEL ENEMIGO, ATRAVEZ DE LA CLASE RECURSOS.
	}

	public void update(){
		// DECREMENTAMOS LA COORDENADA X DEL ENEMIGO, ENEMIGO TIENE UN COMPORTAMIENTO "TONTO".
		x-=dx;
	}
	
	public void render(Graphics g){
		// DIBUJAMOS AL ENEMIGO
		g.drawImage(imagen,x,y,null);
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
	
	// METODO PARA RETORNAR UN RECTANGULO CON LAS DIMENSIONES DEL ENEMIGO, PARA LAS COLISIONES.
	public Rectangle getBounds(){
		return new Rectangle(x,y,imagen.getWidth(), imagen.getHeight());
	}

}
