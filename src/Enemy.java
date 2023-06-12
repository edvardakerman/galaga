import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Enemy {

    private ImageView imageView;
    private double x;
    private double y;
    private final double width = 40;
    private final double height = 40;

    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        createImageView();
    }

    private void createImageView() {
        Image image = new Image("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/03ce464db711efb.png");
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public ImageView getImageView() {
        return imageView;
    }
}

