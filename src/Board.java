import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.*;

public class Board extends JPanel implements KeyListener{
	
	public static final int BOARD_WIDTH =10; 
	public static final int BOARD_HEIGHT =20; 
	public static final int BLOCK_SIZE = 30;
	
	public static int STATE_GAME_PLAY = 0;
	public static int STATE_GAME_OVER = 2;
	public static int STATE_GAME_PAUSE = 1;
	
	private int state = STATE_GAME_PLAY;
	
	private static final int FPS = 60;
	private static int delay = FPS/1000;
	
	private Timer looper;
	private Color[][] board = new Color[BOARD_HEIGHT][BOARD_WIDTH];
	
	private Random random;
	
	private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
	        Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
	//shapes
	private Shape[] shapes = new Shape[7];
	private Shape chooseShape;
	
	public Board(){
		
		random = new Random();
		
		//Shapes
		
		shapes[0] = new Shape(new int[][]{
			{1, 1},
	        {1, 1}, 
        }, this, colors[0]);

        shapes[1] = new Shape(new int[][]{
            {1, 1, 1},
            {0, 1, 0}, 
        }, this, colors[1]);

        shapes[2] = new Shape(new int[][]{
            {1, 1, 1},
            {1, 0, 0}, 
        }, this, colors[2]);

        shapes[3] = new Shape(new int[][]{
            {1, 1, 1},
            {0, 0, 1}, 
        }, this, colors[3]);

        shapes[4] = new Shape(new int[][]{
        	{1, 1, 0},
            {0, 1, 1}, 
        }, this, colors[4]);

        shapes[5] = new Shape(new int[][]{
        	 {0, 1, 1},
             {1, 1, 0},  
        }, this, colors[5]);

        shapes[6] = new Shape(new int[][]{
        	{1, 1, 1, 1} 
        }, this, colors[6]);
        
        
        chooseShape = shapes[random.nextInt(shapes.length)];
        //TIMER or ticker
		looper = new Timer(delay, new ActionListener(){
			
			@Override  
			public void actionPerformed(ActionEvent e) {
				//check collision
				update();
				repaint();
			}
	});
		
		looper.start();
	
}	
	
	public void update() {
		if(state == STATE_GAME_PLAY) {
			
			chooseShape.update();
		}
	}
	
	public void setChooseShape() {
		chooseShape = shapes[random.nextInt(shapes.length)];
		chooseShape.reset();
		checkOverGame();
	}
	
	private void checkOverGame() {
		int [][] coords = chooseShape.getCoords();
		for(int row = 0; row < coords.length;row++)
		{
			for(int col = 0; col < coords[0].length;col++)
			{
				if(coords[row][col] != 0)
				{
					if(board[row + chooseShape.getY()][col + chooseShape.getX()] != null)
					{
						state = STATE_GAME_OVER;
					}
				}
			}
		}
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		chooseShape.renderShape(g);
		
		
		for(int row = 0; row < board.length; row++)
		{
			for( int col = 0; col < board[row].length; col++)
			{
				if(board[row][col] != null)
				{
					g.setColor(board[row][col]);
					g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
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
		
		if(state == STATE_GAME_OVER)
		{
			
		g.setColor(Color.WHITE);
		g.drawString("GAMER OVER", 325, 100);
		
		}
		
		if(state == STATE_GAME_PAUSE)
		{
			
		g.setColor(Color.WHITE);
		g.drawString("PAUSE", 325, 100);
		
		}
	}
	
	public Color[][] getBoard(){
		return board;
	}
//Controls
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			chooseShape.speedUp();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			chooseShape.moveRight();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			chooseShape.moveLeft();
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			chooseShape.rotateShape();
		}
		
		if(state == STATE_GAME_OVER)
		{
			if(e.getKeyCode() == KeyEvent.VK_R)
			{
				for(int row = 0; row < board.length; row++)
				{
					for( int col = 0; col < board[row].length; col++)
					{
						board[row][col] = null;
					}
				}
				setChooseShape();
				state = STATE_GAME_PLAY;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P)
		{
			if(state == STATE_GAME_PLAY)
			{
				
				state = STATE_GAME_PAUSE;
			}
			else if(state == STATE_GAME_PAUSE)
			{
				state = STATE_GAME_PLAY;
			}
		
			} 	
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				chooseShape.speedDown();
			}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

