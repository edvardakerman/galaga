package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * PowerUp is a abstract class and provides the basic functionality of
 * ImageView, positioning and collisions.
 */

public abstract class PowerUp {

	private ImageView powerUpImageView;
	private double powerUpX;
	private double powerUpY;

	PowerUp(double x, double y, String image) {
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

	public boolean powerUpPlayerCollision(Player player) {
		boolean hit = false;

		Rectangle shipRect = new Rectangle(player.getPlayerImageView().getX(), player.getPlayerImageView().getY(),
				Constants.playerWidth, Constants.playerHeight);

		Rectangle powerUpRect = new Rectangle(this.getPowerUpImageView().getX(), this.getPowerUpImageView().getY(),
				Constants.powerUpWidth, Constants.powerUpHeight);

		if (shipRect.getBoundsInParent().intersects(powerUpRect.getBoundsInParent())) {
			hit = true;
			powerUpImageView.setX(-100);
			powerUpImageView.setY(-100);
		}

		return hit;
	}

	public abstract void use(Player player);

}
