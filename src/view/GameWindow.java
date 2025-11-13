package view;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	private GamePanel gamePanel;
	
	public GameWindow() {
		
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

}
