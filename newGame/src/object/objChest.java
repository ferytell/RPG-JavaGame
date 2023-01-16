package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objChest extends SuperObject {
	
	public objChest() {
		
		name = "chest";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/Chest_1.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
