package object;

import entity.Entity;
import main.GamePanel;

public class objCoinBronze extends Entity{
	
	GamePanel gp;
	public objCoinBronze(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Bronze Coin";
		down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
		value = 1;
	}
	
	public void use(Entity entity) {
		
		gp.playSE(1);
		gp.ui.addMessage("+" + value + "Coin" );
		gp.player.coin += value;

	}
	
	
	
}
