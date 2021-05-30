package ru.ccfit.nsu.kokunina.view.gameElements;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ground {

    private static final Logger log = LoggerFactory.getLogger(Ground.class);

    private final int GROUND_SPEED = 10;

    private final Image ground1 = new Image("/img/land1.png");
    private final Image ground2 = new Image("/img/land2.png");
    private final Image ground3 = new Image("/img/land3.png");
    private final ArrayList<ImageView> grounds;

    private final Pane groundPane;

    public Ground(Pane groundPane, double width) {
        this.groundPane = groundPane;

        grounds = new ArrayList<>();
        int numbLand = (int) (width / ground1.getWidth()) + 2;
        for (int i = 0; i < numbLand; i++) {
            ImageView ground = null;
            int rand = new Random().nextInt(3);
            switch (rand) {
                case 0 -> ground = new ImageView(ground1);
                case 1 -> ground = new ImageView(ground2);
                case 2 -> ground = new ImageView(ground3);
            }
            ground.setX(i * ground1.getWidth());
            grounds.add(ground);
        }
    }

    public void draw() {
        for (ImageView imageView : grounds) {
            groundPane.getChildren().add(imageView);
        }
    }

    public void update() {
        List<Node> nodes = groundPane.getChildren();
        for (ImageView imageView : grounds) {
            imageView.setX(imageView.getX() - GROUND_SPEED);
        }
        Node firstNode = nodes.get(0);
        ImageView firstNodeImage = (ImageView) firstNode;
        if (firstNodeImage.getX() < groundPane.getLayoutX() - firstNodeImage.getImage().getWidth()) {
            nodes.remove(0);
            ImageView lastGround = (ImageView) nodes.get(nodes.size() - 1);
            firstNodeImage.setX(lastGround.getX() + lastGround.getImage().getWidth());
            nodes.add(firstNodeImage);
        }
    }

    public double getHeight() {
        return ground1.getHeight();
    }
}
