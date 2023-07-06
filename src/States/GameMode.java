package States;

import Constants.Constants;

public class GameMode {
	private String backgroundColor = Constants.Blackbackground;
    private String playerImg = Constants.playerImg;
    private String enemyImg = Constants.enemyImg;
    private String enemyShooterImg = Constants.enemyShooterImg;
    private String currentGameMode;

	
	public GameMode(String GameMode) {
		setGameMode(GameMode);
	}
	
	private void setGameMode(String GameMode) {
		this.currentGameMode = GameMode;
    	if (this.currentGameMode == "Classic") {
    		this.backgroundColor = Constants.Blackbackground;
    		this.playerImg = Constants.playerImg;
    	    this.enemyImg = Constants.enemyImg;
    	    this.enemyShooterImg = Constants.enemyShooterImg;
    	} else if (this.currentGameMode == "Special") {
    		this.backgroundColor = Constants.Bluebackground;
    		this.playerImg = Constants.specialPlayerImg;
    	    this.enemyImg = Constants.specialEnemyImg;
    	    this.enemyShooterImg = Constants.specialEnemyShooterImg;
    	}
	}
	
    public void switchGameMode() {
    	if (currentGameMode == "Classic") {
    		setGameMode("Special");
    	} else if (currentGameMode == "Special") {
    		setGameMode("Classic");	
    	}
    }

	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public String getPlayerImg() {
		return this.playerImg;
	}

	public String getEnemyImg() {
		return this.enemyImg;
	}

	public String getEnemyShooterImg() {
		return this.enemyShooterImg;
	}
	
	public String getCurrentGameMode() {
		return this.currentGameMode;
	}
}
