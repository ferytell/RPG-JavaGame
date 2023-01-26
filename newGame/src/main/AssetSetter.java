package main;

import Monster.monGreenSlime;
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
		
		gp.obj[0] = new objDoor(gp);
		gp.obj[0].worldX = gp.tileSize * 2;
		gp.obj[0].worldY = gp.tileSize * 7;
		

		gp.obj[1] = new objBoot(gp);
		gp.obj[1].worldX = gp.tileSize * 2;
		gp.obj[1].worldY = gp.tileSize * 9;
	} 

	
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 6;
		gp.npc[0].worldY = gp.tileSize * 6;
		
		gp.npc[1] = new NPC_OldMan(gp);
		gp.npc[1].worldX = gp.tileSize * 2;
		gp.npc[1].worldY = gp.tileSize * 6;
	} 
	
	public void setMonster() {
		
		gp.monster[0] = new monGreenSlime(gp);
		gp.monster[0].worldX = gp.tileSize * 10;
		gp.monster[0].worldY = gp.tileSize * 12;
		

		gp.monster[1] = new monGreenSlime(gp);
		gp.monster[1].worldX = gp.tileSize * 9;
		gp.monster[1].worldY = gp.tileSize * 12;
	
	} 


}
