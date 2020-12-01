import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;

public class Sound {
	
	public void playMusic() {
		
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
	public void playSoundEffect()
	{
		
	}
}
