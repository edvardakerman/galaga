package galaga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private ImageView imageView;
    private ImageView laserBeamView;
    private double imageViewX;
    private double imageViewY;
    private final double imageViewWidth = 40;
    private final double imageViewHeight = 40;
    private boolean shooting = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the image view object
        Image image = new Image("https://art.pixilart.com/02d5fee1a00ae6b.png");
        imageView = new ImageView(image);
        imageViewX = 200;
        imageViewY = 200;
        imageView.setX(imageViewX);
        imageView.setY(imageViewY);
        imageView.setFitWidth(imageViewWidth);
        imageView.setFitHeight(imageViewHeight);
        
        // Create the laser beam view object
        Image laserBeamImage = new Image("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/8c8b1d81151796e.png");
        laserBeamView = new ImageView(laserBeamImage);
        laserBeamView.setX(-100);
        laserBeamView.setY(-100);
        laserBeamView.setFitWidth(10);
        laserBeamView.setFitHeight(40);

        // Create the pane and add the image view to it
        Pane root = new Pane();
        root.getChildren().addAll(imageView, laserBeamView);

        // Create the scene
        Scene scene = new Scene(root, 400, 400);
        scene.setFill(javafx.scene.paint.Color.BLACK);

        // Add key event handlers
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    imageViewY -= 10;
                    imageView.setY(imageViewY);
                    break;
                case DOWN:
                    imageViewY += 10;
                    imageView.setY(imageViewY);
                    break;
                case LEFT:
                    imageViewX -= 10;
                    imageView.setX(imageViewX);
                    break;
                case RIGHT:
                    imageViewX += 10;
                    imageView.setX(imageViewX);
                    break;
                case SPACE:
                    if (!shooting) {
                        shooting = true;
                        double laserBeamX = imageViewX + imageViewWidth / 2 - laserBeamView.getFitWidth() / 2;
                        double laserBeamY = imageViewY - laserBeamView.getFitHeight();
                        laserBeamView.setX(laserBeamX);
                        laserBeamView.setY(laserBeamY);
                        startLaserBeamAnimation();
                    }
                    break;
                default:
                    break;
            }
        });

        // Show the stage
		primaryStage.setTitle("Galactica!");
        primaryStage.setScene(scene);
        primaryStage.show();
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
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}

