package object;

import entity.Entity;
import main.GamePanel;

public class objAxe extends Entity{

	public objAxe(GamePanel gp) {
		super(gp);
		
		type = type_axe;
		name = "woodcutter Axe";
		down1 =  setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name + "]\n little bit rusty \n but still can cut trees.";
		price = 20;
	}

}
