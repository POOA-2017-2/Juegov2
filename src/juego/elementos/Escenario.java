package juego.elementos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import juego.estados.Juego;
import juego.manager.Recursos;

/*
 *	CLASE PARA CONTROLAR AL ESCENARIO. 
 * 
 * */

public class Escenario {

	private BufferedImage fondo; // IMAGEN DE FONDO DEL ESCENARIO
	private BufferedImage piso; // IMAGEN DEL PISO DEL ESCENARIO.
	private BufferedImage piso2; // IMAGEN DEL PISO DEL ESCENARIO.
	private int x; // COORDENADA X DEL PISO
	private Juego game; // OBJETO QUE CONTINE LA VENTANA PRINCIPAL DE LA APLICACION.
	
	public Escenario(Juego game) {
		// TODO Auto-generated constructor stub
		this.game=game;
		// INICIALIZAMOS IMAGENES A TRAVEZ DE LA CLASE RECURSOS.
		fondo=Recursos.fondo;
		piso=Recursos.piso;
		piso2=Recursos.piso;
		
		x=0;
	}
	
	//METODO PARA ACTUALIZAR LAS VARIABLES DEL ESCENARIO.
	public void update(){
		// SI SE PRESIONA LA TECLA FLECHA DERECHA.
		if(game.getPnlJuego().getKm().isDerecha()){
			x--; // DECREMENTAMOS LA COORDENADA X.
			if(x<=-piso.getWidth()){ // SI LA COORDENADA X SE SALE DE RANGO
				x=0; // REINICIAMOS LA COORDENADA X
			}
		}
		// SI SE PRESIONA LA TECLA FLECHA IZQUIERDA
		else if(game.getPnlJuego().getKm().isIzquierda()){
			// SI LA COORDENADA SE ENCUENTRA DENTRO DEL RANGO.
			if(x<-5){
				// INCREMENTAMOS LA COORDENADA X.
				x++;
			}
		}
	}
	
	// METODO PARA DIBUJAR EL ESCENARIO.
	public void render(Graphics g){
		
		g.drawImage(fondo, 0, 0, null); // DIBUJAMOS EL FONDO DEL ESCENARIO
		g.drawImage(piso, x, 0, null); // DIBUJAMOS EL PISO
		g.drawImage(piso2, x+piso.getWidth(), 0, null); // DIBUJAMOS EL PISO EXTRA, PARA REALIZAR LA SIMULACION DE MOVIMIENTO DEL MUNDO.
		//g.dispose();
	}

	public BufferedImage getFondo() {
		return fondo;
	}

	public void setFondo(BufferedImage fondo) {
		this.fondo = fondo;
	}

	public BufferedImage getPiso() {
		return piso;
	}

	public void setPiso(BufferedImage piso) {
		this.piso = piso;
	}

	
}
