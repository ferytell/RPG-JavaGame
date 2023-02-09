package Monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.objCoinBronze;
import object.objHeart;
import object.objMana;
import object.objRock;

public class monGreenSlime extends Entity{
	GamePanel gp;

	public monGreenSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		name = "Green Slime";
		speed = 1;
		maxLife = 5;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new objRock(gp);
				
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
		
		int i = new Random().nextInt(100)+1;
		if (i > 99 && projectile.alive == false && shootAvailableCounter == 30) {
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shootAvailableCounter = 0;
			
			
		}
	}
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	
	public void checkDrop()  {
		
		// cast probability
		int i = new Random().nextInt(101);
		
		// set the probability
		
		if (i < 60) {
			dropItem(new objCoinBronze(gp));
		}
		if (i >= 60 && i <= 85) {
			dropItem(new objHeart(gp));
		}
		if (i >= 85 && i <= 100) {
			dropItem(new objMana(gp));
		}
		
	}
	
	

}
