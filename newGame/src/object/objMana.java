package object;

import entity.Entity;
import main.GamePanel;

public class objMana extends Entity{
	
	GamePanel gp;
	public objMana(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		
		type = type_pickupOnly;
		value = 1;
		
		name = "Mana Crystal";
		down1 = setup ("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image = setup ("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image2 = setup ("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\n Cristal for restore\n" + value + " your mana.";
		price = 5;
		
	}
	
	public void use(Entity entity) {
		gp.playSE(2);
		gp.ui.addMessage("Mana +" +value);
		entity.mana += value;
		
	}
	

}
