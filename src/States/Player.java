package States;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

    private ImageView playerImageView;
    private ImageView laserBeamView;
    private double playerX;
    private double playerY;
    private double playerWidth = 40;
    private double playerHeight = 40;
    private double playerSpeed = 10;
    private double laserSpeed = 6;
    private boolean shooting = false;
    private int score = 0;
    private int lives = 3;
    private int scoreMultiplier = 1;

    public Player() {
        playerX = 180;
        playerY = 350;

		try {
			Image playerImage = new Image(new FileInputStream(Constants.playerImg));
	        playerImageView = new ImageView(playerImage);
	        playerImageView.setX(playerX);
	        playerImageView.setY(playerY);
	        playerImageView.setFitWidth(playerWidth);
	        playerImageView.setFitHeight(playerHeight);
	        createLaserBeamView();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void createLaserBeamView() {
		try {
			Image laserBeamImage = new Image(new FileInputStream(Constants.laserImg));
	        laserBeamView = new ImageView(laserBeamImage);
	        laserBeamView.setX(-100);
	        laserBeamView.setY(-100);
	        laserBeamView.setFitWidth(10);
	        laserBeamView.setFitHeight(40);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public ImageView getLaserBeamView() {
        return laserBeamView;
    }
    
    public void moveUp() {
        if (playerY > 10) {
            playerY -= playerSpeed;
            playerImageView.setY(playerY);
        }
    }

    public void moveDown() {
        if (playerY < 360) {
            playerY += playerSpeed;
            playerImageView.setY(playerY);
        }
    }

    public void moveLeft() {
        if (playerX > 10) {
            playerX -= playerSpeed;
            playerImageView.setX(playerX);
        }
    }

    public void moveRight() {
        if (playerX < 360) {
            playerX += playerSpeed;
            playerImageView.setX(playerX);
        }
    }

    public void shootLaserBeam() {
        if (!shooting) {
        	shooting = true;
            double laserBeamX = playerX + playerWidth / 2 - laserBeamView.getFitWidth() / 2;
            double laserBeamY = playerY - laserBeamView.getFitHeight();
            laserBeamView.setX(laserBeamX);
            laserBeamView.setY(laserBeamY);
        }
    }
    
    public void moveLaserBeam() {    	
    	if (laserBeamView.getY() >= -30) {
    		laserBeamView.setY(laserBeamView.getY() - laserSpeed);
    	} else {
    		shooting = false;
    	}
        
    }

    public boolean enemyHit(Enemy enemy) {
    	boolean hit = false;
    	
    	if (shooting) {
        	if (laserBeamView.getX() >=  enemy.getX() && laserBeamView.getX() <= (enemy.getX() + 40))  {
            	if (laserBeamView.getY() >=  enemy.getY() && laserBeamView.getY() <= (enemy.getY() + 40))  {
            		shooting = false;
        	        laserBeamView.setX(-100);
        	        laserBeamView.setY(-100);
            		hit = true;
            		setScore(1);
            	}
        	}
    	}
    	
    	return hit;
    }

	public int getScore() {
		return score;
	}
	
	public void setScore(int Score) {
		this.score += Score * scoreMultiplier;
	}
	
	public void setScoreMultiplier(int newValue) {
		this.scoreMultiplier = newValue;
	}
	
	public int getScoreMultiplie() {
		return scoreMultiplier;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
}
