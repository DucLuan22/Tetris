 
import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

public class Display {
	public final int width = 650, height = 637;
	private JFrame window;
	private Board board = new Board();;
	
	public  int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public Display()
	{
		window = new JFrame("Tetris");
		window.setSize(width,height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		board = new Board();
		BGM();
		window.setVisible(true);		
		window.add(board);
		window.addKeyListener(board);
	}
	
	public void BGM()
	{
		Sound.playMusic();	
	}
}