package Main;
import Constants.Constants;
import States.Game;
import States.HighScore;
import States.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene menuScene;
    private Scene gameScene;
    private Game game;
    HighScore highScore = new HighScore();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        createMenuScene();

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Galaga!");
        primaryStage.show();
    }
    

    private void createMenuScene() {    	
        Menu menu = new Menu();
        menu.setOnStartGame(() -> {

        	createGameScene(menu.getGameMode());
            primaryStage.setScene(gameScene);
            
            game.startGame();
            
            gameScene.setOnKeyPressed(event -> {
            	  if (event.getCode() == KeyCode.ESCAPE) {
            		  	game.stopGame();
            	        primaryStage.setScene(menuScene);
            	        menu.gethighScoreText().setText("HighScore: " + highScore.getHighScore());
            	  } else {
            		  game.handleKeyPress(event.getCode());
            	  }
            });
            	   
            	  
                       
        });

        menuScene = new Scene(menu, Constants.screenWidth, Constants.screenHeight);
    }

    private void createGameScene(String gameMode) {   	
        game = new Game(gameMode);
        gameScene = new Scene(game, Constants.screenWidth, Constants.screenHeight);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
