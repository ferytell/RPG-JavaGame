package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	
	// Screen settings
	final int orginalTilesSizes = 16; 		// 16x16 pixel
	final int scale = 3;
	
	final int tileSize  = orginalTilesSizes * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; 		// 768 pixel
	final int screenHeight = tileSize * maxScreenRow;		// 576 pixel
	
	
	// FPS setting
	int FPS = 60;

	keyHandler keyH = new keyHandler();
		
	Thread gameThread;
	
	// set player default position
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	
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
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		
		if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		
		if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		
		if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}


	}

		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.blue);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}

	
	
	
	
}
	
	
	
	
	
