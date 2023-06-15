import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {

    private ImageView enemyImageView;
    private double enemyX;
    private double enemyY;
    private double enemyWidth = 40;
    private double enemyHeight = 40;
    private double enemySpeed = 0.5;

    public Enemy(double x, double y, String image) {
        enemyX = x;
        enemyY = y;

		try {
			Image enemyImage = new Image(new FileInputStream(image));
	        enemyImageView = new ImageView(enemyImage);
	        enemyImageView.setX(enemyX);
	        enemyImageView.setY(enemyY);
	        enemyImageView.setFitWidth(enemyWidth);
	        enemyImageView.setFitHeight(enemyHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public double getX() {
    	return enemyX;
    }
    
    public double getY() {
    	return enemyY;
    }
    
    public double getEnemyWidth() {
    	return enemyWidth;
    }

    public ImageView getEnemyImageView() {
        return enemyImageView;
    }

    public void move() {
        enemyY += enemySpeed;
        enemyImageView.setY(enemyY);
    }
    
    public boolean collidesWith() {
        // Logic to check collision with wall
    	if (this.enemyY >= 400) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public ImageView getLaserBeamView() {
        return null;
    }
    
    public void shootLaserBeam() {
    	// empty
    }
    
    public void moveLaserBeam() {
    	// empty

    }
}
