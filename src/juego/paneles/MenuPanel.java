package juego.paneles;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.botones.MiBoton;
import juego.estados.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * CLASE PARA CONSTRUIR EL PANEL DEL MENU
 * */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MiBoton btnNew; // BOTON NUEVO DEL MENU
	private MiBoton btnAbout; // BOTON ABOUT DEL MENU
	private MiBoton btnQuit; // BOTON QUIT DEL MENU
	private MiBoton btnOptions; // BOTON OPCIONES DEL MENU
	
	public MenuPanel( final Game juego) {
		
		setBackground(new Color(0, 0, 0)); // PONEMOS EL FONDO DEL PANEL EN NEGRO
		setLayout(null); // PONEMOS EL LAYOUT EN ABSOLUT, PARA SITUAR LOS ELEMENTOS EN LAS COORDENADAS QUE MAS NOS CONVENGAN.
		
		btnOptions=new MiBoton(juego,"Options"); // CREAMOS EL BOTON OPCIONES
		btnOptions.setBounds(6, 92, 117, 29); // ESTABLECEMOS LA COORDENADA Y DIMENSIONES DEL BOTON.
		add(btnOptions); // AGREGAMOS EL BOTON AL PANEL
		
		
		btnNew = new MiBoton(juego,"New"); // CREAMOS EL BOTON NEW
		// AGREGAMOS LA ACCION QUE REALIZARA EL BOTON NEW
		btnNew.addActionListener(
			// CLASE ANONIMA PARA CONTROLAR LA ACCION DEL BOTON
			new ActionListener() {
				// METODO QUE SE EJECUTARA CUANDO REALIZAMOS CLICK EN EL BOTON.
				public void actionPerformed(ActionEvent e) {
					juego.getJuego().show(); // MUESTRA EN PANTALLA EL PANEL DEL JUEGO.
				}
			}
		);
		btnNew.setBounds(6, 64, 117, 29);// ESTABLECEMOS LA COORDENADA Y DIMENSIONES DEL BOTON.
		add(btnNew); // AGREGAMOS EL BOTON AL PANEL

		
		btnAbout = new MiBoton(juego,"About");// CREAMOS EL BOTON ABOUT
		btnAbout.setBounds(6, 120, 117, 29);// ESTABLECEMOS LA COORDENADA Y DIMENSIONES DEL BOTON.
		add(btnAbout);// AGREGAMOS EL BOTON AL PANEL
		
		
		btnQuit = new MiBoton(juego,"Quit"); // CREAMOS EL BOTON QUIT
		btnQuit.setBounds(6, 152, 117, 29); // ESTABLECEMOS LA COORDENADA Y DIMENSIONES DEL BOTON.
		add(btnQuit);// AGREGAMOS EL BOTON AL PANEL
		

		JLabel lblNombreDeMi = new JLabel("NOMBRE DE MI JUEGO"); // CREAMOS UN LABEL PARA INCLUIR EL NOMBRE DEL JUEGO
		lblNombreDeMi.setForeground(new Color(165, 42, 42)); // ESTABLECEMOS EL COLOR DEL LABEL
		lblNombreDeMi.setBounds(173, 40, 146, 16); // ESTABLECEMOS  LA COORDENADA Y DIMENSION DEL LABEL.
		add(lblNombreDeMi); // AGREGAMOS EL LABEL AL PANEL.
		
		JLabel lblMaximoScore = new JLabel("MAXIMO SCORE: 10000 - JUAN"); // CREAMOS UN LABEL PARA MOSTRAR EL MAXIMO PUNTUAJE DEL JUEGO.
		lblMaximoScore.setForeground(new Color(30, 144, 255)); // ESTABLECEMOS EL COLOR DEL LABEL.
		lblMaximoScore.setBounds(145, 177, 230, 16); // ESTABLECEMOS LA COORDENADA Y LA DIMENSION DEL LABEL.
		add(lblMaximoScore); // AGREGAMOS EL LABEL AL PANEL.
		
	}

}
