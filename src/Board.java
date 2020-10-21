import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.*;

public class Board extends JPanel implements KeyListener{
	
	public static final int BOARD_WIDTH =10; 
	public static final int BOARD_HEIGHT =20; 
	public static final int BLOCK_SIZE = 30;
	
	private static final int FPS = 60;
	private static int delay = FPS/1000;
	
	private Timer looper;
	private Color[][] board = new Color[BOARD_WIDTH][BOARD_HEIGHT];
	//shapes
	private Color[][] shape = {
			{Color.RED, Color.RED,Color.RED},
			{null, Color.RED,null}
	};
	//END	
	private int x = 4;
	private int y = 0;
	
	//Speeds
	private int normal = 600;
	private int fast = 50;
	private int delayTimeForBlockSpeed = normal;
	private long startTime;
	
	private int sideWayMove = 0;
	private boolean collision = false;
	public Board(){
		//TIMER or tick generator
		
		looper = new Timer(delay, new ActionListener(){
			
			@Override
			//check x-axis.
			public void actionPerformed(ActionEvent e) {
				//check collision
				if(collision)
				{
					return;
				}
				if(x + sideWayMove + shape[0].length <= BOARD_WIDTH && x + sideWayMove >= 0) {
				x += sideWayMove;
				}
				sideWayMove = 0;
				if(System.currentTimeMillis() - startTime> delayTimeForBlockSpeed)
				{		
					if(y+ shape.length < BOARD_HEIGHT) {
					
						y++;
					
				}
					else
					{
						collision = true;
					}
					startTime = System.currentTimeMillis();
				}
				repaint();
			}
		});
		
		looper.start();
	
}	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		//draw shapes
		for(int i = 0; i < shape.length;i++)
		{
			for(int j = 0; j < shape[0].length;j++)
			{
				if(shape[i][j] != null) {
						g.setColor(shape[i][j]);
						g.fillRect(j * BLOCK_SIZE + x * BLOCK_SIZE, i * BLOCK_SIZE + y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
}
		
		//draw board
		g.setColor(Color.white);
		for(int row = 0; row < BOARD_HEIGHT;row++)
		{
			g.drawLine(0, row*BLOCK_SIZE, BLOCK_SIZE*BOARD_WIDTH, row*BLOCK_SIZE);
		}
		for(int col = 0; col < BOARD_WIDTH +1;col++)
		{
			g.drawLine(col*BLOCK_SIZE, 0, col* BLOCK_SIZE,BOARD_HEIGHT*BLOCK_SIZE);
		}
	}
	
	//Controls

	@Override
	//Arrows
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			delayTimeForBlockSpeed = fast;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			sideWayMove++;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			sideWayMove--;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				delayTimeForBlockSpeed = normal;
			}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

