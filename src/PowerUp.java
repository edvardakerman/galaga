import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class PowerUp {
	
    private ImageView powerUpImageView;
    private double powerUpX;
    private double powerUpY;
    private double powerUpWidth = 40;
    private double powerUpHeight = 40;
	
	
	PowerUp(double x, double y, String image){
		this.powerUpX = x;
		this.powerUpY = y;
		
		try {
			Image powerUpImage = new Image(new FileInputStream(image));
			powerUpImageView = new ImageView(powerUpImage);
			powerUpImageView.setX(powerUpX);
			powerUpImageView.setY(powerUpY);
			powerUpImageView.setFitWidth(powerUpWidth);
			powerUpImageView.setFitHeight(powerUpHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
    public ImageView getPowerUpImageView() {
        return powerUpImageView;
    }
	
	public abstract void use();

}
