import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);
        scene.setFill(javafx.scene.paint.Color.BLACK);

        Player player = new Player(scene.getWidth() / 2, scene.getHeight() / 2);

        root.getChildren().addAll(player.getImageView(), player.getLaserBeamView());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    player.moveUp();
                    break;
                case DOWN:
                    player.moveDown();
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case RIGHT:
                    player.moveRight();
                    break;
                case SPACE:
                    player.shoot();
                    break;
                default:
                    break;
            }
        });

        primaryStage.setTitle("Galactica!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
