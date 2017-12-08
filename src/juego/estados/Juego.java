package juego.estados;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.Timer;

import juego.elementos.Enemigo;
import juego.elementos.Escenario;
import juego.elementos.Jugador;
import juego.manager.Recursos;
import juego.paneles.GamePanel;

public class Juego implements Runnable {
	
	
	private boolean activo; // VARIABLE PARA VER SI EL JUEGO ESTA ACTIVO.
	private Thread gameLoop; // HILO PARA CONTROLAR EL CICLO DEL JUEGO.
	private Graphics g; // OBJETO PARA CONTROLAR EL DIBUJADO DE LA PANTALLA.

	private GamePanel pnlJuego; // PANEL QUE CONTIENE LA INTERFAZ GRAFICA DEL JUEGO.
	private Escenario escenario; // ESCENARIO DEL JUEGO
	private Jugador jugador; // OBJETO QUE CONTROLA EL JUGADOR
	private ArrayList<Enemigo> listaEnemigos; // TODOS LOS ENEMIGOS DEL JUEGO.
	private Game juego; // OBJETO QUE CONTINE LA VENTANA PRINCIPAL DE LA APLICACION.
	private BufferStrategy bs; // OBJETO PARA CONTROLAR LOS FRAMES QUE VEMOS EN PANTALLA.
	private Timer t; // TIMER PARA CONTROLAR LA APARICION DE ENEMIGOS.
	
	public Juego(Game juego) {
		this.juego=juego;
		pnlJuego=new GamePanel(); //  INICIALIZAMOS LA INTERFAZ DEL JUEGO
		juego.getVentana().getPnlVista().add(pnlJuego,"Juego"); //// AGREGAMOS EL PANEL DEL JUEGO AL CARDLAYOUT DE LA VENTANA PRINCIPAL.
		//init();
	}

	// METODO PARA INICIALIZAR EL JUEGO.
	public void init() {
			
		escenario=new Escenario(this); // INICIALIZAMOS EL ESCENARIO DEL JUEGO.
		jugador=new Jugador(this,10,160); // INICIALIZAMOS EL JUGADOR DEL JUEGO.
		listaEnemigos=new ArrayList<Enemigo>(); // INICIALIZAMOS LA LISTA DE ENEMIGOS.
		
		// AGREGAMOS 3 ENEMIGOS COMO PRUEBA.
		listaEnemigos.add(new Enemigo(250, 160)); 
		listaEnemigos.add(new Enemigo(300, 160));
		listaEnemigos.add(new Enemigo(350, 160));
		
		// INICIALIZAMOS EL TIMER QUE CONTROLA LA APARICION DE ENEMIGOS. 
		// PRIMER PARAMETRO DEL CONSTRUCTOR ES EL TIEMPO EN MILISEGUNDOS QUE EL TIMER TARDA EN EJECUTARSE.
		// SEGUNDO PARAMETRO DEL CONSTRUCTOR ES LA ACCION QUE REALIZARA EL TIMER.
		t=new Timer(1000, new ActionListener() {
			// METODO DE ACCION DEL TIMER
			public void actionPerformed(ActionEvent e) {
				listaEnemigos.add(new Enemigo(300, 160)); // AGREGA UN NUEVO ENEMIGO EN LA COORDENADA X=300, Y=160.
			}
		});
		
		Recursos.audioMenu.stop();// PARAMOS EL SONIDO DEL MENU
		Recursos.audioJuego.loop(); // INICAMOS EL SONIDO DEL JUEGO.
		
	}
	
	
	// METODO PARA DIBUJAR LOS COMPONENTES DEL JUEGO.
	public void render(Graphics g) {
		
		bs=pnlJuego.getCanvas().getBufferStrategy(); // INICIALIZAMOS EL BUFFER QUE CONTIENE LOS FRAMES DEL JUEGO.
		
		if(bs==null){ // SI ES NULO
		
			pnlJuego.getCanvas().createBufferStrategy(3); // CREAMOS UN NUEVO BUFFER CON 3 FRAMES.
			return;
		}
		
		g=bs.getDrawGraphics(); // INICIALIZAMOS EL OBJETO GRAPHICS PARA PODER DIBUJAR.
		g.clearRect(0, 0,juego.getAncho(), juego.getAlto()); // LIMPIAMOS LA PANTALLA DEL JUEGO.
		// PINTAR ELEMENTOS
		escenario.render(g); // PINTAMOS EL ESCENARIO.
		jugador.render(g); // PINTAMOS AL JUGADOR.
		
		// RECORREMOS LA LISTA DE ENEMIGOS.
		for(int i=0; i<listaEnemigos.size();i++){
			listaEnemigos.get(i).render(g); // DIBUJAMOS ENEMIGO POR ENEMIGO
		}
		// FIN DEL PINTADO
		bs.show(); // MOSTRAMOS EL FRAME DIBUJADO.
		g.dispose(); // LIBERAMOS AL OBJETO GRAPHICS. LIBERACION DE MEMORIA.
		
	}

	
	// METODO PARA ACTUALIZAR TODAS LAS VARIABLES DEL JUEGO. LOGICA DEL JUEGO.
	public void update() {
		escenario.update(); // ACTUALIZAMOS LAS VARIABLES DEL ESCENARIO.
		jugador.update(); // ACTUALIZAMOS LAS VARIABLES DEL JUGADOR.
		
		// RECORREMOS LA LISTA DE ENEMIGOS
		for(int i=0; i<listaEnemigos.size();i++){
			Enemigo e=listaEnemigos.get(i); // OBTENEMOS UN ENEMIGO EN LA POSICION i
			e.update(); // ACTUALIZAMOS AL ENEMIGO
			if(jugador.getBounds().intersects(e.getBounds())){ // PREGUNTAMOS SI CHOCA CON EL JUGADOR.
				listaEnemigos.remove(i); // SI CHOCA REMOVEMOS AL ENEMIGO, PODRIAN INCLUIR BAJAR SALUD DEL JUGADOR.
			}
			
		}
		
	}

	
	// METODO PARA MOSTRAR EL PANEL DEL JUEGO.
	public void show() {
		CardLayout c=(CardLayout)juego.getVentana().getPnlVista().getLayout(); // OBTENEMOS EL LAYOUT DE LA VENTANA.
		c.show(juego.getVentana().getPnlVista(), "Juego"); // MOSTRAMOS EN PANTALLA EL PANEL QUE CONTIENE LA INTERFAZ DEL JUEGO.
		juego.getVentana().setVisible(true); // HACEMOS VISIBLE LA VENTANA.
		start();// LLAMAMOS AL METODO START, PARA EJECUTAR EL CICLO DEL JUEGO.
	}

