package juego.elementos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import juego.estados.Juego;
import juego.manager.Animacion;
import juego.manager.Recursos;
/*
 * 
 * CLASE PARA CONTROLAR AL JUGADOR.
 * 
 * */
public class Jugador {

	private int x; // COORDENADA X DEL JUGADOR.
	private int y; // COORDENADA Y DEL JUGADOR. 
	private int dx; // VELOCIDAD DEL JUGADOR.
	private ArrayList<Bala> listaBalas; // LISTA DE BALAS QUE HA DISPARADO EL JUGADOR
	private BufferedImage imagen; // IMAGEN DEL JUGADOR
	private Juego juego; // OBJETO QUE CONTINE LA VENTANA PRINCIPAL DE LA APLICACION.
	private Animacion jugadorIzquierda; // OBJETO PARA CONTROLAR LA ANIMACION DEL JUGADOR AL PRESIONAR LA TECLA FLECHA IZQUIERDA.
	private Animacion jugadorDerecha; // OBJETO PARA CONTROLAR LA ANIMACION DEL JUGADOR AL PRESIONAR LA TECLA FLECHA DERECHA.
	
	public Jugador(Juego juego,int x, int y) {
		// INICIALIZAMOS VARIABLES
		this.juego=juego;
		this.x = x;
		this.y = y;
		dx=1;
		imagen=Recursos.jugador;
		jugadorIzquierda=new Animacion(100, Recursos.jugadorIzquierda);
		jugadorDerecha=new Animacion(100, Recursos.jugadorDerecha);
		listaBalas=new ArrayList<Bala>();
	}

	// METODO PARA ACTUALIZAR VARIABLES DEL JUGADOR.
	public void update(){
		
		// CHECA SI EL JUGADOR SE DESPLAZA A LA DERECHA
		if(juego.getPnlJuego().getKm().isDerecha() || juego.getPnlJuego().getMouse().isRight()){
			if(x<=juego.getJuego().getAncho()-150){ // CHECAMOS QUE LA COORDENADA X NO SE SALGA DEL LIMITE
				x+=dx; // INCREMENTAMOS LA COORDENADA X
			}
			jugadorIzquierda.stop(); // DETENEMOS ANIMACION DE JUGADOR IZQUIERDA
			jugadorDerecha.start(); // INICIAMOS ANIMACION DE JUGADOR DERECHA
		}
		// CHECA SI EL JUGADOR SE DESPLAZA A LA IZQUIERDA
		else if(juego.getPnlJuego().getKm().isIzquierda() || juego.getPnlJuego().getMouse().isLeft()){ // CHECAMOS QUE LA COORDENADA X NO SE SALGA DEL LIMITE.
			if(x>=10){
				x-=dx; // DECREMENTAMOS LA COORDENADA X
			}
			jugadorDerecha.stop(); // DETENEMOS ANIMACION DE JUGADOR DERECHA.
			jugadorIzquierda.start(); // INICIAMOS ANIMACION DE JUGADOR IZQUIERDA.
		}
		// CHECA SI EL JUGADOR DISPARA
		if(juego.getPnlJuego().getKm().isDisparo() || juego.getPnlJuego().getMouse().isFire()){
			String direccion="right";
			int xBala=x+imagen.getWidth()+2; // COORDENADA X DE LA BALA
		    int yBala=y+10; // COORDENADA Y DE LA BALA
			if(juego.getPnlJuego().getKm().isIzquierda()){
				direccion="left";
				xBala=x-2; // COORDENADA X DE LA BALA SI ES POR EL LADO IZQUIERDO
			}
			listaBalas.add(new Bala(xBala, yBala,direccion)); // AGREGAMOS LA NUEVA BALA A LA LISTA DE BALAS EXISTENTES.
			juego.getPnlJuego().getKm().setDisparo(false); // PONEMOS A DISPARO EN FALSE DEL KEYMANAGER PARA QUE NO SIGA DISPARANDO
			juego.getPnlJuego().getMouse().setFire(false); // PONEMOS A DISPARO EN FALSE DEL MOUSEMANAGER PARA QUE NO SIGA DISPARANDO
		}
		
		for(int i=0;i<listaBalas.size();i++){ // RECORREMOS LAS BALAS EXISTENTES
			
			Bala item= listaBalas.get(i);
			item.update(); // ACTUALIZAMOS POSICION DE LA BALA
			
			if(item.getX()<0 || item.getX()>320){ // SE PREGUNTA SI LA BALA ESTA FUERA DE RANGO DEL JUEGO
				
				/*
				 * NOTA: Los ArrayList tienen el metodo remove para eliminar elementos, este metodo esta sobrecargado.
				 * Primera opcion= remove(int index) ---> Remueve el elemento en el indice especificado.
				 * Segunda opcion= remove(Object item) ---> Remueve un OBJETO si existe en el arreglo, si hay mas de dos objetos iguales remueve al primero
				 * 											que encuentra.
				 * 
				 * EL METODO UPDATE PUEDE CAUSAR PROBLEMAS AL ESTAR LLAMANDOSE 60 VECES POR SEGUNDO, PUEDE PASAR QUE QUIERAN
				 * ELIMINAR OBJETOS QUE YA NO EXISTEN, Y NO PUEDEN APRECIARLO EN EL JUEGO YA QUE AL USAR LA ESTRATEGIA BufferStrategy
				 * ESTAN OBSERVANDO UN FRAME ANTIGUO MIENTRAS LOS NUEVOS ESTAN EN LA COLA DE FRAMES. 
				 * POR ESTE MOTIVO ES MEJOR USAR LA SEGUNDA OPCION PARA REMOVER OBJETOS YA QUE VALIDA SI EXISTE O NO, SI EXISTE LO REMUEVE
				 * SI NO EXISTE NO HACE NADA, EVITAMOS EXCEPCIONES DE INTENTAR ELIMINAR ALGO QUE YA NO EXISTE.
				 * 
				 * 
				 * */
				listaBalas.remove(item); // SI ESTA SE REMUEVE LA BALA.
			}
			
			for(int j=0;j<juego.getListaEnemigos().size();j++){ // RECORRE  LA LISTA DE ENEMIGOS
				
				Enemigo enemigo=juego.getListaEnemigos().get(j); // OBTENEMOS EL ENEMIGO ACTUAL
				
				if (item.getBounds().intersects(enemigo.getBounds())){  // CHECAMOS SI EL ENEMIGO CHOCA CON NUESTRA BALA
					 juego.getListaEnemigos().remove(enemigo); // SI CHOCA REMOVEMOS AL ENEMIGO
					 listaBalas.remove(item); // SI CHOCA REMOVEMOS LA BALA
					 break; // SALIMOS DEL FOR QUE RECORRE LOS ENEMIGOS.
				}
				
			}
		}
		
	}
	

