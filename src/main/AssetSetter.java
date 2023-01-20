package main;

import object.objCampfire;
import object.objChest;
import object.objCoin;
import object.objKey;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new objKey();
		gp.obj[0].worldX = 5 * gp.tileSize;
		gp.obj[0].worldY = 2 * gp.tileSize;
		
		gp.obj[1] = new objChest();
		gp.obj[1].worldX = 8 * gp.tileSize;
		gp.obj[1].worldY = 3 * gp.tileSize;
		
		gp.obj[2] = new objChest();
		gp.obj[2].worldX = 13 * gp.tileSize;
		gp.obj[2].worldY = 3 * gp.tileSize;

		gp.obj[3] = new objKey();
		gp.obj[3].worldX = 16 * gp.tileSize;
		gp.obj[3].worldY = 3 * gp.tileSize;

		gp.obj[4] = new objCoin();
		gp.obj[4].worldX = 17 * gp.tileSize;
		gp.obj[4].worldY = 3 * gp.tileSize;

	}

}
