package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sounds() {
		soundURL[0] = getClass().getResource("/sounds/moan1.wav");
		soundURL[1] = getClass().getResource("/sounds/coin.wav");
		soundURL[2] = getClass().getResource("/sounds/powerup.wav");
		soundURL[3] = getClass().getResource("/sounds/lasergun.wav");
		soundURL[4] = getClass().getResource("/sounds/unlock.wav");
		soundURL[5] = getClass().getResource("/sounds/speak.wav");
		soundURL[6] = getClass().getResource("/sounds/fanfare.wav");
		soundURL[7] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch(Exception e) {
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		
		clip.stop();
	}
}
