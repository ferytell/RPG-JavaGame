package main;

import entity.NPC_OldMan;
import object.objBoot;
import object.objCampfire;
import object.objChest;
import object.objCoin;
import object.objDoor;
import object.objKey;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 6;
		gp.npc[0].worldY = gp.tileSize * 6;
	} 

}
