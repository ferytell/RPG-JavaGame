package object;
import entity.Entity;
import main.GamePanel;

public class objHeart extends Entity {
	
	
	public objHeart(GamePanel gp) {
		super(gp);
		
		name = "heart";
		image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
		
	}

}
