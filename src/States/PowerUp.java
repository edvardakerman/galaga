package States;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class PowerUp {
	
    private ImageView powerUpImageView;
    private double powerUpX;
    private double powerUpY;

	PowerUp(double x, double y, String image){
		this.powerUpX = x;
		this.powerUpY = y;
		
		try {
			Image powerUpImage = new Image(new FileInputStream(image));
			powerUpImageView = new ImageView(powerUpImage);
			powerUpImageView.setX(powerUpX);
			powerUpImageView.setY(powerUpY);
			powerUpImageView.setFitWidth(Constants.powerUpWidth);
			powerUpImageView.setFitHeight(Constants.powerUpHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
    public ImageView getPowerUpImageView() {
        return powerUpImageView;
    }
    
    public boolean playerHit(Player player) {
    	boolean hit = false;

    	if (powerUpImageView.getX()+20 >=  player.getPlayerImageView().getX() && powerUpImageView.getX() <= (player.getPlayerImageView().getX()+20))  {
        	if (powerUpImageView.getY() <=  player.getPlayerImageView().getY()+20 && powerUpImageView.getY()+20 >= (player.getPlayerImageView().getY()))  {
        		powerUpImageView.setX(-100);
        		powerUpImageView.setY(-100);
        		hit = true;
        	}
    	}
    	return hit;
    }
    	
    	
	
	public abstract void use(Player player);

}