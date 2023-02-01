package Monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class monGreenSlime extends Entity{
	GamePanel gp;

	public monGreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = 2;
		name = "Green Slime";
		speed = 1;
		maxLife = 5;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
				
		solidArea.x = 3;
		solidArea.y = 10;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
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
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	
	

}
