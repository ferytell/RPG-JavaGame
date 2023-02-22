package object;

import entity.Entity;
import main.GamePanel;

public class objSwordNormal extends Entity{
	public objSwordNormal (GamePanel gp) {
		super(gp);
		name = "Normal Sword";
		
		type = type_sword;
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\n An old sword found from \n trash.";
		price = 8;
		
	}
}
