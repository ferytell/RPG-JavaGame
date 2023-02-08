 package entity;

import main.GamePanel;

public class Projectiles extends Entity{
	
	Entity user;

	public Projectiles(GamePanel gp) {
		super(gp);
		
		
		
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
	public void update() {
		
		// Collision check, if player that shoot check collision on monster and vice versa
		if ( user == gp.player ) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if (monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack);
				alive = false;
			}
		}
		
		if ( user != gp.player ) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if (gp.player.invincible == false && contactPlayer == true) {
				damagePlayer(attack);
				alive = false;
				System.out.println("test");
			}
		}
		
		switch (direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		life --;
		if (life < 0) {
			alive = false;
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public boolean haveResources(Entity user) {
		boolean haveResources = false;
		return haveResources;
	}
	public void subtractResource(Entity user) {
	}

}
