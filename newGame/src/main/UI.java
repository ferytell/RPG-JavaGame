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

import entity.Entity;
import object.objHeart;
import object.objKey;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaBold, papyrus;
	BufferedImage heart_full, heart_half, heart_blank;

	public boolean messageOn = false;
	public boolean gameFinnished = false;
	public String message = "";
	int messageCounter = 0;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0;   // 0: about
	
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
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
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
			//System.out.println(gp.gameState);
		}
		
		// >>>>>>>>>>>>  PAUSE STATE  <<<<<<<<<<<<<<<<<<<<<<<
		
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
			drawPlayerLife();
			//System.out.println(gp.gameState);
		}
		
		// >>>>>>>>>>>>  DiALOGUE STATE  <<<<<<<<<<<<<<<<<<<<<<<
		 if (gp.gameState == gp.dialogueState) {
			 drawPlayerLife();
			 drawDialogueScreen();
			 
		 }
	}
	
	public void drawPlayerLife() {
		
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
		
		
		
	}
	
	
	
	
	public void drawTitleScreen() {
		
		if (titleScreenState == 0) {

			g2.setColor(new Color(2, 12, 8));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25f));
			String text = "THE  LEGEND  OF  TOKENENK ";
			int x = getXforCenterText(text);
			int y = gp.tileSize * 3;
			
			// SHADOW
			g2.setColor(Color.GRAY);
			g2.drawString(text, x+5, y+5);
			// MAIN FONT COLOR
			g2.setColor(Color.WHITE);
			g2.drawString(text, x, y);
			
			// MAIN IMAGE
			x =	gp.screenWidth/2 - (gp.tileSize * 2) / 2;
			y += gp.tileSize * 2;
			g2.drawImage(gp.player.poke, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			// MAIN MENU
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
			
			text = "QUIT";
			x = getXforCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			//	g2.setColor(Color.RED);
			//	g2.drawString(text, x+2, y+2);
			}
		
		}
		else if (titleScreenState == 1) {
			// CLASS SELECTION SCREEN
			
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String text = "Select Your Class !!";
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
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
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
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(12, 12, 12, 100); // >>>>>>>>>> RGB
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
	
}
