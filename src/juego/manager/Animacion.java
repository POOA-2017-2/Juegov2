package juego.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

/*
 * CLASE ENCARGADA DE REALIZAR LAS ANIMACIONES DEL JUEGO.
 * 
 * */
public class Animacion {

	private BufferedImage[] frames; // ARREGLOS DE IMAGENES PARA CONTROLAR LA ANIMACION.
	private int speed; // VELOCIDAD DE LA ANIMACION.
	private int index; // INDICE PARA CONTROLAR EL ARREGLO DE IMAGENES
	private Timer temporizador; // TIMER QUE CONTROLA EL RECORRIDO DE LAS IMAGENES
	
	public Animacion(int speed, BufferedImage[] frames) {
		// INICIALIZAMOS VARIABLES.
		this.speed=speed;
		this.frames=frames;
		index=0;
		
		
		// TIMER PARA CONTROLAR LA ANIMACION, ACTUALIZA LA IMAGEN DE LA ANIMACION INCREMENTANDO EL INDICE PARA RECORRER LA LISTA DE IMAGENES.
		// SPEED ES LA VELOCIDAD DE ACTUALIZACION EN ESTE CASO.
		// SEGUNDO PARAMETRO LA ACCION DEL TIMER. EN ESTE CASO MANDA A LLAMAR EL METODO UPDATE.
		temporizador=new Timer(speed, new ActionListener() {
			
			// ACCION DEL TIMER
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update();
			}
		});
		
		// LE DECIMOS AL TIMER QUE SE EJECUTE INFINITAMENTE.
		temporizador.setRepeats(true);
	}
	
	// METODO PARA ACTUALIZAR VARIABLES. CONTROLA EL MOVIMIENTO DE LA ANIMACION.
	public void update(){
		index++; // INCREMENTAMOS INDICE PARA PASAR A LA SIGUIENTE IMAGEN DEL ARREGLO.
		
		// SI EL INDICE ES MAYOR O IGUAL A  LA LONGITUD DE LA LISTA DE IMAGENES, YA NO HAY IMAGENES PARA MOSTRAR. 
		// POR LO TANTO REINICIAMOS EL INDICE A 0 PARA QUE VUELVA AL INICIO DE LA LISTA DE IMAGENES.
		if(index>=frames.length){ 
			index=0;
		}
	}

	// METODO QUE RETORNA LA IMAGEN QUE ACTUALMENTE ESTA ACTIVA. 
	public BufferedImage currentFrame(){
		return frames[index];
	}
	
	// METODO PARA INICIAR LA ANIMACION
	public void start(){
		// SI EL TIMER AUN NO HA INICIADO
		if(!temporizador.isRunning()){
			index=0; // INICIALIZAMOS EL INDICE EN 0. ES DECIR LA PRIMERA IMAGEN DE LA LISTA DE IMAGENES.
			temporizador.start(); // INICIAMOS LA ACCION DEL TIMER. TIMER MANDA A LLAMAR SU ACCION CADA CIERTO TIEMPO.
		}

	}

	// METODO PARA PARAR LA ANIMACION.
	public void stop(){
		// PREGUNTAMOS SI EL TIMER ESTA EN EJECUCION.
		if(temporizador.isRunning()) 
			temporizador.stop(); // PARAMOS EL TIMER.
	}

	
	public int getSpeed() {
		return speed;
	}

}
