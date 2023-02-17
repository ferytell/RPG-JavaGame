package main;



public class eventHandler {
	
	GamePanel gp;
	EventRect eventRect[] [] [];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public eventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect [gp.maxMap] [gp.maxWorldCol] [gp.maxWorldRow]; 
		
		int map = 0;
		int col = 0;
		int row = 0;
		while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map] [col] [row] = new EventRect();
			eventRect[map] [col] [row].x = 23;
			eventRect[map] [col] [row].y = 23;
			eventRect[map] [col] [row].width = 2;
			eventRect[map] [col] [row].height = 2;
			eventRect[map] [col] [row].eventRectDefaultX = eventRect[map] [col] [row].x;
			eventRect[map] [col] [row].eventRectDefaultY = eventRect[map] [col] [row].y;
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if (row ==  gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent( ) {
		
		// Check if the Player char is more than 1 tile away from last event
		
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		if (canTouchEvent == true) {
			if (hit(0, 12, 3, "right") == true) {damagePit(gp.dialogueState);}
			else if (hit(0, 16, 11, "any") == true) {healingArea(gp.dialogueState);}
			else if (hit(0, 19, 4, "left") == true) {teleport(gp.dialogueState, 158, 222);}
			else if (hit(0, 187, 239, "down") == true) {teleport(gp.dialogueState, 3, 23);}
			else if (hit(0, 187, 239, "down") == true) {teleport(gp.dialogueState, 3, 23);}		
			else if (hit(0, 27, 13, "any") == true) {teleportMap(1, 28, 12);}
			else if (hit(1, 28, 12, "any") == true) {teleportMap(0, 27, 13);}
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if (map == gp.currentMap) {
			
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map] [col] [row].x = col * gp.tileSize + eventRect[map] [col] [row].x;
			eventRect[map] [col] [row].y = row * gp.tileSize + eventRect[map] [col] [row].y;
			
			if (gp.player.solidArea.intersects(eventRect[map] [col] [row]) && eventRect[map] [col] [row].eventDone == false) {
				if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map] [col] [row].x = eventRect[map] [col] [row].eventRectDefaultX;
			eventRect[map] [col] [row].y = eventRect[map] [col] [row].eventRectDefaultY;
		}

		return hit;
	}
	
	public void teleport(int gameState, int x, int y) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport";
		gp.player.worldX = gp.tileSize * x;
		gp.player.worldY = gp.tileSize * y;
	}
	
	public void teleportMap(int map, int col, int row) {
		
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize * col;
		gp.player.worldY = gp.tileSize * row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
		gp.playSE(14);
	}
	
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "you step on a shit";
		gp.player.life -= 1;
		gp.playSE(10);
//		eventRect[col] [row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingArea(int gameState) {
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true; 
			gp.ui.currentDialogue = "healing virtue";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
		}
		
		
	}

}
