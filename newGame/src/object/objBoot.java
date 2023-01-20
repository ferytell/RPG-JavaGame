package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objBoot extends SuperObject {
	public objBoot() {
		
		name = "boot";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
