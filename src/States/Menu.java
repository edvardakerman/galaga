package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends VBox {

    private Button startButton, exitButton, switchGameModeButton;
    private Text highScoreText;
    private ImageView galagaImageView;
    private Runnable onStartGame;
    HighScore highScore = new HighScore();
    private String gameMode = "classicMode";
    
    public Menu() {
        setStyle(Constants.Blackbackground);
        setSpacing(20);
        setAlignment(Pos.CENTER);
        
		Image galagaLogoImage;
		try {
			galagaLogoImage = new Image(new FileInputStream(Constants.galagaLogoImg));
			galagaImageView = new ImageView(galagaLogoImage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        highScoreText = new Text("HighScore: " + highScore.getHighScore());
        highScoreText.setFont(Font.font(Constants.font, 15));
        highScoreText.setFill(Color.WHITE);

        startButton = createButton("Start Game", 20);
        startButton.setOnAction(event -> {
            if (onStartGame != null) {
                onStartGame.run();
            }
        });
        
        switchGameModeButton = createButton("Classic", 15);
        switchGameModeButton.setOnAction(event -> {
        	switchGameMode();
        });
        
        exitButton = createButton("Exit", 15);
        exitButton.setOnAction(event -> {
        	System.exit(0);
        });

        getChildren().addAll(galagaImageView, startButton, switchGameModeButton, exitButton, highScoreText);
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }
    
    private void switchGameMode() {
    	if (gameMode == "classicMode") {
    		gameMode = "specialMode";
    		setStyle(Constants.Bluebackground);
    		switchGameModeButton.setText("Special");
    	} else if (gameMode == "specialMode") {
    		gameMode = "classicMode";
    		switchGameModeButton.setText("Classic");
    		setStyle(Constants.Blackbackground);
    	}
    }
    
    private Button createButton(String text, int size) {
        Button btn = new Button(text);
        btn.setFont(Font.font(Constants.font, size));
        btn.setStyle("-fx-focus-color: transparent;");
        return btn;
    }

	public String getGameMode() {
		return gameMode;
	}
	
	public Text gethighScoreText() {
		return highScoreText;
	}
}
