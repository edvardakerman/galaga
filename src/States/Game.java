package States;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constants.Constants;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game handles the game including the player and enemies.
 * 
 * The class uses an animationTimer to handle graphics and time sensitive
 * changes. This includes spawning, removing and moving objects.
 */

public class Game extends Pane {

	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<EnemyShooter> enemieShooters = new ArrayList<EnemyShooter>();
	private ArrayList<LaserBeam> laserBeams = new ArrayList<LaserBeam>();
	private Text scoreText, livesText, gameOverText, instrcutionsText;
	private boolean isGameOver = false;
	private AnimationTimer gameLoop;

	public Game(GameMode gameMode) {

		setPrefSize(Constants.screenWidth, Constants.screenHeight);
		setStyle(gameMode.getBackgroundColor());

		player = new Player(gameMode.getPlayerImg());
		getChildren().addAll(player.getPlayerImageView(), player.getLaserBeam().getLaserBeamView());

		gameOverText = createText(Constants.screenWidth / 2 - 50, Constants.screenHeight / 2 - 30, "Game Over", 20);
		instrcutionsText = createText(Constants.screenWidth / 2 - 70, Constants.screenHeight / 2, "Press ESC for menu",
				15);

		livesText = createText(10, 20, "Lives: " + player.getLives(), 18);
		scoreText = createText(10, 50, "Score: " + player.getScore(), 18);
		getChildren().addAll(livesText, scoreText);

		ExtraLifePowerUp extraLifePowerUp = new ExtraLifePowerUp(-100, -100, Constants.heartImg);
		getChildren().add(extraLifePowerUp.getPowerUpImageView());

		ExtraScorePowerUp extraScorePowerUp = new ExtraScorePowerUp(-100, -100, Constants.cherryImg);
		getChildren().add(extraScorePowerUp.getPowerUpImageView());

		gameLoop = new AnimationTimer() {
			private long lastEnemySpawnTime, lastExtraLifePoweUpSpawnTime, lastExtraScorePoweUpSpawnTime = 10;
			private Random random = new Random();

			@Override
			public void handle(long now) {
				checkGameStatus();
				if (isGameOver) {
					this.stop();
					getChildren().addAll(gameOverText, instrcutionsText);
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
					if (random.nextInt(10) + 1 > 4) {
						Enemy enemy = new Enemy(enemyX, enemyY, gameMode.getEnemyImg());
						getChildren().add(enemy.getEnemyImageView());
						enemies.add(enemy);
					} else {
						EnemyShooter enemy = new EnemyShooter(enemyX, enemyY, gameMode.getEnemyShooterImg());
						getChildren().addAll(enemy.getEnemyImageView(), enemy.getLaserBeam().getLaserBeamView());
						enemieShooters.add(enemy);
					}

					lastEnemySpawnTime = now;

				}
			}

			private void spawnPowerUps(long now) {
				if (now - lastExtraLifePoweUpSpawnTime >= 30000000000L && player.getLives() < 3) {
					double powerUpX = random.nextDouble() * (Constants.screenWidth - Constants.powerUpWidth);
					double powerUpY = random.nextDouble(
							(Constants.screenHeight - Constants.powerUpHeight) - (Constants.screenHeight / 2))
							+ Constants.screenHeight / 2;
					extraLifePowerUp.getPowerUpImageView().setX(powerUpX);
					extraLifePowerUp.getPowerUpImageView().setY(powerUpY);
					lastExtraLifePoweUpSpawnTime = now;

				}

				if (now - lastExtraScorePoweUpSpawnTime >= 30000000000L && player.getScore() >= 20) {
					double powerUpX = random.nextDouble() * (Constants.screenWidth - 40);
					double powerUpY = random.nextDouble((Constants.screenHeight - 40) - (Constants.screenHeight / 2))
							+ Constants.screenHeight / 2;
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

					if (player.enemyHit(enemyShooter) || enemyShooter.playerEnemyCollision(player)) {
						getChildren().remove(enemyShooter.getEnemyImageView());
						laserBeams.add(enemyShooter.getLaserBeam());
						enemyTmp = enemyShooter;

					}

					playerHit(enemyShooter);
					enemyTmp = collisions(enemyShooter, enemyTmp);

				}
				enemieShooters.remove(enemyTmp);

				// Game takes over laserbeams without ownership, (enemyships shot down)
				for (LaserBeam laserBeam : laserBeams) {
					laserBeam.moveLaserBeam();

					if (laserBeam.laserShipCollision(player.getPlayerImageView().getX(),
							player.getPlayerImageView().getY(), Constants.playerWidth, Constants.playerHeight)) {
						player.setLives(player.getLives() - 1);
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

	private Enemy collisions(Enemy enemy, Enemy enemyTmp) {
		if (enemy.slipsByPlayer() || enemy.playerEnemyCollision(player)) {
			getChildren().remove(enemy.getEnemyImageView());
			enemyTmp = enemy;
			player.setLives(player.getLives() - 1);
		}

		return enemyTmp;
	}

	private void playerHit(EnemyShooter enemyShooter) {
		if (enemyShooter.playerHitWithEnemyLaser(player)) {
			player.setLives(player.getLives() - 1);
		}
	}

	public void handleKeyPress(KeyCode keyCode) {
		player.move(keyCode);
	}

	private Text createText(double x, double y, String text, int size) {
		Text t = new Text(x, y, text);
		t.setFont(Font.font(Constants.font, size));
		t.setFill(Color.WHITE);
		return t;
	}

	public void startGame() {
		gameLoop.start();
	}

	private void checkGameStatus() {
		if (player.getLives() == 0) {
			stopGame();
		}
	}

	public void stopGame() {
		isGameOver = true;
		HighScore highScore = new HighScore();
		highScore.saveScore(player.getScore());
	}

}