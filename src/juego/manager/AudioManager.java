package juego.manager;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 *  CLASE PARA CONTROLAR EL AUDIO, FORMATO DEL AUDIO WAV.
 * 
 * */
public class AudioManager {

	Clip clip;
	
	public AudioManager(String path) {
		// TODO Auto-generated constructor stub
		try {
			clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(AudioManager.class.getResourceAsStream(path)));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// METODO PARA INICIAR SONIDO.
	public void start(){
		if(clip.isOpen() && !clip.isRunning())
			clip.start();
	}
	
	// METODO PARA DETENER SONIDO.
	public void stop(){
		if(clip.isRunning())
			clip.stop();
	}
	
	// METODO PARA INICIAR SONIDO EN UN CICLO INFINNITO.
	public void loop(){
		if(clip.isOpen() && !clip.isRunning())
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// METODO PARA INICIAR SONIDO EN UN CICLO DE N VECES.
	public void loop(int n){
		if(clip.isOpen() && !clip.isRunning())
			clip.loop(n);
	}
}
