
package object;

import entity.Entity;
import entity.Projectiles;
import main.GamePanel;

public class objRock extends Projectiles {
	GamePanel gp;

	public objRock(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		name = "Rock";
		speed = 4;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		getImage();
		
		
	}
	
	public void getImage() {
		up1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);

	}
	
	public boolean haveResources(Entity user) {
		boolean haveResources = false;
		if (user.ammo >= useCost) {
			haveResources = true;
		}
		return haveResources;
	}
	
	public void subtractResource(Entity user) {
		user.ammo -= useCost;
		
	}


}
