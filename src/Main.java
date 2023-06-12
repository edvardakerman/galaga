import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	// Create pane
    	Pane root = new Pane();
        
    	// Create the scene
    	Scene scene = new Scene(root, 400, 400);
        
    	// Set background color
        scene.setFill(javafx.scene.paint.Color.BLACK);

        // Create player
        Player player = new Player(scene.getWidth() / 2, scene.getHeight() / 2);
        Enemy enemy = new Enemy(scene.getWidth() / 2, 10);

        root.getChildren().addAll(player.getImageView(), player.getLaserBeamView(), enemy.getImageView());

        // Key event handlers
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

        // Show stage & set title
        primaryStage.setTitle("Galactica!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
