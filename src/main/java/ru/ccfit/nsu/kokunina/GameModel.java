package ru.ccfit.nsu.kokunina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ccfit.nsu.kokunina.view.GameView;
import ru.ccfit.nsu.kokunina.view.gameElements.Player;
import ru.ccfit.nsu.kokunina.view.gameElements.enemies.EnemiesManager;

public class GameModel {
    private static final Logger log = LoggerFactory.getLogger(GameModel.class);

    private Player player;
    private EnemiesManager enemiesManager;
    private final GameView view;

    private boolean isGameOver = false;

    public GameModel(GameView view) {
        this.view = view;
    }

    public void collisionDetected() {
        view.gameOver();
        isGameOver = true;
        log.debug("Game is over");
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
