package galaga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private ImageView imageView;
    private double imageViewX;
    private double imageViewY;
    private final double imageViewWidth = 40;
    private final double imageViewHeight = 40;

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

        // Create the pane and add the image view to it
        Pane root = new Pane();
        root.getChildren().add(imageView);

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
                default:
                    break;
            }
        });

        // Show the stage
		primaryStage.setTitle("Galactica!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

