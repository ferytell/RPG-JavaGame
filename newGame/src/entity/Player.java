package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTools;
import main.keyHandler;
import object.objShieldWood;
import object.objSwordNormal;

public class Player extends Entity{
	

	keyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	
			
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
		
		attackArea.width = 36;
		attackArea.height = 36;
				
		
	//	System.out.print(solidArea.width);
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 10;
		speed = 4;
		direction = "down";
		
		// PLAYER STATS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new objSwordNormal(gp);
		currentShield = new objShieldWood(gp);
		attack = getAttack();
		defense = getDefense();
	}
	
	public int getAttack() {
		return attack = strength * currentWeapon.attackValue;
	}
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	public void getPlayerImage() {
		
		
		up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
		poke = setup("/NPC/poke", gp.tileSize, gp.tileSize); // picture for start menu, we will change it later
	}
	
	public void getPlayerAttackImage() {
		attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
	}
	
	public void update() {
		
		if (attacking == true) {
			attacking();
		}
		if(keyH.upPressed == true || keyH.rightPressed == true ||
				keyH.downPressed == true || keyH.leftPressed == true || keyH.enterPressed == true) {
			
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
		
		// >>>>>>>>>>>>>>>>>>> Check Monster Collision <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
		
			
		// >>>>>>>>>>>>>>>>>>> Check Event <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			gp.eHandler.checkEvent();
			
			
		// If collision is false, player can move  
			if(collisionOn == false && keyH.enterPressed == false) {
				switch(direction) {
				case "up":
					worldY -= speed; break;
				case "down":
					worldY += speed; break;
				case "left":
					worldX -= speed; break;
				case "right":
					worldX += speed; break;
				}
			}
			

			if (keyH.enterPressed == true && attackCanceled == false) {
				gp.playSE(9);
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;
			
			gp.keyH.enterPressed = false; 
			
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
		
		if (invincible == true) {
			invincibleCounter ++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void attacking() {
		// the first 5 frame animation will use picture 1, then for next 25 frame use picture 2, lastly back using picture 1
		spriteCounter ++;
		
		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		if (spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			// save current worldX, worldY, solidArea on temporary variable
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			
			switch(direction) {
			case "up" : worldY -= attackArea.height; break;
			case "down" : worldY += attackArea.height; break;
			case "left" : worldX -= attackArea.width; break;
			case "right" : worldX += attackArea.width; break;
			}
			
			// attackArea become solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			// check monsterCollison with the updated worldX, worldY, and solidArea
			int monsterIndex  = gp.cChecker.checkEntity(this, gp.monster);
			
			damageMonster(monsterIndex);
			
			// after check collision, restore original data
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth; 
			solidArea.height = solidAreaHeight;
		}
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {

		} 
	}
	
	public void interactNPC(int i) {
		
		if (gp.keyH.enterPressed == true) {
						
			if(i != 999) {
				//System.out.println("NPC!!");
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
				}   
		}
		
		
		
	}
	public void contactMonster(int i) {
		
		if (i != 999) {
			if (invincible == false) {
				gp.playSE(10);
				life -= 1;
				invincible = true;
			}
			
			
		}
	}
	
	
	public void damageMonster(int i) {
		if (i != 999) {
			
			if (gp.monster[i].invincible == false) {
				gp.playSE(8);			
				gp.monster[i].life -= 1;
				gp.monster[i].invincible =true;
				gp.monster[i].damageReaction();
				
				if (gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.blue);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		
		switch(direction) {
		
		case "up":
			if (attacking == false) {
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}	
			}
			else if (attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if (spriteNum == 1) {image = attackUp1;}
				if (spriteNum == 2) {image = attackUp2;}
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) {image = down1;}
				if (spriteNum == 2) {image = down2;}		
			}
			else if (attacking == true) {
				if (spriteNum == 1) {image = attackDown1;}
				if (spriteNum == 2) {image = attackDown2;}
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}				
			}
			else if (attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if (spriteNum == 1) {image = attackLeft1;}
				if (spriteNum == 2) {image = attackLeft2;}
			}
			break;
		case "right":
			if (attacking == false) {
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}				
			}
			else if (attacking == true) {
				if (spriteNum == 1) {image = attackRight1;}
				if (spriteNum == 2) {image = attackRight2;}
			}
			break;
		}
		
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);

		// to draw HITBOXES we will delete it later
		g2.setColor(Color.red);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		
		
		// reset opacity
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
		// DEBUGGING
		
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.WHITE);
//		g2.drawString("invincible: " + invincibleCounter, 10, 400);
	}

}
