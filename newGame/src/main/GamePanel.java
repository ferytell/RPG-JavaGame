package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
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
	
	public final int maxWorldCol = 250;
	public final int maxWorldRow = 250;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	
	
	// FPS setting
	
	int FPS = 60;
	
	// STSTEM >>>
	
	TileManager tileMan = new TileManager(this);
	public keyHandler keyH = new keyHandler(this);
	Sounds music = new Sounds();
	Sounds soundEffect = new Sounds();
	public collisionChecker cChecker = new collisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this); 
	public UI ui = new UI(this);
	public eventHandler eHandler = new eventHandler(this);
	Thread gameThread;
	
	
	
	
	// ENTITY AND OBJECT >>>>>>>>>
	
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10]; 				// number of obj that can be displayed
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[30];
	
	ArrayList<Entity> entityList = new ArrayList<>();
	
	// >>>>>>>>>>>>> GAME STATE <<<<<<<<<<<<<<<<<<<<
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	

	
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject(); 
		aSetter.setNPC();
		aSetter.setMonster();
	//	playMusic(7);
	//	stopMusic();
		gameState = titleState;
		

		
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
		
		if(gameState == playState) {
			
			// <<<<<<<<<<<<<<<< PLAYER UPDATE  >>>>>>>>>>>>>>>>
			
			player.update();
			
			// <<<<<<<<<<<<<<<< NPC UPDATE  >>>>>>>>>>>>>>>>
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			// <<<<<<<<<<<<<<<< MONSTER UPDATE  >>>>>>>>>>>>>>>>
			
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					monster[i].update();
				}
			}
			
		}
		
		if(gameState == pauseState) {
			// nothing
		}
		
//		objCoin.update();
	}

		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// <<<<<<<<<<<<<<<< TITLE SCREEN  >>>>>>>>>>>>>>>>
		
		if (gameState == titleState) {
			ui.draw(g2);
		}
		else {
			// <<<<<<<<<<<<<<<< Tile draw  >>>>>>>>>>>>>>>>
			tileMan.draw(g2);
			
			// >>>>>>>>>>>>>>>>>>> add Entity to List <<<<<<<<<<<<<<<<<<<<	
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			// <<<<<<<<<<<<<<<<<<< Sorting List >>>>>>>>>>>>>>>>>>>>
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					
					 int result = Integer.compare(e1.worldY, e2.worldY);
					
					return result;
				}
				
			});
			// <<<<<<<<<<<<<<<<<<< Draw Entities >>>>>>>>>>>>>>>>>>>>
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			// <<<<<<<<<<<<<<<<<<< Delete Entities List >>>>>>>>>>>>>>>>>>>>
			entityList.clear();
			
			
			// <<<<<<<<<<<<<<<<<<< UI >>>>>>>>>>>>>>>>>>>>
			ui.draw(g2);
			
			g2.dispose();
		
			
		}
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
	
	
	
	
	
