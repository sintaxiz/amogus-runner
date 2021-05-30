package ru.ccfit.nsu.kokunina.view.gameElements.enemies;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public interface Enemy {
    void update();
    void draw(Pane pane);
    Rectangle getBound();
    boolean isOutOfScreen();
}
