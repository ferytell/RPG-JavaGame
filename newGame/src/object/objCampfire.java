package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objCampfire extends SuperObject{
	public objCampfire() {
		
		name = "campfire";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/campfire.gif"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
