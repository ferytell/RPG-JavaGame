package entity;

import java.util.Random;



import main.GamePanel;


public class NPC_OldMan extends Entity{
	 
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
		setDialogue();
		
		
	}
//	public BufferedImage poke;
	public void getImage() {
		
		
		up1 = setup("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
		right1 = setup("/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
		left1 = setup("/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/NPC/oldman_left_2", gp.tileSize, gp.tileSize);
		down1 = setup("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
		
	}
	public void setDialogue() {
		
		dialogue[0] = " Hello, young man!";
		dialogue[1] = " Do You know that you /n can swim on lava, /n but only once?";
		dialogue[2] = " I wish I get them Maiden..";
		dialogue[3] = " You look like a shit!";
	}
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 120) {				// NPC change direction every 2 seconds

			Random random = new Random();
			int i = random.nextInt(100)+1;			// pick a random num from 0 - 100
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}
	
	public void speak() {
		super.speak();
	}
	


}
