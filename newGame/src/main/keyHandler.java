package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shootKeyPressed;
	// debug
	

	public keyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		// <<<<<<<<<<<<<<<<<<<<<<<< TITLE STATE >>>>>>>>>>>>>>>>>>>>>>>>
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}
		// <<<<<<<<<<<<<<<<<<<<<<<< PLAY STATE >>>>>>>>>>>>>>>>>>>>>>>>		
		else if (gp.gameState == gp.playState) {	
			playState(code);
		}
		// <<<<<<<<<<<<<<<<<<<<<<<< PAUSE STATE >>>>>>>>>>>>>>>>>>>>>>>>
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// <<<<<<<<<<<<<<<<<<<<<<<< DIALOGUE STATE >>>>>>>>>>>>>>>>>>>>>>>>
		else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		// <<<<<<<<<<<<<<<<<<<<<<<< CHARACTER STATE >>>>>>>>>>>>>>>>>>>>>>>>
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		// <<<<<<<<<<<<<<<<<<<<<<<< OPTION STATE >>>>>>>>>>>>>>>>>>>>>>>>
		else if (gp.gameState == gp.optionState) {
			optionState(code);
		}
	}
	
	public void titleState(int code) {
		if (gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W) { gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) { gp.ui.commandNum = 4; }
			}
			if(code == KeyEvent.VK_S) { gp.ui.commandNum++;
				if (gp.ui.commandNum > 4) { gp.ui.commandNum = 0; }
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) { gp.ui.titleScreenState = 1; }
				
				if (gp.ui.commandNum == 1) { 
				}
				if (gp.ui.commandNum == 2) { 
				}
				if (gp.ui.commandNum == 3) { gp.ui.titleScreenState = 3; }
				
				if (gp.ui.commandNum == 4) {
					System.exit(0);
				}
			}
		
		}
		
		// >>>>>>>>>>>>>>>>>> <<<<<<<<<<<<<<<< 
		
		else if (gp.ui.titleScreenState == 1) {
			if(code == KeyEvent.VK_W) { gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) { gp.ui.commandNum = 3; }
			}
			if(code == KeyEvent.VK_S) {	gp.ui.commandNum++;
				if (gp.ui.commandNum > 3) { gp.ui.commandNum = 0; }

			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					System.out.println("select player with more attack power");
					gp.gameState = gp.playState;
					gp.playMusic(7);
				}
				
				if (gp.ui.commandNum == 1) {
					System.out.println("select player with more speed");
					gp.gameState = gp.playState;
					gp.playMusic(7);
				}
				
				if (gp.ui.commandNum == 2) {
					System.out.println("select player with more magical power");
					gp.gameState = gp.playState;
					gp.playMusic(7);
				}

				if (gp.ui.commandNum == 3) {
					gp.ui.titleScreenState = 0;
					
				}
			}
		
		}
		
		else if (gp.ui.titleScreenState == 3) {
			if (code == KeyEvent.VK_ENTER) {
				gp.ui.titleScreenState = 0;
			}
		}
	}
	
	public void playState(int code) {

		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {				// Press "P" for Pause the game
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_F) {
			shootKeyPressed = true;	
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState =  gp.optionState;	
		}

	}
	
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code) {
		if (code == KeyEvent.VK_C) {
			gp.gameState =  gp.playState;
		}
		if (code == KeyEvent.VK_W) {
			if (gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				gp.playSE(11);
			}
		}
		if (code == KeyEvent.VK_A) {
			if (gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSE(11);
			}
		}
		if (code == KeyEvent.VK_S) {
			if (gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSE(11);
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSE(11);
			}			
		}
		if (code  == KeyEvent.VK_ENTER)  {
			gp.player.selectItem();
			
		}
	}

	public void optionState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		int maxCommandNum = 0;
		switch (gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		
		}
		
		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;	
			}
		}
		
		if (code == KeyEvent.VK_A) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(8);		
				}
				if (gp.ui.commandNum == 2 && gp.soundEffect.volumeScale > 0) {
					gp.soundEffect.volumeScale--;
					gp.playSE(8);
				}	
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale ++;
					gp.music.checkVolume();
					gp.playSE(8);		
				}
				if (gp.ui.commandNum == 2 && gp.soundEffect.volumeScale < 5) {
					gp.soundEffect.volumeScale++;
					gp.playSE(8);
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_F) {
			shootKeyPressed = false;
		}
	}
	

}
