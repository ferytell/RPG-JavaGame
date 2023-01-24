package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class objBoot extends SuperObject {
	
	GamePanel gp;
	public objBoot(GamePanel gp) {
		
		name = "boot";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
