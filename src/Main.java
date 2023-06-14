import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private Pane root;
    private Scene scene;
    private double screenWidth = 400;
    private double screenHeight = 400;
    private Player player;
    private List<Enemy> enemies;
    private Text scoreText, livesText;

    
    public void start(Stage primaryStage) {
        root = new Pane();
        
        livesText = createText(10, 20, "Lives: " + 3);
        root.getChildren().add(livesText);

        scoreText = createText(10, 50, "Score: " + 0);
        root.getChildren().add(scoreText);
        
        
        scene = new Scene(root, screenWidth, screenHeight);
        scene.setFill(javafx.scene.paint.Color.BLACK);

        player = new Player();
        root.getChildren().addAll(player.getPlayerImageView(), player.getLaserBeamView());

        enemies = new ArrayList<>();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                player.moveUp();
            } else if (e.getCode() == KeyCode.DOWN) {
                player.moveDown();
            } else if (e.getCode() == KeyCode.LEFT) {
                player.moveLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.moveRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                player.shootLaserBeam();
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            private long lastEnemySpawnTime = 0;
            private Random random = new Random();

            @Override
            public void handle(long now) {
                spawnEnemy(now);
                moveEntities();
                
            }

            private void spawnEnemy(long now) {
                if (now - lastEnemySpawnTime >= 2000000000) {
                    double enemyX = random.nextDouble() * (screenWidth - 40);
                    double enemyY = 0;
                    Enemy enemy = new Enemy(enemyX, enemyY);
                    enemies.add(enemy);
                    root.getChildren().add(enemy.getEnemyImageView());
                    lastEnemySpawnTime = now;
                }
            }

            private void moveEntities() {
                player.moveLaserBeam();
                
                Enemy enemyTmp = new Enemy(-100, -100);
                for (Enemy enemy : enemies) {
                    enemy.move();
                    
                    if (player.enemyHit(enemy)) {
                    	root.getChildren().remove(enemy.getEnemyImageView());
                    	enemyTmp = enemy;
                    	scoreText.setText("Score: " + player.getScore());
                    }
                    
                    if (enemy.collidesWith()) {
                    	root.getChildren().remove(enemy.getEnemyImageView());
                    	enemyTmp = enemy;
                    	player.setLives(player.getLives()-1);
                    	livesText.setText("Lives: " + player.getLives());
                    }
                }
            	enemies.remove(enemyTmp);
            }
        };
        timer.start();

        primaryStage.setTitle("Galactica!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Text createText(double x, double y, String text) {
        Text t = new Text(x, y, text);
        t.setFont(Font.font("Arial", 18));
        t.setFill(Color.WHITE);
        return t;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
