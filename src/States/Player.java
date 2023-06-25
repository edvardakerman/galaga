package States;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player {

    private ImageView playerImageView;
    private LaserBeam laserBeam;
    private double playerX = (Constants.screenWidth - Constants.playerWidth) / 2;
    private double playerY = Constants.screenHeight - 50;
    private boolean shooting = false;
    private int score = 0;
    private int lives = 3;
    private int scoreMultiplier = 1;
    
    public Player(String playerImg) {
    	
		try {
			Image playerImage = new Image(new FileInputStream(playerImg));
	        playerImageView = new ImageView(playerImage);
	        playerImageView.setX(playerX);
	        playerImageView.setY(playerY);
	        playerImageView.setFitWidth(Constants.playerWidth);
	        playerImageView.setFitHeight(Constants.playerHeight);
	        laserBeam = new LaserBeam(-100, -100, Constants.laserSpeed);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public LaserBeam getLaserBeam() {
        return laserBeam;
    }
    
    public void move(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) {
            this.moveUp();
        } else if (keyCode == KeyCode.DOWN) {
            this.moveDown();
        } else if (keyCode == KeyCode.LEFT) {
            this.moveLeft();
        } else if (keyCode == KeyCode.RIGHT) {
            this.moveRight();
        } else if (keyCode == KeyCode.SPACE) {
            this.shootLaserBeam();
        }
    }
    
    private void moveUp() {
        if (playerY > 10) {
            playerY -= Constants.playerSpeed;
            playerImageView.setY(playerY);
        }
    }

    private void moveDown() {
        if (playerY < 360) {
            playerY += Constants.playerSpeed;
            playerImageView.setY(playerY);
        }
    }

    private void moveLeft() {
        if (playerX > 0) {
            playerX -= Constants.playerSpeed;
            playerImageView.setX(playerX);
        }
    }

    private void moveRight() {
        if (playerX < 360) {
            playerX += Constants.playerSpeed;
            playerImageView.setX(playerX);
        }
    }

    private void shootLaserBeam() {
        if (!shooting) {
        	shooting = true;
            double laserBeamX = playerX + Constants.playerWidth / 2 - Constants.laserWidth / 2;
            double laserBeamY = playerY - Constants.laserHeight;
            laserBeam.getLaserBeamView().setY(laserBeamY);
            laserBeam.getLaserBeamView().setX(laserBeamX);
        }
    }
    
    public void moveLaserBeam() {    	
    	if (shooting) {
        	if (laserBeam.getLaserBeamView().getY() >= -30) {
        		laserBeam.moveLaserBeam();
        	} else {
        		shooting = false;
        	}
    	}
        
    }
    
    
    public boolean enemyHit(Enemy enemy) {
    	boolean hit = false;
    	
    	if (shooting) {
    		if (laserBeam.hit(enemy.getEnemyImageView().getX(), enemy.getEnemyImageView().getX()+Constants.enemyWidth, enemy.getEnemyImageView().getY(), enemy.getEnemyImageView().getY()-Constants.enemyHeight )) {
        		shooting = false;
        		hit = true;
        		setScore(1);
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
