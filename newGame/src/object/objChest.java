package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class objChest extends SuperObject {
	
	GamePanel gp;
	public objChest(GamePanel gp) {
		
		name = "chest";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/Chest_1.png"));
			uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
