package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class dryTree extends tile_interactive {

	GamePanel gp;
	public dryTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		
		down1 = setup("/tilesInteractive/038", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 5;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		
		if (entity.currentWeapon.type == type_axe) {
			isCorrectItem = true;
		}
		
		return isCorrectItem;
	}
	
	public void playSE() {
		gp.playSE(12);
	}
	
	public tile_interactive getDestroyedForm()  {
		tile_interactive tile = new treeTrunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}

}
