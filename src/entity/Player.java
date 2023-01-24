package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTools;
import main.keyHandler;

public class Player extends Entity{
	

	keyHandler keyH;
	
	public final int screenX;
	public final int screenY;
			
	public Player(GamePanel gp, keyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();							// Here we decide the size of hitbox char
		solidArea.x= 10;
		solidArea.y = 18;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		solidArea.width = 28;
		solidArea.height = 28;
				
		
	//	System.out.print(solidArea.width);
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 10;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		poke = setup("/NPC/poke");
	}
	
	
	
	public void update() {
		if(keyH.upPressed == true || keyH.rightPressed == true ||
				keyH.downPressed == true || keyH.leftPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";	
			}
			if(keyH.downPressed == true) {
				direction = "down";
			}
			if(keyH.leftPressed == true) {
				direction = "left";
			}
			if(keyH.rightPressed == true) {
				direction = "right";
			}
			
		// >>>>>>>>>>>>>>>  Check tile collision  <<<<<<<<<<<<<<<<<<<<<<
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
		// >>>>>>>>>>>>>>>>>>>  Check object collision <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
		// >>>>>>>>>>>>>>>>>>> Check NPC Collision <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			
			
		// If collision is false, player can move  
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}

			spriteCounter++;
			if(spriteCounter > 15) {			// FPS is 60, this will tell how fast animation change (bigger = slower)
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	
	public void pickUpObject(int i) {
		
		if(i != 999) {

		} 
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			//System.out.println("NPC!!");
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false; 
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.blue);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}		
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			
			
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
		g2.setColor(Color.red);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
	}

}
