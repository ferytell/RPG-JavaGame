package object;

import entity.Entity;
import main.GamePanel;

public class objChest extends Entity {
	
	public objChest(GamePanel gp) {
		super(gp);
		
		name = "chest";
		down1 = setup("/objects/Chest_1.png");
		
		collision = true;
	}
}
