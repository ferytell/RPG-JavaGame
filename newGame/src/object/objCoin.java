package object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class objCoin extends Entity {
	
	public BufferedImage img1, img2, img3, img4;
	public static int spriteCounter = 0;
	public static int spriteNum = 1;
	GamePanel gp;
	
	public objCoin(GamePanel gp) {
		
		super(gp);
		
		name = "coin";
		
		img2 = setup("/objects/coin_2.png", gp.tileSize, gp.tileSize);
		img3 = setup("/objects/coin_3.png", gp.tileSize, gp.tileSize);
		img4 = setup("/objects/coin_4.png", gp.tileSize, gp.tileSize);
		img1 = setup("/objects/coin_1.png", gp.tileSize, gp.tileSize);
		
		update();
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
			//uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			break;
		case 3:
			image = img3;
			//uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			break;
		case 4:
			image = img4;
			//uTools.scaleImage(image, gp.tileSize, gp.tileSize);
			break;
			
		}
		
		
	}
	


}
