package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Screen settings
	final int orginalTilesSizes = 16; 		// 16x16 pixel
	final int scale = 3;
	
	public int tileSize  = orginalTilesSizes * scale;
	public int maxScreenCol = 16;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol; 		// 768 pixel
	public int screenHeight = tileSize * maxScreenRow;		// 576 pixel
	
	// World setting
	
	public final int maxWorldCol = 70;
	public final int maxWorldRow = 70;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	
	
	// FPS setting
	int FPS = 60;
	
	TileManager tileMan = new TileManager(this);
	keyHandler keyH = new keyHandler();
	Thread gameThread;
	public collisionChecker cChecker = new collisionChecker(this);
	public Player player = new Player(this, keyH);
	
	
	

	
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
			
		
		
	}
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += ( currentTime - lastTime ) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 0) {
				update();
				repaint();
				delta-- ;
			}
//			System.out.println("goooooo");
			// Update : Update information, kile char position
			//update();
			
			// Draw the screen with the updated information
			//repaint();
		}
		
	}
	
	
	public void update() {
		
		player.update();
		


	}

		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileMan.draw(g2);
		player.draw(g2);
		
		
		g2.dispose();
	}

	
	
	
	
}
	
	
	
	
	
