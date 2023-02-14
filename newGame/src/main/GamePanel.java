package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.objCoin;
import tile.TileManager;
import tile_interactive.tile_interactive;

public class GamePanel extends JPanel implements Runnable{
	
								
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Screen settings
	final int orginalTilesSizes = 16; 		// 16x16 pixel
	final int scale = 3;
	
	public int tileSize  = orginalTilesSizes * scale;
	public int maxScreenCol = 20;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol; 		// 768 pixel
	public int screenHeight = tileSize * maxScreenRow;		// 576 pixel
	
	// World setting
	
	public final int maxWorldCol = 250;
	public final int maxWorldRow = 250;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// FOR FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	
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
	Config config = new Config(this);
	Thread gameThread;
	
	
	
	
	// ENTITY AND OBJECT >>>>>>>>>
	
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[30]; 				// number of obj that can be displayed
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[30];
	public tile_interactive iTile[] = new tile_interactive[50];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
	// >>>>>>>>>>>>> GAME STATE <<<<<<<<<<<<<<<<<<<<
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	

	
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
		aSetter.setInteractiveTiles();
		
	//	playMusic(7);
	//	stopMusic();
		gameState = titleState;
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		
		if (fullScreenOn == true) {
			setFullScreen();
		}
		
	}	
	
	public void setFullScreen() {
		// GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		// GET FULL SCREEN WIDTH AND HEIGHT
		
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
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
				drawToTempScreen(); 		// draw Everything to the buffered image
				drawToScreen();				// draw the Buffered Image to the screen
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
					if (monster[i].alive == true && monster[i].dying ==  false ) {
						monster[i].update();
					}
					if (monster[i].alive == false) {
						monster[i].checkDrop();
						monster[i] = null;
					}
				}
			}
			// <<<<<<<<<<<<<<<PROJECTILE UPDATE  >>>>>>>>>>>>>>>>
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if (projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if (projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}
			// <<<<<<<<<<<<<<<< PARTICLE UPDATE  >>>>>>>>>>>>>>>>
			for(int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					if (particleList.get(i).alive == true) {
						particleList.get(i).update();
					}
					if (particleList.get(i).alive == false) {
						particleList.remove(i);
					}			
				}
			}
			
			// <<<<<<<<<<<<<<<< INTERACTIVE TILES UPDATE  >>>>>>>>>>>>>>>>
			for (int i = 0; i < iTile.length; i++) {
				if (iTile[i] != null) {
					iTile[i].update();
				}
			}
		}
		
		if(gameState == pauseState) {
			// nothing
		}
		
//		objCoin.update();
	}

	public void drawToTempScreen() {
		// <<<<<<<<<<<<<<<< TITLE SCREEN  >>>>>>>>>>>>>>>>
		
		if (gameState == titleState) {
			ui.draw(g2);
		}
		else {
			// <<<<<<<<<<<<<<<< Tile draw  >>>>>>>>>>>>>>>>
			tileMan.draw(g2);
			// <<<<<<<<<<<<<<<< Tile Interactive  >>>>>>>>>>>>>>>>
			for (int i = 0; i < iTile.length; i++) {
				if (iTile[i] != null) {
					iTile[i].draw(g2);
				}
			}
			
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
			
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			
			for (int i = 0; i < particleList.size(); i++) {
				if (particleList.get(i) != null) {
					entityList.add(particleList.get(i));
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
		}
	}

	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
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
	
	
	
	
	
