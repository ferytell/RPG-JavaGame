package main;

import Monster.monGreenSlime;
import entity.NPC_Merchant;
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
		
		int mapNum = 0;
		int i = 0;
		
		gp.obj[mapNum] [i] = new objDoor(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 26;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 12;
		i++;

		gp.obj[mapNum] [i] = new objKey(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 2;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 9;
		i++;
		
		
		gp.obj[mapNum] [i] = new objAxe(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 20;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 4;
		i++;
		
		gp.obj[mapNum] [i] = new objShieldBlue(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 3;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 2;
		i++;
		
		gp.obj[mapNum] [i] = new objPotionRed(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 11;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 22;
		i++;
		
		gp.obj[mapNum] [i] = new objHeart(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 15;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 5;
		i++;
		
		gp.obj[mapNum] [i] = new objMana(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 33;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 8;
		i++;
		
		gp.obj[mapNum] [i] = new objPotionRed(gp);
		gp.obj[mapNum] [i].worldX = gp.tileSize * 27;
		gp.obj[mapNum] [i].worldY = gp.tileSize * 14;
		i++;
		
		
	} 

	
	public void setNPC() {
		
		int i = 0;
		
		// MAP 0 (island2)
		int mapNum = 0;
		gp.npc[mapNum] [i] = new NPC_OldMan(gp);
		gp.npc[mapNum] [i].worldX = gp.tileSize * 6;
		gp.npc[mapNum] [i].worldY = gp.tileSize * 6;
		i++;
		
		// MAP 1 (island)
		mapNum = 1;
		i = 0;
		gp.npc[mapNum] [i] = new NPC_Merchant(gp);
		gp.npc[mapNum] [i].worldX = gp.tileSize * 29;
		gp.npc[mapNum] [i].worldY = gp.tileSize * 8;
		i++;
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 10;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 12;
		i++;

		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 9;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 12;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 20;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 9;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 24;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 9;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 19;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 6;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 17;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 8;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 23;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 7;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 11;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 4;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 12;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 10;
		i++;
		
		gp.monster[mapNum] [i] = new monGreenSlime(gp);
		gp.monster[mapNum] [i].worldX = gp.tileSize * 25;
		gp.monster[mapNum] [i].worldY = gp.tileSize * 8;
		i++;
	}
	
	public void setInteractiveTiles() {
		int mapNum = 0;
		int i=0;
		gp.iTile[mapNum] [i] = new dryTree(gp, 2, 21); i++;
		gp.iTile[mapNum] [i] = new dryTree(gp, 3, 21); i++;
		gp.iTile[mapNum] [i] = new dryTree(gp, 4, 21); i++;
		gp.iTile[mapNum] [i] = new dryTree(gp, 16, 10); i++;
		
	}


}
