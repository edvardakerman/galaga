import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
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

    
    public void start(Stage primaryStage) {
        root = new Pane();
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
                    	System.out.println("Hit! " + player.getScore());
                    	enemyTmp = enemy;
                    }
                    
                    if (enemy.collidesWith()) {
                    	root.getChildren().remove(enemy.getEnemyImageView());
                    	System.out.println("Enemy ship slipt by!");
                    	enemyTmp = enemy;
                    	player.setLives(player.getLives()-1);
                    	System.out.println("Lives: "+ player.getLives());
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

    public static void main(String[] args) {
        launch(args);
    }
}
