package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class objDoor extends SuperObject{
	
	GamePanel gp;
	public objDoor(GamePanel gp) {
		
		name = "door";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/door_iron.png"));
			uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
