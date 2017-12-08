package juego.manager;

import java.awt.image.BufferedImage;

/*
 * 
 * CLASE ENCARGADA DE MANEJAR LOS SPRITES.
 * 
 * */
public class SpriteManager {

	private BufferedImage sprite; // ATRIBUTO DONDE GUARDAMOS EL SPRITE.
	
	public SpriteManager(String path) {
		sprite=ImageManager.cargaImagen(path);
	}
	
	// METODO PARA OBTENER UNA IMAGEN DEL SPRITE.
	public BufferedImage subImagen(int x, int y, int w, int h){
		return sprite.getSubimage(x, y, w, h);
	}

}
