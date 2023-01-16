package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objKey extends SuperObject{

	public objKey() {
		
		name = "key";
		try {
			image =  ImageIO.read(getClass().getResourceAsStream("/objects/keys_1_1.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
