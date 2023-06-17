package States;
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

    private Player player;
    private List<Enemy> enemies;
    private List<EnemyShooter> enemieShooters;
    private List<LaserBeam> laserBeams;
    private Text scoreText, livesText, gameOverText, instrcutionsText;
    private boolean isGameOver = false;
    private AnimationTimer gameLoop;
    private String playerImg = Constants.playerImg;
    private String enemyImg = Constants.enemyImg;
    private String enemyShooterImg = Constants.enemyShooterImg;

    public Game(String gameTyp) {
        
        setPrefSize(Constants.screenWidth, Constants.screenHeight);  
        
    	if (gameTyp == "classicMode") {
    		setStyle("-fx-background-color: #111111;");
    		this.playerImg = Constants.playerImg;
    	    this.enemyImg = Constants.enemyImg;
    	    this.enemyShooterImg = Constants.enemyShooterImg;
    	} else if (gameTyp == "specialMode") {
    		setStyle("-fx-background-color: #34495E;");
    		this.playerImg = Constants.specialPlayerImg;
    	    this.enemyImg = Constants.specialEnemyImg;
    	    this.enemyShooterImg = Constants.specialEnemyShooterImg;
    	}
        
        player = new Player(playerImg);
        getChildren().addAll(player.getPlayerImageView(), player.getLaserBeam().getLaserBeamView());

        enemies = new ArrayList<>();
        enemieShooters = new ArrayList<>();
        laserBeams = new ArrayList<>();
        
        gameOverText = createText(Constants.screenWidth / 2 -50, Constants.screenHeight / 2 -30, "Game Over", 20);
        instrcutionsText = createText(Constants.screenWidth / 2 -70, Constants.screenHeight / 2, "Press ESC for menu", 15);

        livesText = createText(10, 20, "Lives: " + player.getLives(), 18);
        scoreText = createText(10, 50, "Score: " + player.getScore(), 18);
        getChildren().addAll(livesText, scoreText);
        
        ExtraLifePowerUp extraLifePowerUp = new ExtraLifePowerUp(-100, -100, Constants.heartImg);
        getChildren().add(extraLifePowerUp.getPowerUpImageView());
        
        ExtraScorePowerUp extraScorePowerUp = new ExtraScorePowerUp(-100, -100, Constants.cherryImg);
        getChildren().add(extraScorePowerUp.getPowerUpImageView());

        gameLoop = new AnimationTimer() {
            private long lastEnemySpawnTime, lastExtraLifePoweUpSpawnTime, lastExtraScorePoweUpSpawnTime  = 10;
            private Random random = new Random();
       

            @Override
            public void handle(long now) {
                if (player.getLives() == 0) {
                    stopGame();
                }
            	if (isGameOver) {
                    getChildren().addAll(gameOverText, instrcutionsText);
            		this.stop();
            	}
                spawnEnemy(now);
                spawnPowerUps(now);
                moveEntities();
                extraLifePowerUp.use(player);
                extraScorePowerUp.use(player);
                livesText.setText("Lives: " + player.getLives());
            	scoreText.setText("Score: " + player.getScore());
            }

            private void spawnEnemy(long now) {
                if (now - lastEnemySpawnTime >= 3000000000L - player.getScore() * 10000000) {                
                    double enemyX = random.nextDouble() * (Constants.screenWidth - Constants.enemyWidth);
                    double enemyY = 0;
                    if (random.nextInt(10) + 1 > 5) {
                    	Enemy enemy = new Enemy(enemyX, enemyY, enemyImg);
                    	getChildren().add(enemy.getEnemyImageView());
                    	enemies.add(enemy);
                    } else {
                    	EnemyShooter enemy = new EnemyShooter(enemyX, enemyY, enemyShooterImg);
                    	getChildren().addAll(enemy.getEnemyImageView(), enemy.getLaserBeam().getLaserBeamView()); 
                    	enemieShooters.add(enemy);
                    }
                       
                    lastEnemySpawnTime = now;
                    
                }
            }
            
            
            private void spawnPowerUps(long now) {
                if (now - lastExtraLifePoweUpSpawnTime >= 30000000000L && player.getLives() < 3) {                
                    double powerUpX = random.nextDouble() * (Constants.screenWidth - 40);
                    double powerUpY = random.nextDouble((Constants.screenHeight-40)-(Constants.screenHeight/2)) + Constants.screenHeight/2;
                    extraLifePowerUp.getPowerUpImageView().setX(powerUpX);
                    extraLifePowerUp.getPowerUpImageView().setY(powerUpY);
                    lastExtraLifePoweUpSpawnTime = now;
                    
                }
                
                if (now - lastExtraScorePoweUpSpawnTime >= 30000000000L && player.getScore() >= 20) {                
                    double powerUpX = random.nextDouble() * (Constants.screenWidth - 40);
                    double powerUpY = random.nextDouble((Constants.screenHeight-40)-(Constants.screenHeight/2)) + Constants.screenHeight/2;
                    extraScorePowerUp.getPowerUpImageView().setX(powerUpX);
                    extraScorePowerUp.getPowerUpImageView().setY(powerUpY);
                    lastExtraScorePoweUpSpawnTime = now;
                    
                }
                	
            }
            

            private void moveEntities() {

            	
                player.moveLaserBeam();
                
                // Temporary variables
                Enemy enemyTmp = new Enemy(-100, -100, Constants.enemyImg);
                LaserBeam laserBeamTmp = new LaserBeam(-100, -100, 0);
                               
                // Enemies
                for (Enemy enemy : enemies) {
                    enemy.move();
                    
                    if (player.enemyHit(enemy)) {
                    	getChildren().remove(enemy.getEnemyImageView());
                    	enemyTmp = enemy;
                    }
                    
                    enemyTmp = collisions(enemy, enemyTmp);
                }               
                enemies.remove(enemyTmp);
                
                
                // Enemy Shooters
                for (EnemyShooter enemyShooter : enemieShooters) {
                	enemyShooter.move();
                	enemyShooter.shootLaserBeam();
                	enemyShooter.moveLaserBeam();
                                        
                    if (player.enemyHit(enemyShooter)) {
                    	getChildren().removeAll(enemyShooter.getEnemyImageView());
                    	laserBeams.add(enemyShooter.getLaserBeam());
                    	enemyTmp = enemyShooter;
                    	
                    }
                    
                    playerHit(enemyShooter);
                    enemyTmp = collisions(enemyShooter, enemyTmp);
                 
                                      
                }
                enemieShooters.remove(enemyTmp);
                
                // Laserbeams
                for (LaserBeam laserBeam : laserBeams) {
                	laserBeam.moveLaserBeam();
                	
                	if (laserBeam.hit(player.getPlayerImageView().getX(), player.getPlayerImageView().getX()+Constants.playerWidth, player.getPlayerImageView().getY(), player.getPlayerImageView().getY()-Constants.playerHeight)) {
                		player.setLives(player.getLives()-1);
                		laserBeamTmp = laserBeam;
                		getChildren().remove(laserBeam.getLaserBeamView());
                	}
                	
                	if (laserBeam.getLaserBeamView().getY() >= 420) {
                		laserBeamTmp = laserBeam;
                		getChildren().remove(laserBeam.getLaserBeamView());
                	}
                }
                laserBeams.remove(laserBeamTmp);   
                
            }
        };

    }
    
    public Enemy collisions(Enemy enemy, Enemy enemyTmp) {        
        if (enemy.collidesWith()) {
        	getChildren().remove(enemy.getEnemyImageView());
        	enemyTmp = enemy;
        	player.setLives(player.getLives()-1);
        }
        
        return enemyTmp;
    }
    
    public void playerHit(EnemyShooter enemyShooter) {
        if (enemyShooter.playerHit(player)) {
        	player.setLives(player.getLives()-1);
        }
    }
   
    
    public void handleKeyPress(KeyCode keyCode) {        
        player.move(keyCode);
    }

    private Text createText(double x, double y, String text, int size) {
        Text t = new Text(x, y, text);
        t.setFont(Font.font("Arial", size));
        t.setFill(Color.WHITE);
        return t;
    }
    
    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        isGameOver = true;
        HighScore highScore = new HighScore();
        highScore.saveScore(player.getScore());
    }
    
}