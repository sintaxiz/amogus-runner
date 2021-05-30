package ru.ccfit.nsu.kokunina.view;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Animation {
    private final List<Image> frames;
    private int frameIndex = 0;
    private final long delay;
    private long prevTimeMillis;

    public Animation(int frameRate) {
        this.delay = 1000 / frameRate; // time in ms on one frame
        prevTimeMillis = System.currentTimeMillis();
        frames = new ArrayList<>();
    }

    public void update() {
        if (System.currentTimeMillis() - prevTimeMillis > delay) {
            if (frameIndex >= frames.size() - 1) {
                frameIndex = 0;
            } else {
                frameIndex++;
            }
            prevTimeMillis = System.currentTimeMillis();
        }

    }

    public void addFrame(Image frame) {
        frames.add(frame);
    }

    public Image getFrame() {
        if (frames.isEmpty()) {
            return null;
        }
        return frames.get(frameIndex);
    }
}
