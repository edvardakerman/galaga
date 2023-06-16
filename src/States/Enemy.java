package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {

    private ImageView enemyImageView;
    private double enemyX;
    private double enemyY;

    public Enemy(double x, double y, String image) {
        enemyX = x;
        enemyY = y;

		try {
			Image enemyImage = new Image(new FileInputStream(image));
	        enemyImageView = new ImageView(enemyImage);
	        enemyImageView.setX(enemyX);
	        enemyImageView.setY(enemyY);
	        enemyImageView.setFitWidth(Constants.enemyWidth);
	        enemyImageView.setFitHeight(Constants.enemyHeight);
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
        enemyY += Constants.enemySpeed;
        enemyImageView.setY(enemyY);
    }
    
    public boolean collidesWith() {
    	if (this.enemyY >= 400) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
}
