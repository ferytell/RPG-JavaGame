package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	

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
			
			if (gp.ui.titleScreenState == 0) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}

				}
				if (code == KeyEvent.VK_ENTER) {
					if (gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
						
					}
					
					if (gp.ui.commandNum == 1) {
						// 
						
					}
					
					if (gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
			
			}
			
			// >>>>>>>>>>>>>>>>>> <<<<<<<<<<<<<<<< 
			
			else if (gp.ui.titleScreenState == 1) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if (gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if (gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}

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
		}
		
		// <<<<<<<<<<<<<<<<<<<<<<<< PLAY STATE >>>>>>>>>>>>>>>>>>>>>>>>
		
		if (gp.gameState == gp.playState) {
			

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
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}

		}
		
		// <<<<<<<<<<<<<<<<<<<<<<<< PAUSE STATE >>>>>>>>>>>>>>>>>>>>>>>>
		
		else if (gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}
		// <<<<<<<<<<<<<<<<<<<<<<<< DIALOGUE STATE >>>>>>>>>>>>>>>>>>>>>>>>
		else if (gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
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
			downPressed = false;		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}

		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}
	

}
