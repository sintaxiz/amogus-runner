package ru.ccfit.nsu.kokunina.view.gameElements.enemies;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AmogusEnemy implements Enemy {

    private static final Logger log = LoggerFactory.getLogger(AmogusEnemy.class);

    private final ImageView amogusView;
    private final Rectangle rectangle;

    private final int ENEMY_SPEED = 10;

    public AmogusEnemy(AmogusType amogusType) {
        if (amogusType == AmogusType.SMALL) {
            amogusView = new ImageView("/img/amogus.png");
        } else {
            amogusView = new ImageView("/img/manyAmogus.png");
        }
        rectangle = new Rectangle();
        rectangle.setWidth(amogusView.getImage().getWidth() - 10);
        rectangle.setHeight(amogusView.getImage().getHeight() - 10);
    }

    @Override
    public void update() {
        amogusView.setX(amogusView.getX() - ENEMY_SPEED);

        rectangle.setX(amogusView.getX());
        rectangle.setY(amogusView.getY() + 200);
    }

    @Override
    public void draw(Pane pane) {
        pane.getChildren().add(amogusView);
    }

    @Override
    public Rectangle getBound() {
        return rectangle;
    }

    @Override
    public boolean isOutOfScreen() {
        return (amogusView.getX() + amogusView.getImage().getWidth() < 0);
    }

    public void setX(double X) {
        amogusView.setX(X);
    }
}
