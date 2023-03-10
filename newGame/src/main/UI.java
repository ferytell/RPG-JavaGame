package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entity.Entity;
import object.objCoinBronze;
import object.objHeart;
import object.objKey;
import object.objMana;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaBold, papyrus;
	BufferedImage heart_full, heart_half, heart_blank, mana_full, mana_blank, coin;

	public boolean messageOn = false;
	public boolean gameFinnished = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0;   // 0: about
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int subState = 0;
	int counter = 0;
	public Entity npc;
	
//	double playTime;
	
//	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		

		try {
			InputStream is = getClass().getResourceAsStream("/font/Monika_Bold.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa_Bold.ttf");
			purisaBold = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/PAPYRUS.TTF");
			papyrus = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create HUD Object
		
		Entity heart =  new objHeart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new objMana(gp);
		mana_full = crystal.image;
		mana_blank = crystal.image2;
		Entity bronzeCoin =  new objCoinBronze(gp);
		coin = bronzeCoin.down1;
		
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(papyrus);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		
		// >>>>>>>>>>>>>>>>>>>  TITLE STATE  <<<<<<<<<<<<<<<<<<<<<<<
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
			//System.out.println(gp.gameState);
		}
		
		
		// >>>>>>>>>>>>  PLAY STATE  <<<<<<<<<<<<<<<<<<<<<<<
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
			//System.out.println(gp.gameState);
		}
		
		// >>>>>>>>><<<<<<<<<>>>  PAUSE STATE  <<<<<<<<<<<<<<<<<<<<<<<
		
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
			//System.out.println(gp.gameState);
		}
		
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>  DiALOGUE STATE  <<<<<<<<<<<<<<<<<<<<<<<
		 if (gp.gameState == gp.dialogueState) {
			 drawPlayerLife();
			 drawDialogueScreen();
		 }
		 
		// >>>>>>>>>>>>>>>>>>  CHARACTER STATE  <<<<<<<<<<<<<<<<<<<<<<<
		 if (gp.gameState == gp.characterState) {		 
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		 
		// >>>>>>>>>>>>>>>>>>  OPTION STATE  <<<<<<<<<<<<<<<<<<<<<<<
		if (gp.gameState == gp.optionState) {		 
			drawOptionScreen();		
		}
		
		// >>>>>>>>>>>>>>>>>>  GAME OVER STATE  <<<<<<<<<<<<<<<<<<<<<<<
		if (gp.gameState == gp.gameOverState) {		 
			drawGameOverScreen();		
		}
		
		// >>>>>>>>>>>>>>>>>>  TRANSITION STATE  <<<<<<<<<<<<<<<<<<<<<<<
		if (gp.gameState == gp.transitionState) {		 
			drawTransition();		
		}
		
		// >>>>>>>>>>>>>>>>>>  TRADE STATE  <<<<<<<<<<<<<<<<<<<<<<<
		if (gp.gameState == gp.tradeState) {		 
			drawTradeScreen();		
		}
	}
	
	public void drawPlayerLife() { // AND MANA
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// >>>>>>>>>>>>  DRAW MAX LIFE  <<<<<<<<<<<<<<<<<
		while (i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// >>>>>>>>>>>>  RESET  <<<<<<<<<<<<<<<<<
		
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
				
		// >>>>>>>>>>>>  DRAW CURRENT LIFE  <<<<<<<<<<<<<<<<<
		
		while (i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		// >>>>>>>>>>>>  DRAW MAX MANA  <<<<<<<<<<<<<<<<<
		
		x = gp.tileSize/2 - 5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while (i < gp.player.maxMana) {
			g2.drawImage(mana_blank, x, y, null);
			i++;
			x += 35 ;
		}
		
		// >>>>>>>>>>>>  DRAW MANA  <<<<<<<<<<<<<<<<<
		
		x = gp.tileSize/2 - 5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while (i < gp.player.mana) {
			g2.drawImage(mana_full, x, y, null);
			i++;
			x += 35;
		}
	}
	
	public void drawMessage() {
		 int messageX = gp.tileSize;
		 int messageY = gp.tileSize * 4;
		 g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		 
		 for (int i = 0; i < message.size();i++) {
			  if (message.get(i) != null) {
				  g2.setColor(Color.RED);
				  g2.drawString(message.get(i), messageX, messageY);
				  	
				  int counter =  messageCounter.get(i) + 1;
				  messageCounter.set(i, counter);
				  messageY += 50;
				  
				  if (messageCounter.get(i) > 100) {
					  message.remove(i);
					  messageCounter.remove(i); 
				  }
			  }
		 }
	};
	public void drawTitleScreen() {
		
		if (titleScreenState == 0) {

			g2.setColor(new Color(2, 12, 8));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25f));
			String text = "THE FOOL'S : REGRETS";
			int x = getXforCenterText(text);
			int y = gp.tileSize * 2;
			
			// SHADOW
			g2.setColor(Color.GRAY);
			g2.drawString(text, x+5, y+5);
			// MAIN FONT COLOR
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y);
			
			// MAIN IMAGE
			x =	gp.screenWidth/2 - (gp.tileSize * 2) / 2;
			y += gp.tileSize * 1;
			g2.drawImage(gp.player.poke, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			// :>>>>>>>>>>>>>>>>>>>>>>>>>>>> MAIN MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15f));
			
			text = "NEW GAME";
			x = getXforCenterText(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			//	g2.setColor(Color.RED);
			//	g2.drawString(text, x+2, y+2);
			}
			
			text = "LOAD GAME";
			x = getXforCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			//	g2.setColor(Color.RED);
			//	g2.drawString(text, x+2, y+2);
			}
			
			text = "OPTIONS";
			x = getXforCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			//	g2.setColor(Color.RED);
			//	g2.drawString(text, x+2, y+2);
			}
			
			text = "CREDITS";
			x = getXforCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			//	g2.setColor(Color.RED);
			//	g2.drawString(text, x+2, y+2);
			}
			
			text = "QUIT";
			x = getXforCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 4) {
				g2.drawString(">", x - gp.tileSize, y);
			//	g2.setColor(Color.RED);
			//	g2.drawString(text, x+2, y+2);
			}
		
		}
		else if (titleScreenState == 1) {
			g2.setColor(new Color(2, 12, 8));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			// CLASS SELECTION SCREEN
			
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String text = "Fool Select Your Class !!";
			int x = getXforCenterText(text);
			int y = gp.tileSize * 3;
			g2.drawString(text, x, y);
			
			g2.setFont(g2.getFont().deriveFont(25F));
			text = "Fighter";
			x = getXforCenterText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Assasins";
			x = getXforCenterText(text);
			y += gp.tileSize * 1;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "Sorcerer";
			x = getXforCenterText(text);
			y += gp.tileSize * 1;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			
			g2.setFont(g2.getFont().deriveFont(20F));
			text = "Back";
			x = getXforCenterText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);
			if (commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			
					
		}
		
		else if (titleScreenState == 3) {
			g2.setColor(new Color(2, 12, 8));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String text = "The Fools's: Regrets";
			int x = getXforCenterText(text);
			int y = gp.tileSize * 3;
			g2.drawString(text, x, y);
			
			
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(20F));
			text = "Version : 0.1.1"; x = getXforCenterText(text); y += gp.tileSize * 1; g2.drawString(text, x, y);
			text = "Programing : Ferytell"; x = getXforCenterText(text); y += gp.tileSize * 1; g2.drawString(text, x, y);
			text = "Design : Ferytell"; x = getXforCenterText(text); y += gp.tileSize * 1; g2.drawString(text, x, y);
			text = "Graphic : Ferytell"; x = getXforCenterText(text); y += gp.tileSize * 1; g2.drawString(text, x, y);
			text = "Music : Ferytell"; x = getXforCenterText(text); y += gp.tileSize * 1; g2.drawString(text, x, y);

		}
		
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20f));
		String text = "PAUSE";
		int x = getXforCenterText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		// Window screen
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 6);
		int height = gp.screenHeight - (gp.tileSize * 7);
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for (String line: currentDialogue.split("/n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	
	public void drawCharacterScreen() {
		// CREATE FRAME
		final int frameX = gp.tileSize * 2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int textX = frameX + 20;
		int textY =  frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		
		g2.drawString("Level", textX, textY); textY += lineHeight;
		g2.drawString("Life", textX, textY); textY += lineHeight;
		g2.drawString("Mana", textX, textY); textY += lineHeight;
		g2.drawString("Strength", textX, textY); textY += lineHeight;
		g2.drawString("Dexterity", textX, textY); textY += lineHeight;
		g2.drawString("Attack", textX, textY); textY += lineHeight;
		g2.drawString("Defense", textX, textY); textY += lineHeight;
		g2.drawString("Exp", textX, textY);	textY += lineHeight;
		g2.drawString("Next Lv", textX, textY);	textY += lineHeight;
		g2.drawString("Coin", textX, textY); textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY); textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY); textY += lineHeight;
		
		// VALUES
		
		int tailX = (frameX + frameWidth) - 30;
		
		// RESET textY
		
		textY =  frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight - 15;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-10, null);
		textY += lineHeight + 15;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-10, null);
		
	}
	public void drawInventory(Entity entity, boolean cursor) {

		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;

		if (entity == gp.player) {
			frameX = gp.tileSize * 12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize * 2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		
		// Frame
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		 
		// Slot
		
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize + 4;
		
		// Draw Player Items
		
		for (int i = 0; i < entity.inventory.size(); i++) {
			
			// Equip Player's items
			
			if (entity.inventory.get(i) == entity.currentWeapon || 
					gp.player.inventory.get(i) == entity.currentShield) {
				g2.setColor(new Color(240, 190, 2));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			slotX += slotSize;
			
			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXStart;
				slotY += slotSize;
			}
		}
		
		// Cursor
		if (cursor == true) {
			int cursorX = slotXStart + (slotSize * slotCol);
			int cursorY = slotYStart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			// Draw Cursor
			
			g2.setColor(Color.cyan);
			g2.setStroke(new BasicStroke(3)) 	;
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			// Description Frame
			
			int dFrameX = frameX;
			int dFrameY =  frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize * 3;
			
			
			// Draw Description text
			
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(20F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			if (itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				for (String line: entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					
					textY += 32;
			
				}
			}

		}
		
	}
	
	public void drawTransition() {
		counter ++;
		
		g2.setColor(new Color(0,0,0, counter*5));
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
		
		if (counter == 50) {
			counter =0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
			
		}
	}
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(60,0,0,200));
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
		
		text = "GAME OVER";
		// SHADOW
		g2.setColor(Color.BLACK);
		x = getXforCenterText(text);
		y = gp.tileSize * 6;
		g2.drawString(text, x, y);
		
		//MAIN
		g2.setColor(Color.WHITE);
		g2.drawString(text, x-4, y-4);
		
		// RETRY
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		text = "Retry";
		x = getXforCenterText(text)/2;
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">>              <<", x - 40, y);
		}
		
		// BACK TO MAIN MENU
		text = "Quit";
		x = (getXforCenterText(text)/2) + getXforCenterText(text);
		//y += 55;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">>              <<", x - 40, y);
		}
		 
	}
	
	public void drawOptionScreen() {
		
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(30F));
		
		// SUB WINDOW
		
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 9;
		int frameHeight = gp.tileSize * 10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch (subState) {
		case 0: option_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY);break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConfirmation(frameX, frameY); break;
		
		}
		
		gp.keyH.enterPressed = false;
	}
	
	public void option_top (int frameX, int frameY) {
		int textX;
		int textY;
		
		// TITTLE
		
		String text = "Options";
		textX = getXforCenterText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// FULL SCREEN ON/OFF
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.drawString("Full Screen", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				if (gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if (gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
				
			}
		}
		// MUSIC
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		// SE
		textY += gp.tileSize;
		g2.drawString("Sound Effect", textX, textY);
		if (commandNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		// CONTROL
		textY += gp.tileSize;
		g2.drawString("Contol", textX, textY);
		if (commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		// END GAME
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if (commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			
			if (gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		// BACK
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if (commandNum == 5) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
			
		} 
		
		// FULL SCREEN CHECK BOX
		textX = frameX + (int) (gp.tileSize * 5.5) ;
		textY = frameY + gp.tileSize * 2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if (gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		// MUSIC VOLUME
		textY += gp.tileSize ;
		g2.drawRect(textX, textY, 120, 24); 			// 120/5 = 24 (we divide volume into 5 level)
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// SE VOLUME
		textY += gp.tileSize ;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.soundEffect.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();

	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "The change will take effect/n after restarting the gem";
		
		for (String line: currentDialogue.split("/n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// BACK
		textY += frameY +(int) (gp.tileSize*3.5);
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	
	public void options_control(int frameX, int frameY) {
		g2.setFont(g2.getFont().deriveFont(25F));
		int textX;
		int textY;
		
		// title
		String text = "Control";
		textX = getXforCenterText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY += gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
		g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
		g2.drawString("Pause", textX, textY); textY += gp.tileSize;
		g2.drawString("Options", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*6;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("F", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;
		
		// BACK
		
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
		 		commandNum = 3;
			}
		}
	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "Quit the fucking Game and\n return to title screen?";
		for (String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YESSS
		String text = "Yes";
		textX = getXforCenterText(text);
		textY += gp.tileSize * 3;
		g2.drawString(text, textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if (gp.keyH.enterPressed == true) {
				gp.stopMusic();
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}		
		// NOOO
		text = "No";
		textX = getXforCenterText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX-30, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
	}
	
	public void drawTradeScreen() {
		switch(subState) {
		case 0 : tradeSelect(); break;
		case 1 : tradeBuy(); break;
		case 2 : tradeSell(); break;
 		}
		
		gp.keyH.enterPressed = false;
	}
	
	public void tradeSelect() {
		
		drawDialogueScreen();
		
		// DRAW WINDOW
		int x = gp.tileSize * 14;
		int y = (gp.tileSize * 5) + 15;
		int width = gp.tileSize * 4;
		int height = (int) (gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		// DRAW TEXT
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if ( commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if (gp.keyH.enterPressed == true) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if ( commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if (gp.keyH.enterPressed == true) {
				subState = 2;
			}

		}
		y += gp.tileSize;

		g2.drawString("Leave", x, y);
		if ( commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if (gp.keyH.enterPressed == true) {
				commandNum = 0;
				gp.gameState  = gp.dialogueState;
				currentDialogue = "Come Again, Huehehehe!";
			}

		}

	}
	
	public void tradeBuy() {
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, false);
		
		// DRAW NPC INVENTORY
		drawInventory(npc, true);
		
		// DRAW HINT WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 9;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[Esc] Back", x+24, y+60);
		
		// DRAW PLAYER COIN WINDOW
		x = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);
		
		// DRAW PRICE WINDOW 
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if (itemIndex < npc.inventory.size()) {
			
			x = (int) (gp.tileSize * 5.5);
			y = (int) (gp.tileSize * 5.5);
			width = (int) (gp.tileSize * 2.5);
			height = (int) (gp.tileSize);
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+6, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 8-20);
			g2.drawString(text, x, y + 30);
			
			// BUY AN ITEM
			if (gp.keyH.enterPressed == true) {
				if (npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You need more coin to but they!";
					drawDialogueScreen();
				}
				else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You Cannot carry anymore than that!";
				}
				else {
					gp.player.coin -= npc.inventory.get(itemIndex).price;
					gp.player.inventory.add(npc.inventory.get(itemIndex));
				}
			}
		}
		
		
	}
 	
	public void tradeSell() {
		
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int width;
		int height;
		
		// DRAW HINT WINDOW
		x = gp.tileSize * 2;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[Esc] Back", x+24, y+60);
		
		// DRAW PLAYER COIN WINDOW
		x = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);
		
		// DRAW PRICE WINDOW 
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if (itemIndex < gp.player.inventory.size()) {
			
			x = (int) (gp.tileSize * 15.5);
			y = (int) (gp.tileSize * 5.5);
			width = (int) (gp.tileSize * 2.5);
			height = (int) (gp.tileSize);
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x+10, y+6, 32, 32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 18-20);
			g2.drawString(text, x, y + 30);
			
			// SELL AN ITEM
			if (gp.keyH.enterPressed == true) {
				
				if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You cannot sell equipped Items!";
				}
				else {
					gp.player.inventory.remove(itemIndex);
					gp.player.coin += price;
				}

			}
		}
				
		
	}
	
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(12, 12, 12, 230); // >>>>>>>>>> RGB
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width - 10, height - 10, 25, 25);
		
	}
	public int getXforCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
		
	}
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
		
	}
}
