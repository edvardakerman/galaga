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

    private Button startBtn, exitBtn, switchGameModeBtn;
    private Text highScoreText;
    private ImageView galagaImageView;
    private Runnable onStartGame;
    HighScore highScore = new HighScore();
    //private String gameMode = "classicMode";
    GameMode gameMode = new GameMode("Classic");
    
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

        startBtn = createButton("Start Game", 20);
        startBtn.setOnAction(event -> {
            if (onStartGame != null) {
                onStartGame.run();
            }
        });
        
        switchGameModeBtn = createButton("Classic", 15);
        switchGameModeBtn.setOnAction(event -> {
        	switchGameModeBtnAction();
        });
        
        exitBtn = createButton("Exit", 15);
        exitBtn.setOnAction(event -> {
        	System.exit(0);
        });

        getChildren().addAll(galagaImageView, startBtn, switchGameModeBtn, exitBtn, highScoreText);
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }
    
    private void switchGameModeBtnAction() {
    	gameMode.switchGameMode();  	
		setStyle(gameMode.getBackgroundColor());
		switchGameModeBtn.setText(gameMode.getCurrentGameMode());
    }
    
    private Button createButton(String text, int size) {
        Button btn = new Button(text);
        btn.setFont(Font.font(Constants.font, size));
        btn.setStyle("-fx-focus-color: transparent;");
        return btn;
    }

	public GameMode getGameMode() {
		return gameMode;
	}
	
	public Text gethighScoreText() {
		return highScoreText;
	}
}
