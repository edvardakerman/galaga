import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Pane {

    private final double screenWidth = 400;
    private final double screenHeight = 400;
    private Player player;
    private List<Enemy> enemies;
    private Text scoreText, livesText, gameOverText;
    private boolean isGameOver = false;
    private AnimationTimer gameLoop;

    public Game() {
        setStyle("-fx-background-color: #111111;");
        setPrefSize(screenWidth, screenHeight);

        player = new Player();
        getChildren().addAll(player.getPlayerImageView(), player.getLaserBeamView());

        enemies = new ArrayList<>();
        
        gameOverText = createText(screenWidth / 2 -50, screenHeight / 2 -20, "Game Over");

        livesText = createText(10, 20, "Lives: " + player.getLives());
        getChildren().add(livesText);

        scoreText = createText(10, 50, "Score: " + player.getScore());
        getChildren().add(scoreText);
    
        setOnKeyPressed(e -> {
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

        gameLoop = new AnimationTimer() {
            private long lastEnemySpawnTime = 10;
            private Random random = new Random();
       

            @Override
            public void handle(long now) {
            	if (isGameOver) {
                    getChildren().add(gameOverText);
            		this.stop();
            	}
                spawnEnemy(now);
                moveEntities();
            }

            private void spawnEnemy(long now) {
                if (now - lastEnemySpawnTime >= 2000000000) {                
                    double enemyX = random.nextDouble() * (screenWidth - 40);
                    double enemyY = 0;
                    if (random.nextInt(10) + 1 > 5) {
                    	Enemy enemy = new Enemy(enemyX, enemyY, Constants.enemyImg);
                    	getChildren().add(enemy.getEnemyImageView());
                    	enemies.add(enemy);
                    } else {
                    	EnemyShooter enemy = new EnemyShooter(enemyX, enemyY, Constants.enemyShooterImg);
                    	getChildren().addAll(enemy.getEnemyImageView(), enemy.getLaserBeamView()); 
                    	enemies.add(enemy);
                    }
                       
                    lastEnemySpawnTime = now;
                    
                }
            }

            private void moveEntities() {
            	
                player.moveLaserBeam();
                
                Enemy enemyTmp = new Enemy(-100, -100, Constants.enemyImg);
                for (Enemy enemy : enemies) {
                    enemy.move();
                    enemy.shootLaserBeam();
                    enemy.moveLaserBeam();
                    
                    if (player.enemyHit(enemy)) {
                    	getChildren().removeAll(enemy.getEnemyImageView(), enemy.getLaserBeamView());
                    	enemyTmp = enemy;
                    	scoreText.setText("Score: " + player.getScore());
                    }
                    
                    if (enemy.playerHit(player)) {
                    	player.setLives(player.getLives()-1);
                    	livesText.setText("Lives: " + player.getLives());
                        if (player.getLives() == 0) {
                            stopGame();
                            break;
                        }
                    }
                    
                    if (enemy.collidesWith()) {
                    	getChildren().remove(enemy.getEnemyImageView());
                    	enemyTmp = enemy;
                    	player.setLives(player.getLives()-1);
                    	livesText.setText("Lives: " + player.getLives());
                        if (player.getLives() == 0) {
                            stopGame();
                            break;
                        }
                    }
                }
                
                enemies.remove(enemyTmp);
            }
        };

    }
    
    public void handleKeyPress(KeyCode keyCode) {
        if (keyCode == KeyCode.UP) {
            player.moveUp();
        } else if (keyCode == KeyCode.DOWN) {
            player.moveDown();
        } else if (keyCode == KeyCode.LEFT) {
            player.moveLeft();
        } else if (keyCode == KeyCode.RIGHT) {
            player.moveRight();
        } else if (keyCode == KeyCode.SPACE) {
            player.shootLaserBeam();
        }
    }

    private Text createText(double x, double y, String text) {
        Text t = new Text(x, y, text);
        t.setFont(Font.font("Arial", 18));
        t.setFill(Color.WHITE);
        return t;
    }
    
    public void startGame() {
        gameLoop.start();
    }

    private void stopGame() {
        isGameOver = true;
    }
  
}
