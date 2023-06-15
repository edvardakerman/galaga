package Main;
import States.Game;
import States.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene menuScene;
    private Scene gameScene;
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        createMenuScene();
        createGameScene();

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Game Menu");
        primaryStage.show();
    }
    

    private void createMenuScene() {    	
        Menu menu = new Menu();
        menu.setOnStartGame(() -> {

            primaryStage.setScene(gameScene);
            primaryStage.setTitle("Game");
            game.startGame();      
            gameScene.setOnKeyPressed(event -> game.handleKeyPress(event.getCode()));
                       
        });

        menuScene = new Scene(menu, 400, 400);
    }

    private void createGameScene() {   	
        game = new Game();
        gameScene = new Scene(game, 400, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
