package States;

import Constants.Constants;

public class EnemyShooter extends Enemy{
	
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
        	if (laserBeam.getLaserBeamView().getY() <= 420) {
        		laserBeam.moveLaserBeam();
        	} else {
        		shooting = false;
        	}
    	}

    }
    
    public boolean playerHit(Player player) {
    	boolean hit = false;
    	 
    	if (shooting) {
    		if (laserBeam.hit(player.getPlayerImageView().getX(), player.getPlayerImageView().getX()+Constants.playerWidth, player.getPlayerImageView().getY(), player.getPlayerImageView().getY()-Constants.playerHeight )) {
        		shooting = false;
        		hit = true;
    		}

    	}
    	
    	return hit;
    }

}