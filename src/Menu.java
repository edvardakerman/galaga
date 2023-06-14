import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends VBox {

    private Button startButton, exitButton;
    private Text welcomeText;
    private Runnable onStartGame;

    public Menu() {
        setStyle("-fx-background-color: #222222;");
        setSpacing(20);
        
        welcomeText = new Text(180, 50, "Welcome to Galactica!!");
        welcomeText.setFont(Font.font("Arial", 18));
        welcomeText.setFill(Color.WHITE);

        startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            if (onStartGame != null) {
                onStartGame.run();
            }
        });
        
        exitButton = new Button("Exit Game");
        exitButton.setOnAction(event -> {
        	System.exit(0);
        });

        getChildren().addAll(welcomeText, startButton, exitButton);
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }
}
