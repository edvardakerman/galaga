package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Enemy handles the enemy behavior including its ImageView and variables. This
 * is a superclass to the EnemyShooter class.
 */

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

	public boolean slipsByPlayer() {
		if (this.enemyY >= 400) {
			return true;
		} else {
			return false;
		}
	}

	public boolean playerEnemyCollision(Player player) {
		boolean hit = false;

		Rectangle playerRect = new Rectangle(player.getPlayerImageView().getX(), player.getPlayerImageView().getY(),
				Constants.playerWidth, Constants.playerHeight);

		Rectangle enemyRect = new Rectangle(this.getEnemyImageView().getX(), this.getEnemyImageView().getY(),
				Constants.enemyWidth, Constants.enemyHeight);

		if (playerRect.getBoundsInParent().intersects(enemyRect.getBoundsInParent())) {
			hit = true;
		}

		return hit;
	}

}
