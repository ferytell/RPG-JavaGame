package object;

import entity.Entity;
import main.GamePanel;

public class objShieldWood extends Entity{

	public objShieldWood(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name =  "wood shield";
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue =1;
		description = "[" + name + "]\n An old shield from the fucking\n shrine.";
		price = 7;
	}
	

}
