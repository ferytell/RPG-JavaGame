package object;

import entity.Entity;
import main.GamePanel;

public class objBoot extends Entity {
	
	public objBoot(GamePanel gp) {
		super(gp);
		name = "boot";
		down1 = setup("/objects/boots");
	}
	

}
