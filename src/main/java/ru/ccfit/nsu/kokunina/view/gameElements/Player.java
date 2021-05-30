package ru.ccfit.nsu.kokunina.view.gameElements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ccfit.nsu.kokunina.view.Animation;
import ru.ccfit.nsu.kokunina.view.AudioManager;

public class Player {
    static private final Logger log = LoggerFactory.getLogger(Player.class);

    private double GROUNDY;

    private final ImageView player;
    private double X;
    private double Y;
    private double width;
    private double height;
    private double speedY = 0;
    static private final double GRAVITY = 0.3f;
    static private final double SPEED_Y = -7;

    private final Rectangle bounds;


    private final Animation runAnimation;
    static private final int RUN_FRAME_RATE = 15;
    private static final String HERO_IMAGE_LOC = "img/main-character1.png";
    private static final String DEAD_HERO_LOC = "img/dead.png";

    private boolean inJump = false;

    public Player(double GROUNDY) {
        this.GROUNDY = GROUNDY;

        player = new ImageView(new Image(HERO_IMAGE_LOC));
        X = player.getX();
        Y = player.getY();

        Y = GROUNDY;

        height = player.getImage().getHeight();
        width = player.getImage().getWidth();

        runAnimation = new Animation(RUN_FRAME_RATE);
        runAnimation.addFrame(new Image("img/main-character1.png"));
        runAnimation.addFrame(new Image("img/main-character2.png"));
        runAnimation.addFrame(new Image("img/main-character3.png"));
        runAnimation.addFrame(new Image("img/main-character4.png"));

        bounds = new Rectangle();
        bounds.setWidth(player.getImage().getWidth() - 10);
        bounds.setHeight(player.getImage().getHeight() - 10);

    }

    public void jump() {
        // check for disabling double jump
        if (!inJump) {
            inJump = true;
            speedY = SPEED_Y;
            Y += speedY;
        }
        update();
    }

    public void update() {
        if (Y > GROUNDY - 1) { // touch the ground
            inJump = false;
            speedY = 0;
            Y = GROUNDY;
        } else {
            speedY += GRAVITY;
            Y += speedY;

        }

        player.setX(X);
        player.setY(Y);

        bounds.setX(X);
        bounds.setY(Y);

        player.setImage(runAnimation.getFrame());
        runAnimation.update();
    }

    public Rectangle getBound() {
        return bounds;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getHeight() {
        return height;
    }

    public ImageView getImageView() {
        return player;
    }

    public void setGameOver() {
        //player.setImage(new Image(DEAD_HERO_LOC));
        player.setImage(new Image("/img/dead2.png"));
    }
}
