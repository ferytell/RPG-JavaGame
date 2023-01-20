package object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class objCoin extends SuperObject {
	
	public BufferedImage img1, img2, img3, img4;
	public static int spriteCounter = 0;
	public static int spriteNum = 1;
	
	public objCoin() {
		
		name = "coin";
		getCoinImage();
		update();
		
	}
	
	public void getCoinImage() {
		try {
			img1 =  ImageIO.read(getClass().getResourceAsStream("/objects/coin_1.png"));
			img2 =  ImageIO.read(getClass().getResourceAsStream("/objects/coin_2.png"));
			img3 =  ImageIO.read(getClass().getResourceAsStream("/objects/coin_3.png"));
			img4 =  ImageIO.read(getClass().getResourceAsStream("/objects/coin_4.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		spriteCounter++;
		if(spriteCounter > 15) {			// FPS is 60, this will tell how fast animation change (bigger = slower)
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			if(spriteNum == 2) {
				spriteNum = 3;
			}
			if(spriteNum == 3) {
				spriteNum = 4;
			}
			if(spriteNum == 4) {
				spriteNum = 1;
			}	
		}
		switch(spriteNum) {
		case 1:
			image = img1;
			break;
		case 2:
			image = img2;
			break;
		case 3:
			image = img3;
			break;
		case 4:
			image = img4;
			break;
			
		}
		
		
	}
	


}
