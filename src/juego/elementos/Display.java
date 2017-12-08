package juego.elementos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * CLASE QUE CONTROLA LA VENTANA DE LA APLICACION.
 *  
 * */

public class Display extends JFrame{

	private static final long serialVersionUID = 1L;
	private int ancho; // ANCHO DE LA VENTANA.
	private int alto; // ALTO DE LA VENTANA.
	private String titulo; // TITULO DE LA VENTANA
	private JPanel pnlVista; // PANEL QUE CONTROLA QUE ES LO QUE SE MUESTRA EN LA VENTANA. ESTE PANEL TIENE EL CARDLAYOUT.
	
	public Display(int ancho, int alto, String titulo) throws HeadlessException {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.titulo = titulo;
		init();
	}

	public void init(){
		setSize(ancho, alto); // ESTABLECEMOS EL TAMANO DE LA VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // LE DECIMOS A LA APLICACION QUE EL METODO DEL BOTON CERRAR ES CERRAR LA APLICACION.
		setTitle(titulo); // ESTABLECEMOS EL TITULO DE LA APLICACION
		setResizable(false); // ESTABLECEMOS QUE LA VENTANA NO SE PUEDA REDIMENSIONAR. TAMANO FIJO.
		setLocationRelativeTo(null); // ESTABLECEMOS QUE LA VENTANA SE POSICIONE EN EL CENTRO DE LA PANTALLA.
		getContentPane().setLayout(new BorderLayout(0,0)); //<--- ESTABLECEMOS NUEVO LAYOUT
		
		pnlVista=new JPanel(); // INICIALIZAMOS EL PANEL VISTA
		pnlVista.setLayout(new CardLayout(0,0)); // PANEL QUE CONTROLA QUE ES LO QUE SE MUESTRA EN LA VENTANA. ESTE PANEL TIENE EL CARDLAYOUT.
		getContentPane().add(pnlVista, BorderLayout.CENTER); // AGREGAMOS EL PANEL VISTA EN EL CENTRO DE LA VENTANA
		
		
		//pack(); //<--- AJUSTA TAMANO DE LA PANTALLA, PARA QUE TODOS LOS ELEMENTOS SEAN VISIBLES. 
		setVisible(true); //<--- AL FINAL SIEMPRE, SI NO LO PONEN AL FINAL MOSTRARA UNA VENTANA VACIA UN TIEMPO, HASTA QUE TERMINE DE CONSTRUIRSE LA VENTANA.
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	// METODO PARA RETORNAR EL PANEL QUE CONTROLA QUE ES LO QUE SE VISUALIZA EN LA VENTANA.
	public JPanel getPnlVista() {
		return pnlVista;
	}
	
	
	
}
