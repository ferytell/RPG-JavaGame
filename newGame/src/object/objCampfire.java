package object;

import entity.Entity;
import main.GamePanel;

public class objCampfire extends Entity{
	
	GamePanel gp;
	public objCampfire(GamePanel gp) {
		super(gp);
		name = "campfire";
		down1 = setup("/objects/campfire.gif");
	}
}
