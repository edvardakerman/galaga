package States;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends VBox {

    private Button startButton, exitButton, switchGameModeButton;
    private Text welcomeText, highScoreText;
    private Runnable onStartGame;
    HighScore highScore = new HighScore();
    private String gameMode = "classicMode";
    
    public Menu() {
        setStyle("-fx-background-color: #222222;");
        setSpacing(20);
        setAlignment(Pos.CENTER);
        
        welcomeText = new Text("Welcome to Galactica!!");
        welcomeText.setFont(Font.font("Arial", 18));
        welcomeText.setFill(Color.WHITE);
        
        highScoreText = new Text("HighScore: " + highScore.getHighScore());
        highScoreText.setFont(Font.font("Arial", 18));
        highScoreText.setFill(Color.WHITE);

        startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            if (onStartGame != null) {
                onStartGame.run();
            }
        });
        
        switchGameModeButton = new Button("Switch Game Mode");
        switchGameModeButton.setOnAction(event -> {
        	if (gameMode == "classicMode") {
        		gameMode = "specialMode";
        	} else if (gameMode == "specialMode") {
        		gameMode = "classicMode";
        	}
        });
        
        exitButton = new Button("Exit Game");
        exitButton.setOnAction(event -> {
        	System.exit(0);
        });

        getChildren().addAll(welcomeText, startButton, switchGameModeButton, exitButton, highScoreText);
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }

	public String getGameMode() {
		return gameMode;
	}
	
	public Text gethighScoreText() {
		return highScoreText;
	}
}
