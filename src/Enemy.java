import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {

    private ImageView enemyImageView;
    private double enemyX;
    private double enemyY;
    private double enemyWidth = 40;
    private double enemyHeight = 40;
    private double enemySpeed = 0.5;

    public Enemy(double x, double y) {
        enemyX = x;
        enemyY = y;

		try {
			Image enemyImage = new Image(new FileInputStream(Constants.enemyImg));
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

    public ImageView getEnemyImageView() {
        return enemyImageView;
    }

    public void move() {
        enemyY += enemySpeed;
        enemyImageView.setY(enemyY);
    }
    
    public boolean collidesWith() {
        // Logic to check collision with an wall
    	if (this.enemyY == 400) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
