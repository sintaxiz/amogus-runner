package ru.ccfit.nsu.kokunina.view;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

public class AudioManager {

    private static MediaPlayer backgroundMusicPlayer;

    public static void playJumpSound() {
        AudioClip jump = new AudioClip(AudioManager.class.getResource("/sound/jump.mp3").toExternalForm());
        jump.play();
    }

    public static void playDeadSound() {
        AudioClip deadSound = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource("/sound/dead.mp3")).toExternalForm());
        deadSound.play();
    }

    public static void playBackgroundMusic() {
        Media backgroundMusic = new Media(AudioManager.class.getResource("/sound/background.mp3").toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.play();
        backgroundMusicPlayer.setOnEndOfMedia(() -> backgroundMusicPlayer.seek(Duration.ZERO));
    }

    public static void muteBackgroundMusic() {
        backgroundMusicPlayer.setMute(true);
    }

    public static void unmuteBackgroundMusic() {
        backgroundMusicPlayer.setMute(false);
    }

    public static void stopBackgroundMusic() {
        backgroundMusicPlayer.stop();
    }
}
