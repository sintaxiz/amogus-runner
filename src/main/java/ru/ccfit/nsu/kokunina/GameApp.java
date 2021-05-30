package ru.ccfit.nsu.kokunina;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ccfit.nsu.kokunina.controller.GameController;
import ru.ccfit.nsu.kokunina.view.GameView;


public class GameApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(GameApp.class);


    private static final double H = 400;
    private static final double W = 800;

    private static final String ICON_LOC = "/img/main-character1.png";
    private static final String TITLE = "Amogus Runner";

    @Override
    public void start(Stage mainStage) throws InterruptedException {

        GameView gameView = new GameView(H, W);
        gameView.setup();

        // Setup main window
        mainStage.setResizable(false);
        mainStage.setWidth(W);
        mainStage.setHeight(H);
        mainStage.setOnCloseRequest(event -> System.exit(0));
        mainStage.setTitle(TITLE);
        mainStage.setScene(gameView.getScene());
        mainStage.getIcons().add(new Image(ICON_LOC));
        mainStage.show();

        GameModel gameModel = new GameModel(gameView);
        GameController gameController = new GameController(gameView, gameModel);

        // game controller update game by timer, "game loop"
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameController.isGameOver()) {
                    gameController.updateGameState();
                }
            }
        };
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}