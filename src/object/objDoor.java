package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objDoor extends SuperObject{
	public objDoor() {
		
		name = "door";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/door_iron.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
