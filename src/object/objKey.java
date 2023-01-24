package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class objKey extends SuperObject{
	
	GamePanel gp;
	public objKey(GamePanel gp) {
		
		name = "key";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/keys_1_1.png"));
			uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
