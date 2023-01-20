package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import object.objCoin;
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
	
	// STSTEM >>>
	
	TileManager tileMan = new TileManager(this);
	keyHandler keyH = new keyHandler();
	Sounds music = new Sounds();
	Sounds soundEffect = new Sounds();
	public collisionChecker cChecker = new collisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this); 
	public UI ui = new UI(this);
	Thread gameThread;
	
	
	
	
	// ENTITY AND OBJECT >>>>>>>>>
	
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10]; 				// number of obj that can be displayed
	

	
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject(); 
		playMusic(7);
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
//		objCoin.update();
	}

		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// Tile draw
		
		tileMan.draw(g2);
		
		// object draw
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// Player draw
		player.draw(g2);
		
		// UI
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		
		soundEffect.setFile(i);
		soundEffect.play();
	}
	
	
	
	
}
	
	
	
	
	
