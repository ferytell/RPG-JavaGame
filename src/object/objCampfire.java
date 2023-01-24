package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class objCampfire extends SuperObject{
	
	GamePanel gp;
	public objCampfire(GamePanel gp) {
		
		name = "campfire";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/campfire.gif"));
			uTools.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
