package juego.paneles;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JButton;
import javax.swing.JPanel;

import juego.manager.KeyManager;
import juego.manager.MouseManager;
/*
 * 
 * CLASE PARA CONSTRUIR EL PANEL DEL JUEGO.
 * 
 * */
public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Canvas canvas; // LIENZO PARA PINTAR EL JUEGO.
	private KeyManager km; // OBJETO PARA CONTROLAR LOS EVENTOS DEL TECLADO.
	private MouseManager mouse; //OBJETO PARA CONTROLAR LOS EVENTOS DEL MOUSE.
	
	public GamePanel() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init(){
		
		setLayout(new BorderLayout(0,0));
		canvas=new Canvas(); // INICIALIZAMOS EL CANCAS(LIENZO)
		canvas.setSize(300, 250); // DECIMOS QUE NUESTRO LIENZO TENDRA UN TAMANO DE ANCHO=300, ALTO=250
		add(canvas,BorderLayout.CENTER); // <--- AGREGAMOS EL CANVAS AL CENTRO DEL CONTENTPANE
		
		
		JPanel panelBotones=new JPanel(); //<-- PANEL PARA INCLUIR BOTONES/
		
		mouse=new MouseManager(); // INICIALIZAMOS EL OBJETO QUE CONTROLA LOS EVENTOS DEL MOUSE.
		
		JButton btnLeft =new JButton("<---"); // BOTON IZQUIERDO.
		btnLeft.setActionCommand("left"); // ESTABLECEMOS LA ACCION QUE DESARROLLARA EL BOTON.
		btnLeft.addMouseListener(mouse); // AGREGAMOS AL BOTON, EL OBJETO QUE CONTROLARA LOS EVENTOS DEL MOUSE CON EL BOTON.
		panelBotones.add(btnLeft); // AGREGAMOS EL BOTON AL PANEL.
		
		
		JButton btnRight=new JButton("-->"); // BOTON DERECHO.
		btnRight.setActionCommand("right");// ESTABLECEMOS LA ACCION QUE DESARROLLARA EL BOTON.
		btnRight.addMouseListener(mouse); // AGREGAMOS AL BOTON, EL OBJETO QUE CONTROLARA LOS EVENTOS DEL MOUSE CON EL BOTON.
		panelBotones.add(btnRight); // AGREGAMOS EL BOTON AL PANEL.
		
		JButton btnFire=new JButton("FIRE");// BOTON FIRE
		btnFire.setActionCommand("fire");// ESTABLECEMOS LA ACCION QUE DESARROLLARA EL BOTON.
		btnFire.addMouseListener(mouse);// AGREGAMOS AL BOTON, EL OBJETO QUE CONTROLARA LOS EVENTOS DEL MOUSE CON EL BOTON.
		panelBotones.add(btnFire); // AGREGAMOS EL BOTON AL PANEL.
		
		
		add(panelBotones,BorderLayout.SOUTH); //<--- AGREGAMOS AL SUR DEL CONTENTPANE, EL PANEL QUE CONTIENE BOTONES
		
		km=new KeyManager(); // INICIALIZAMOS EL OBJETO QUE CONTROLA LOS EVENTOS DEL TECLADO.
		canvas.addKeyListener(km); // AGREGAMOS AL CANVAS(LIENZO), EL OBJETO QUE CONTROLA LOS EVENTOS DEL TECLADO.
		canvas.setFocusable(true); // PONEMOS EN FOCO AL LIENZO.
		//addKeyListener(km);
		//setFocusable(true);
		

		setVisible(true); // HACEMOS VISIBLE AL PANEL.
		//add(canvas);
	}
	
	// METODO PARA RETORNAR EL LIENZO.
	public Canvas getCanvas() {
		return canvas;
	}

	// METODO PARA RETORNAR EL OBJETO QUE CONTROLA LOS EVENTOS DEL TECLADO.
	public KeyManager getKm() {
		return km;
	}

	// METODO PARA RETORNAR EL OBJETO QUE CONTROLA LOS EVENTOS DEL MOUSE.
	public MouseManager getMouse() {
		return mouse;
	}
	
	
	
	
}
