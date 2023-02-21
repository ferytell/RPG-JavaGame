package entity;

import main.GamePanel;
import object.objMana;
import object.objPotionRed;

public class NPC_Merchant extends Entity{
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
		setDialogue();
		setItem();
		
	}
//	public BufferedImage poke;
	public void getImage() {
		
		
		up1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/NPC/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/merchant_down_2", gp.tileSize, gp.tileSize);
		
	}
	public void setDialogue() {
		
		dialogue[0] = " wassap bro?!/n you wanna some good stuff?";
	}
	
	
	public void setItem() {
		inventory.add(new objPotionRed(gp));
		inventory.add(new objPotionRed(gp));
		inventory.add(new objMana(gp));
	}
	
	public void speak() {
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
