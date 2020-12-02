import javax.swing.*;

public class Display {
	public static final int width = 650, height = 637;
	private JFrame window;
	private Board board = new Board();;
	private Sound bgm;
 
	
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
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
		window.add(board);
		window.addKeyListener(board);
		window.setVisible(true);		
	}
	
	public void BGM()
	{
		if(board.getState() == 0)
		{
			Sound.playMusic();
			
		}
		
	}
}
