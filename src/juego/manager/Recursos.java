package juego.manager;

import java.awt.image.BufferedImage;

/*
 * 
 * CLASE PARA CARGAR TODAS LAS IMAGENES, SONIDOS Y RECURSOS UTILIZADOS DEL JUEGO.
 * 
 * */
public class Recursos {

	public static BufferedImage fondo; // IMAGEN DEL FONDO DEL ESCENARIO.
	public static BufferedImage piso; // IMAGEN DEL PISO DEL ESCENARIO.
	public static BufferedImage jugador; // IMAGEN DEL JUGADOR.
	public static BufferedImage[] jugadorIzquierda; // LISTA DE IMAGENES DEL JUGADOR MOVIMIENDOSE A LA IZQUIRDA.
	public static BufferedImage[] jugadorDerecha; // LISTA DE IMAGENES DEL JUGADOR MOVIENDOSE A LA DERECHA.
	public static BufferedImage enemigo; //IMAGEN DEL ENEMIGO
	public static AudioManager audioMenu;// SONIDO DEL MENU.
	public static AudioManager audioJuego;// SONIDO DEL JUEGO.
	
	
	public static void init(){
		
		// CARGAMOS IMAGENES CON LA CLASE IMAGEMANAGER.
		fondo=ImageManager.cargaImagen("/img/country-platform-back.png");
		piso=ImageManager.cargaImagen("/img/country-platform-tiles-example.png");
		enemigo=ImageManager.cargaImagen("/img/hucha.png");
		
		// CREAMOS UN SPRITE
		SpriteManager sm=new SpriteManager("/img/test.png");
		
		// OBTENEMOS LA IMAGEN DEL JUGADOR CON UNA SUBIMAGEN DEL SPRITE.
		jugador=sm.subImagen(0, 0, 32, 32);
		
		// INICIALIZAMOS ARREGLOS PARA EL JUGADOR EN MOVIMIENTO.
		jugadorIzquierda=new BufferedImage[3];
		jugadorDerecha=new BufferedImage[3];
	
		//OBTENEMOS LAS IMAGENES DEL JUGADOR EN MOVIMIENTO IZQUIERDO DEL SPRITE.
		jugadorIzquierda[0]=sm.subImagen(0, 32, 32, 32);
		jugadorIzquierda[1]=sm.subImagen(32, 32, 32, 32);
		jugadorIzquierda[2]=sm.subImagen(64, 32, 32, 32);
		
		
		//OBTENEMOS LAS IMAGENES DEL JUGADOR EN MOVIMIENTO DERECHO DEL SPRITE.
		jugadorDerecha[0]=sm.subImagen(0, 64, 32, 32);
		jugadorDerecha[1]=sm.subImagen(32, 64, 32, 32);
		jugadorDerecha[2]=sm.subImagen(64, 64, 32, 32);
		
		// CARGAMOS LOS SONIDOS DEL JUEGO.
		audioMenu=new AudioManager("/sonidos/sonidoMenu.wav"); // AUDIO DEL MENU
		audioJuego=new AudioManager("/sonidos/sonidoJuego.wav");  // AUDIO DEL JUEGO
	
	}

}
