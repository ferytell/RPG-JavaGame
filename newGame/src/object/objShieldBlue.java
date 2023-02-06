package object;

import entity.Entity;
import main.GamePanel;

public class objShieldBlue extends Entity{

	public objShieldBlue(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name =  "Blue Shield";
		down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
		defenseValue =1;
		description = "[" + name + "]\n An shield stolen from \n old man.";
	}
	

}
