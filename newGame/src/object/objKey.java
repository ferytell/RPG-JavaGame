package object;

import entity.Entity;
import main.GamePanel;

public class objKey extends Entity{
	
	
	public objKey(GamePanel gp) {
		super(gp);
			
		name = "key";
		down1 = setup("/objects/keys_1_1", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\n A key, I don'k know suddenly\n it's  just there inside my pocket.";
	}
}
