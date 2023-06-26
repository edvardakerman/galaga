package Constants;


public interface Constants {	
	
	// Player
	String playerImg = "src/Public/player.png";
	double playerWidth = 40;
    double playerHeight = 40;
    double playerSpeed = 10;
    double laserSpeed = 6;
	
	// Enemy
    String enemyImg = "src/Public/enemy.png";
    String enemyShooterImg = "src/Public/enemyShooter.png";
    double enemyWidth = 40;
    double enemyHeight = 40;
    double enemySpeed = 0.5;
    
    // Specials
    String specialPlayerImg = "src/Public/specialPlayer.png";
    String specialEnemyImg = "src/Public/specialEnemy.png";
    String specialEnemyShooterImg = "src/Public/specialEnemyShooter.png";
	
	// LaserBeam
    String laserImg = "src/Public/laser.png";
    double laserWidth = 10;
    double laserHeight = 40;
    
    
    //PowerUps
	String heartImg = "src/Public/heart.png";
	String cherryImg = "src/Public/cherry.png";
    double powerUpWidth = 40;
    double powerUpHeight = 40;
    
	// Screen
    final double screenWidth = 400;
    final double screenHeight = 400;
    
    // Other
    String galagaLogoImg = "src/Public/galagaLogo.png";
    String font = "Impact";
    
    // Colors
    String Bluebackground = "-fx-background-color: #34495E;";
    String Blackbackground = "-fx-background-color: #111111;";
}
