package ru.ccfit.nsu.kokunina.view.gameElements.enemies;

import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemiesManager {
    private static final Logger log = LoggerFactory.getLogger(EnemiesManager.class);
    private List<Enemy> enemies;
    private final Pane graphics;
    private final double screenWidth;

    private Random random;

    public EnemiesManager(Pane graphics, double screenWidth) {
        this.graphics = graphics;
        this.screenWidth = screenWidth;
        enemies = new ArrayList<>();
        random = new Random();

        AmogusEnemy amogusEnemy = new AmogusEnemy(AmogusType.SMALL);
        amogusEnemy.setX(screenWidth);
        enemies.add(amogusEnemy);
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }

        Enemy leftEnemy = enemies.get(0);
        if (leftEnemy.isOutOfScreen()) {
            enemies.remove(leftEnemy);
            AmogusEnemy newEnemy = getRandomEnemy();
            enemies.add(newEnemy);
            newEnemy.draw(graphics);
        }

    }

    public void draw() {
        for (Enemy enemy : enemies) {
            enemy.draw(graphics);
        }
    }
    private AmogusEnemy getRandomEnemy() {
        AmogusEnemy amogusEnemy;
        if (random.nextBoolean()) {
            amogusEnemy = new AmogusEnemy(AmogusType.SMALL);
        } else {
            amogusEnemy = new AmogusEnemy(AmogusType.BIG);
        }
        double dispositionOutOfScreen = random.nextInt(200) + amogusEnemy.getBound().getWidth();
        amogusEnemy.setX(screenWidth + dispositionOutOfScreen);
        return amogusEnemy;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
