package object;

import entity.Entity;
import main.GamePanel;

public class objPotionRed extends Entity{
	GamePanel gp;
	

	public objPotionRed(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = "Red Potion";
		value = 5;
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\n Potion for restore\n" + value + " your health.";
	}
	
	public void use(Entity entity) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "you drink the " + name + "!/n" + 
								value + "of your health recovered";
		entity.life += value;
		gp.playSE(2);
	}
	

}
