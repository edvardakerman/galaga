import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyShooter extends Enemy{
	
    private ImageView laserBeamView;
    private double laserSpeed = 1;
    private boolean shooting = false;

	public EnemyShooter(double x, double y, String image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		createLaserBeamView();
	}
	
    private void createLaserBeamView() {
		try {
			Image laserBeamImage = new Image(new FileInputStream(Constants.laserImg));
	        laserBeamView = new ImageView(laserBeamImage);
	        laserBeamView.setX(-100);
	        laserBeamView.setY(-100);
	        laserBeamView.setFitWidth(10);
	        laserBeamView.setFitHeight(40);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ImageView getLaserBeamView() {
        return laserBeamView;
    }
    
    @Override
    public void shootLaserBeam() {
        if (!shooting) {
        	shooting = true;
            double laserBeamX = this.getX() + this.getEnemyWidth() / 2 - laserBeamView.getFitWidth() / 2;
            double laserBeamY = this.getY() + laserBeamView.getFitHeight();
            laserBeamView.setX(laserBeamX);
            laserBeamView.setY(laserBeamY);
        }
    }
    
    public void moveLaserBeam() {
    	if (shooting) {
        	if (laserBeamView.getY() <= 420) {
        		laserBeamView.setY(laserBeamView.getY() + laserSpeed);
        	} else {
        		shooting = false;
        	}
    	}

    }
    
    public boolean playerHit(Player player) {
    	boolean hit = false;
    	
    	if (shooting) {
        	if (laserBeamView.getX() >=  player.getPlayerImageView().getX() && laserBeamView.getX() <= (player.getPlayerImageView().getX() + 40))  {
            	if (laserBeamView.getY() <=  player.getPlayerImageView().getY() && laserBeamView.getY() >= (player.getPlayerImageView().getY() - 40))  {
            		shooting = false;
        	        laserBeamView.setX(-100);
        	        laserBeamView.setY(-100);
            		hit = true;
            	}
        	}
    	}
    	
    	return hit;
    }

}
