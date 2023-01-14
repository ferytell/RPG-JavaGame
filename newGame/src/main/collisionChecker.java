package main;

import entity.Entity;

public class collisionChecker {
	GamePanel gp;
	public collisionChecker(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityLeftCol] [entityTopRow];
			tileNum2 = gp.tileMan.mapTileNum[entityRightCol] [entityTopRow];
			if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true ) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityLeftCol] [entityBottomRow];
			tileNum2 = gp.tileMan.mapTileNum[entityRightCol] [entityBottomRow];
			if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true ) {
				entity.collisionOn = true;
			}

			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityLeftCol] [entityTopRow];
			tileNum2 = gp.tileMan.mapTileNum[entityLeftCol] [entityBottomRow];
			if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true ) {
				entity.collisionOn = true;
			}

			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityRightCol] [entityTopRow];
			tileNum2 = gp.tileMan.mapTileNum[entityRightCol] [entityBottomRow];
			if(gp.tileMan.tile[tileNum1].collision == true || gp.tileMan.tile[tileNum2].collision == true ) {
				entity.collisionOn = true;
			}

			break;
		}
	}

}
