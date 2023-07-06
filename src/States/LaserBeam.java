package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class LaserBeam {

	private ImageView laserBeamView;
	private double laserX;
	private double laserY;
	private double laserSpeed;

	public LaserBeam(double x, double y, double laserSpeed) {
		this.laserX = x - Constants.laserWidth / 2;
		this.laserY = y + Constants.laserHeight;
		this.laserSpeed = laserSpeed;
		createLaserBeamView();
	}

	private void createLaserBeamView() {
		try {
			Image laserBeamImage = new Image(new FileInputStream(Constants.laserImg));
			laserBeamView = new ImageView(laserBeamImage);
			laserBeamView.setX(laserX);
			laserBeamView.setY(laserY);
			laserBeamView.setFitWidth(Constants.laserWidth);
			laserBeamView.setFitHeight(Constants.laserHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImageView getLaserBeamView() {
		return laserBeamView;
	}

	public void moveLaserBeam() {
		laserBeamView.setY(laserBeamView.getY() - laserSpeed);
	}

	public boolean laserShipCollision(double shipX, double shipY, double shipWidth, double shipHeight) {
		boolean hit = false;

		Rectangle shipRect = new Rectangle(shipX, shipY, shipWidth, shipHeight);

		Rectangle laserRect = new Rectangle(this.getLaserBeamView().getX(), this.laserBeamView.getY(),
				Constants.laserWidth, Constants.laserHeight);

		if (shipRect.getBoundsInParent().intersects(laserRect.getBoundsInParent())) {
			hit = true;
	        laserBeamView.setX(-100);
	        laserBeamView.setY(-100);
		}

		return hit;
	}
}