	public void render(Graphics g){
		
		// DIBUJAMOS AL JUGADOR CON EL FRAME ACTUAL (IMAGEN ACTUAL)
		g.drawImage(currentFrame(), x, y, null);
		
		// DIBUJAMOS LAS BALAS
		for(int i=0;i<listaBalas.size();i++){
			listaBalas.get(i).render(g);
		}
		//g.dispose();
	}
	
	public BufferedImage currentFrame(){
		
		// SI EL JUGADOR SE DESPLAZA A LA DERECHA, OBTENEMOS LA IMAGEN ACTUAL DE LA ANIMACION DEL JUGADOR DERECHA.
		if(juego.getPnlJuego().getKm().isDerecha() || juego.getPnlJuego().getMouse().isRight()){
			return jugadorDerecha.currentFrame();
		}
		// SI EL JUGADOR SE DESPLAZA A LA  IZQUIERDA, OBTENEMOS LA IMAGEN ACTUAL DE LA ANIMACION DEL JUGADOR IZQUIERDA.
		else if(juego.getPnlJuego().getKm().isIzquierda() || juego.getPnlJuego().getMouse().isLeft()){
			return jugadorIzquierda.currentFrame();
		}
		// SI NO TIENE MOVIMIENTO, CARGAMOS UNA IMAGEN DEFAULT. PODRIA CAMBIARSE A UNA ANIMACION DEFAULT. 
		else 
			return Recursos.jugador;
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
	
	// METODO PARA RETORNAR UN RECTANGULO CON LAS DIMENSIONES DEL JUGADOR, PARA LAS COLISIONES.
	public Rectangle getBounds(){
		return new Rectangle(x,y,imagen.getWidth(), imagen.getHeight());
	}
}
