package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LaserBeam {
	
    private ImageView laserBeamView;
    private double laserX;
    private double laserY;
    private double laserSpeed = 6;
    //private boolean shooting = false;
	
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
    
    public boolean hit(double x1, double x2, double y1, double y2) {
    	boolean hit = false;
    	
        	if (laserBeamView.getX() >=  x1 && laserBeamView.getX() <= x2)  {
            	if (laserBeamView.getY() <=  y1 && laserBeamView.getY() >= y2)  {
        	        laserBeamView.setX(-100);
        	        laserBeamView.setY(-100);
            		hit = true;
            	}
        	}
    	
    	return hit;
    }
}
