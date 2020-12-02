import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public static Board board;
	public static  void playMusic() {
		
		try 
			{
			File musicPath = new File("tetris.wav");
			if(musicPath.exists()) {
				
				AudioInputStream audioinput= AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioinput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f);
				clip.start();
				clip.loop(LOOP_CONTINUOUSLY);
				
			}
			else {
				System.out.println("can't find file music");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
}
	public void stopMusic() {
		File musicPath = new File("tetris.wav");
		try {
			AudioInputStream audioinput= AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.stop();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void playSoundEffect(String soundfile)
	{
		try 
		{
		File musicPath = new File(soundfile);
		
		if(musicPath.exists()) {
			
			AudioInputStream audioinput= AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.open(audioinput);
			clip.start();
					
		}
		else {
			System.out.println("can't find file music");
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
	}
}
