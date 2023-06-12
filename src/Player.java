import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {

    private ImageView imageView;
    private ImageView laserBeamView;
    private double x;
    private double y;
    private final double width = 40;
    private final double height = 40;
    private boolean shooting = false;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        createImageView();
        createLaserBeamView();
    }

    private void createImageView() {
        Image image = new Image("https://art.pixilart.com/02d5fee1a00ae6b.png");
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    private void createLaserBeamView() {
        Image laserBeamImage = new Image("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/8c8b1d81151796e.png");
        laserBeamView = new ImageView(laserBeamImage);
        laserBeamView.setX(-100);
        laserBeamView.setY(-100);
        laserBeamView.setFitWidth(10);
        laserBeamView.setFitHeight(40);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageView getLaserBeamView() {
        return laserBeamView;
    }

    public void moveUp() {
        y -= 10;
        imageView.setY(y);
    }

    public void moveDown() {
        y += 10;
        imageView.setY(y);
    }

    public void moveLeft() {
        x -= 10;
        imageView.setX(x);
    }

    public void moveRight() {
        x += 10;
        imageView.setX(x);
    }

    public void shoot() {
        if (!shooting) {
            shooting = true;
            double laserBeamX = x + width / 2 - laserBeamView.getFitWidth() / 2;
            double laserBeamY = y - laserBeamView.getFitHeight();
            laserBeamView.setX(laserBeamX);
            laserBeamView.setY(laserBeamY);
            startLaserBeamAnimation();
        }
    }

    private void startLaserBeamAnimation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            double laserBeamY = laserBeamView.getY();
            if (laserBeamY > 0) {
                laserBeamY -= 2;
                laserBeamView.setY(laserBeamY);
            } else {
                shooting = false;
                laserBeamView.setX(-100);
                laserBeamView.setY(-100);
            }
        }));
        timeline.setCycleCount((int)y);
        timeline.play();
    }
}
