package ru.ccfit.nsu.kokunina.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ccfit.nsu.kokunina.view.gameElements.Ground;
import ru.ccfit.nsu.kokunina.view.gameElements.Player;
import ru.ccfit.nsu.kokunina.view.gameElements.enemies.EnemiesManager;
import ru.ccfit.nsu.kokunina.view.gameElements.enemies.Enemy;


public class GameView {

    private static final Logger log = LoggerFactory.getLogger(GameView.class);

    private final double W;
    private double H;

    private Scene mainScene;
    private Pane stackPane;
    private Player player;
    private EnemiesManager enemiesManager;
    private Ground ground;
    private Text scoreCounter;
    private Text gameOverText;
    private int count;
    private boolean isGameOver = false;
    private boolean gameStarted = false;

    private final String GRAY_COLOR_CODE = "f7f7f7";
    private final String FONT_LOC = "/fonts/amogusFont.ttf";
    private final String GAME_OVER_STRING = "GAME OVER";

    public GameView(double h, double w) {
        H = h;
        W = w;
    }

    public void setup() {
        AudioManager.playBackgroundMusic();
        initScoreCounter();
        initGameOverText();

        player = new Player(H / 2);
        player.setX(W / 6);
        player.setY(H / 2);
        player.getImageView().setX(W / 6);
        player.getImageView().setY(H / 2);

        Pane groundPane = new Pane();
        groundPane.setLayoutX(0);
        groundPane.setLayoutY(2 * (H / 3) - player.getHeight() / 3);
        ground = new Ground(groundPane, W);
        ground.draw();

        Pane enemyPane = new Pane();
        enemyPane.setLayoutX(0);
        enemyPane.setLayoutY(groundPane.getLayoutY() - ground.getHeight());
        enemiesManager = new EnemiesManager(enemyPane, W);
        enemiesManager.draw();

        stackPane = new Pane(groundPane, enemyPane, player.getImageView(), scoreCounter, gameOverText);
        mainScene = new Scene(stackPane, W, H);
        mainScene.setFill(Color.valueOf(GRAY_COLOR_CODE));

        mainScene.setOnKeyPressed(e -> {
            gameStarted = true;
            player.jump();
            mainScene.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    AudioManager.playJumpSound();
                    player.jump();
                }
                if (keyEvent.getCode() == KeyCode.M) {
                    AudioManager.muteBackgroundMusic();
                }
                if (keyEvent.getCode() == KeyCode.U) {
                    AudioManager.unmuteBackgroundMusic();
                }
            });
        });

    }

    private void initScoreCounter() {
        scoreCounter = new Text("Score: 0");
        count = 0;
        Font amogusFontForScore = Font.loadFont(GameView.class.getResourceAsStream(FONT_LOC), 35);
        scoreCounter.setFont(amogusFontForScore);
        scoreCounter.setLayoutX(6 * W / 7);
        scoreCounter.setLayoutY(H / 13);
    }

    private void initGameOverText() {
        gameOverText = new Text(GAME_OVER_STRING);
        Font amogusFontForGameOver = Font.loadFont(GameView.class.getResourceAsStream(FONT_LOC), 100);
        gameOverText.setFont(amogusFontForGameOver);
        gameOverText.setLayoutX(W / 2 - 150);
        gameOverText.setLayoutY(H / 2);
        gameOverText.setVisible(false);
    }

    public void update() {
        if (!gameStarted) {
            return;
        }
        if (!isGameOver) {
            player.update();
            ground.update();
            enemiesManager.update();
            count++;
            scoreCounter.setText("Score: " + count);
        }
    }

    public Scene getScene() {
        return mainScene;
    }

    public boolean haveCollision() {
        for (Enemy enemy : enemiesManager.getEnemies()) {
            if (Shape.intersect(enemy.getBound(), player.getBound()).getLayoutBounds().getWidth() != -1) {
                log.info("Collision of player with enemy {} detected", enemy);
                return true;
            }
        }
        return false;
    }

    public void gameOver() {
        player.setGameOver();
        gameOverText.setVisible(true);
        AudioManager.playDeadSound();
        mainScene.setOnKeyPressed(e -> {
        });
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}
