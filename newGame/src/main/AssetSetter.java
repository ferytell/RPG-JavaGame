package main;

import Monster.monGreenSlime;
import entity.NPC_OldMan;
import object.objAxe;
import object.objBoot;
import object.objCampfire;
import object.objChest;
import object.objCoin;
import object.objCoinBronze;
import object.objDoor;
import object.objHeart;
import object.objKey;
import object.objMana;
import object.objPotionRed;
import object.objShieldBlue;
import tile_interactive.dryTree;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int i = 0;
		
		gp.obj[i] = new objDoor(gp);
		gp.obj[i].worldX = gp.tileSize * 26;
		gp.obj[i].worldY = gp.tileSize * 12;
		i++;

		gp.obj[i] = new objKey(gp);
		gp.obj[i].worldX = gp.tileSize * 2;
		gp.obj[i].worldY = gp.tileSize * 9;
		i++;
		
		
		gp.obj[i] = new objAxe(gp);
		gp.obj[i].worldX = gp.tileSize * 20;
		gp.obj[i].worldY = gp.tileSize * 4;
		i++;
		
		gp.obj[i] = new objShieldBlue(gp);
		gp.obj[i].worldX = gp.tileSize * 3;
		gp.obj[i].worldY = gp.tileSize * 2;
		i++;
		
		gp.obj[i] = new objPotionRed(gp);
		gp.obj[i].worldX = gp.tileSize * 11;
		gp.obj[i].worldY = gp.tileSize * 22;
		i++;
		
		gp.obj[i] = new objHeart(gp);
		gp.obj[i].worldX = gp.tileSize * 15;
		gp.obj[i].worldY = gp.tileSize * 5;
		i++;
		
		gp.obj[i] = new objMana(gp);
		gp.obj[i].worldX = gp.tileSize * 33;
		gp.obj[i].worldY = gp.tileSize * 8;
		i++;
		
		gp.obj[i] = new objPotionRed(gp);
		gp.obj[i].worldX = gp.tileSize * 27;
		gp.obj[i].worldY = gp.tileSize * 14;
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
	
	public void setInteractiveTiles() {
		int i=0;
		gp.iTile[i] = new dryTree(gp, 2, 21); i++;
		gp.iTile[i] = new dryTree(gp, 3, 21); i++;
		gp.iTile[i] = new dryTree(gp, 4, 21); i++;
		gp.iTile[i] = new dryTree(gp, 16, 10); i++;
		
	}


}
