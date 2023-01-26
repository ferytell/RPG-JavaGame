package object;
import entity.Entity;
import main.GamePanel;

public class objHeart extends Entity {
	
	
	public objHeart(GamePanel gp) {
		super(gp);
		
		name = "heart";
		image = setup("/objects/heart_full");
		image2 = setup("/objects/heart_half");
		image3 = setup("/objects/heart_blank");
		
	}

}
