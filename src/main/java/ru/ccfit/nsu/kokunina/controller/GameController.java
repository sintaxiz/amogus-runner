package ru.ccfit.nsu.kokunina.controller;

import ru.ccfit.nsu.kokunina.GameModel;
import ru.ccfit.nsu.kokunina.view.GameView;

public class GameController {
    private final GameView view;
    private final GameModel model;

    public GameController(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public void updateGameState() {
        if (view.isGameStarted()) {
            view.update();
            // controller notify model about collision
            if (view.haveCollision()) {
                model.collisionDetected();
            }
        }
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }
}
