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
		int i = 0;
		
		gp.obj[i] = new objDoor(gp);
		gp.obj[i].worldX = gp.tileSize * 2;
		gp.obj[i].worldY = gp.tileSize * 7;
		i++;

		gp.obj[i] = new objBoot(gp);
		gp.obj[i].worldX = gp.tileSize * 2;
		gp.obj[i].worldY = gp.tileSize * 9;
		i++;
		
		
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
		int i = 0;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 10;
		gp.monster[i].worldY = gp.tileSize * 12;
		i++;

		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 9;
		gp.monster[i].worldY = gp.tileSize * 12;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 20;
		gp.monster[i].worldY = gp.tileSize * 9;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 24;
		gp.monster[i].worldY = gp.tileSize * 9;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 19;
		gp.monster[i].worldY = gp.tileSize * 6;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 17;
		gp.monster[i].worldY = gp.tileSize * 8;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 23;
		gp.monster[i].worldY = gp.tileSize * 7;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 11;
		gp.monster[i].worldY = gp.tileSize * 4;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 12;
		gp.monster[i].worldY = gp.tileSize * 10;
		i++;
		
		gp.monster[i] = new monGreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize * 25;
		gp.monster[i].worldY = gp.tileSize * 8;
		i++;
	} 


}
