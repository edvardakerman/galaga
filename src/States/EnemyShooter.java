package States;

import Constants.Constants;

/**
 * EnemyShooter is a subclass to the Enemy class. This class adds the extra
 * functionality shooting to the EnemyShooter
 */

public class EnemyShooter extends Enemy {

	private double laserSpeed = -1;
	private boolean shooting = false;
	private LaserBeam laserBeam;

	public EnemyShooter(double x, double y, String image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		double laserBeamX = this.getX() + Constants.enemyWidth / 2;
		double laserBeamY = this.getY();
		laserBeam = new LaserBeam(laserBeamX, laserBeamY, laserSpeed);
	}

	public LaserBeam getLaserBeam() {
		return laserBeam;
	}

	public void shootLaserBeam() {
		if (!shooting) {
			shooting = true;
			double laserBeamX = this.getX() + Constants.enemyWidth / 2 - Constants.laserWidth / 2;
			double laserBeamY = this.getY() + Constants.laserHeight;
			laserBeam.getLaserBeamView().setY(laserBeamY);
			laserBeam.getLaserBeamView().setX(laserBeamX);
		}
	}

	public void moveLaserBeam() {
		if (shooting) {
			if (laserBeam.getLaserBeamView().getY() <= Constants.screenHeight + Constants.enemyHeight) {
				laserBeam.moveLaserBeam();
			}
		}

	}

	public boolean playerHitWithEnemyLaser(Player player) {
		boolean hit = false;

		if (shooting) {
			if (laserBeam.laserShipCollision(player.getPlayerImageView().getX(), player.getPlayerImageView().getY(),
					Constants.playerWidth, Constants.playerHeight)) {
				hit = true;
			}

		}

		return hit;
	}

}