	public GamePanel getPnlJuego() {
		return pnlJuego;
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Game getJuego() {
		return juego;
	}

	public ArrayList<Enemigo> getListaEnemigos() {
		return listaEnemigos;
	}

	public void setListaEnemigos(ArrayList<Enemigo> listaEnemigos) {
		this.listaEnemigos = listaEnemigos;
	}

	
	// INICIAMOS EL CICLO DEL JUEGO
	public synchronized void start(){
		if(activo)
			return;
		activo=true;
		gameLoop=new Thread(this); // INICIALIZAMOS HILO.
		gameLoop.start();// EJECUTAMOS HILO, MANDA LLAMAR AL METODO RUN.
	}
	
	// PARA EL CICLO DEL JUEGO.
	public  synchronized void stop(){
		if(!activo)
			return;
		activo=false;
		try {
			gameLoop.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// ACCION DEL HILO.
	public void run() {
		// TODO Auto-generated method stub
		init();//<--- INICIALIZAR JUEGO
		t.start(); // INICIALIZAMOS TIMER PARA APARICION DE ENEMIGOS.
		int fps=60;
		double nanoPorFrame=1000000000/fps;
		long nuevo;
		long pasado=System.nanoTime();
		double delta=0;
		// variables auxiliares o de testeo
		long time=0;
		int ticks=0;
		
		while(activo){
			
			nuevo=System.nanoTime();
			time+=nuevo-pasado; //<--- testeo  
			delta+=(nuevo-pasado)/nanoPorFrame;
			pasado=nuevo;
			
			if(delta>=1){
				update();//ACTUALIZACION DE VARIABLES Y LOGICA.
				render(g);//REPINTADO.
				delta--;
				ticks++;
			}
			
			// testeo
			if(time>=1000000000){
				System.out.println("Frames por segundo: "+ticks);
				ticks=0;
				time=0;
			}

		}
		
	}
	
	
}
