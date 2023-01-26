package object;


import entity.Entity;
import main.GamePanel;

public class objDoor extends Entity{
	
	public objDoor(GamePanel gp) {
		
		super(gp);
		name = "door";
		down1 = setup("/objects/door_iron");
		
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
