package juego.botones;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

import juego.estados.Game;

/*
 *  CLASE PARA GENERAR UN BOTON MODIFICADO.
 * 
 * */
public class MiBoton extends JButton{

	private static final long serialVersionUID = 1L;
	String text; // TEXTO DEL BOTON
	
	public MiBoton(Game juego, String text) {
		super(text);
		setContentAreaFilled(false); // SE PONE EN FALSE PARA QUE AREA DEL BOTON NO SE DIBUJE
		setOpaque(false); // SE PONE EN FALSE PARA QUE EL BOTON SEA TRANSPARENTE
		setBorderPainted(false); // SE PONE EN FALSE PARA QUE NO SE PINTE EL BORDE DEL BOTON
		setRolloverEnabled(true); // SE PONE EN TRUE PARA ACTIVAR LA ACCION ROLLOVER DEL BOTON(SABER SI EL MOUSE ESTA ENCIMA DE EL O NO)
		this.text=text;
		
		//addActionListener(new AccionBoton(juego));
	}

	// SOBRE ESCRIBIMOS ESTE METODO, PARA QUE NOS PERMITA DIBUJAR EL BOTON CON NUESTRO PROPIO ESTILO.
	protected void paintComponent(Graphics g){
		super.paintComponent(g); // PRIMERO LE DECIMOS AL PAPA QUE DIBUJE SU PARTE.
		ButtonModel modelo=getModel(); // OBTENEMOS EL MODELO DEL BOTON, NOS PERMITIRA SABER SI EL BOTON ESTA SIENDO CLIQUEADO O SI EL MOUSE ESTA ENCIMA DE EL.
		FontMetrics fm=g.getFontMetrics(); // OBTENEMOS LAS METRICAS DE LA FUENTE DEL BOTON. TAMANO DE LA LETRA, ALTURA, ANCHO, TIPO ETC.
		Rectangle2D r2d=fm.getStringBounds(text,g); // OBTENEMOS UN RECTANGULO DEL TAMANO DEL TEXTO DEL BOTON, PARA PODER CALCULAR EL CENTRO.
		int x=(int) (getWidth()/2-r2d.getWidth()/2); // CALCULAMOS LA COORDENADA X PARA CENTRAR EL TEXTO DEL BOTON.
		int y=(int) (getHeight()/2+r2d.getHeight()/2); // CALCULAMOS LA COORDENADA Y PARA CENTRAR EL TEXTO DEL BOTON.
		
		g.setColor(Color.white); // PONEMOS EL PINCEL EN COLOR BLANCO. ESTE ES EL COLOR DEFAULT PARA EL TEXTO DE LOS BOTONES QUE HE ELEGIDO.
	
		// SI EL BOTON ESTA PRESIONADO ENTONCES...
		if(modelo.isArmed() && modelo.isPressed()){
			g.setColor(Color.white.darker()); // PONEMOS EL PINCEL A COLOR BLANDO OBSCURO
			
			// DIBUJAMOS UN RECTANGULO RELLENO CON LADOS REDONDEADOS DE COLOR BLANCO OBSCURO.
			/*
			 * g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
			 * x= coordenada x inicial del rectangulo.
			 * y=coordenada y inicial del rectangulo.
			 * width=ancho del rectangulo.
			 * height=alto del rectangulo.
			 * arcWidth=arco del ancho del rectangulo, que tan curveado quieres que se vea el ancho del rectangulo.
			 * arcHeight=arco del alto del rectangulo, que tan curveado quieres que se vea el alto del rectangulo.
			 * */
			g.fillRoundRect(20,5,(int)((x+r2d.getWidth())), (int)(5+r2d.getHeight()), 20,20);
			
			g.setColor(Color.yellow.brighter()); // PONEMOS EL PINCEL EN AMARILLO BRILLANTE PARA EL COLOR DEL TEXTO.
			g.drawString(text, x, y); // PINTAMOS EL TEXTO DE COLOR AMARILLO BRILLANTE.
			
		}
		else if(modelo.isRollover()){ // SI EL MOUSE SE ENCUENTRA ENCIMA DEL BOTON.
			g.setColor(Color.red); // PONEMOS EL PINCEL EN COLOR ROJO.
			g.drawString(">", x-15, y); // DIBUJAMOS LA CADENA > EN COLOR ROJO EN LAS COORDENADAS ESPECIFICADAS
			g.drawString("<",(int) (x+r2d.getWidth()+5), y);  // DIBUJAMOS LA CADENA < EN COLOR ROJO EN LAS COORDENADAS ESPECIFICADAS
			g.drawString(text, x, y); // DIBUJAMOS EL TEXTO DEL BOTON EN COLOR ROJO.
		}
		
		g.drawString(text, x, y); // DIBUJAMOS EL TEXTO DEL BOTON EN COLOR BLANCO. SI NO ENTRA A NINGUN IF. COLOR DEL TEXTO DEFAULT=BLANCO
		
		
	}
	
}
