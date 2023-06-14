import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Menu extends VBox {

    private Button startButton;
    private Runnable onStartGame;

    public Menu() {
        setStyle("-fx-background-color: #222222;");
        setSpacing(20);

        startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            if (onStartGame != null) {
                onStartGame.run();
            }
        });

        getChildren().addAll(startButton);
    }

    public void setOnStartGame(Runnable onStartGame) {
        this.onStartGame = onStartGame;
    }
}
