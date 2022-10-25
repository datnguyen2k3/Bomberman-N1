package uet.oop.bomberman.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Soundtrack {
    public static double musicVolume = 1;
    public static double soundVolume = 1;
    private MediaPlayer ending = null;
    private AudioClip explosion = null;
    private AudioClip nextLevel = null;
    private AudioClip placeBomb = null;
    private AudioClip playerDie = null;
    private AudioClip powerUp = null;
    private AudioClip justDie = null;
    private AudioClip levelDone = null;
    private AudioClip takeItem = null;
    private static AudioClip switchButton = null;
    private MediaPlayer themeSong = null;
    private MediaPlayer stageStart = null;
    private MediaPlayer[] levelThemeAt = new MediaPlayer[6];
    private int playTheme = 0;

    public Soundtrack() {
        initializeSoundtrack();
    }

    public void initializeSoundtrack() {
        ending = new MediaPlayer(new Media(new File("res/Sounds/ending.wav").toURI().toString()));
        explosion = new AudioClip(new File("res/Sounds/explosion.wav").toURI().toString());
        nextLevel = new AudioClip(new File("res/Sounds/next_level.wav").toURI().toString());
        placeBomb = new AudioClip(new File("res/Sounds/place_bomb.wav").toURI().toString());
        playerDie = new AudioClip(new File("res/Sounds/player_die.wav").toURI().toString());
        powerUp = new AudioClip(new File("res/Sounds/powerup.wav").toURI().toString());
        themeSong = new MediaPlayer(new Media(new File("res/newSound/theme_song.mp3").toURI().toString()));
        stageStart = new MediaPlayer(new Media(new File("res/Sounds/stage_start.wav").toURI().toString()));
        levelThemeAt[3] = new MediaPlayer(new Media(new File("res/Sounds/level_theme1.mp3").toURI().toString()));
        levelThemeAt[2] = new MediaPlayer(new Media(new File("res/Sounds/level_theme2.mp3").toURI().toString()));
        levelThemeAt[1] = new MediaPlayer(new Media(new File("res/Sounds/level_theme2.mp3").toURI().toString()));
        levelThemeAt[4] = new MediaPlayer(new Media(new File("res/Sounds/level_theme1.mp3").toURI().toString()));
        levelThemeAt[5] = new MediaPlayer(new Media(new File("res/Sounds/level_theme2.mp3").toURI().toString()));

        justDie = new AudioClip(new File("res/Sounds/died.mp3").toURI().toString());
        levelDone = new AudioClip(new File("res/Sounds/level_done.mp3").toURI().toString());
        takeItem = new AudioClip(new File("res/Sounds/cddata_00325.wav").toURI().toString());
        switchButton = new AudioClip(new File("res/Sounds/button.mp3").toURI().toString());

        justDie.setVolume(0.4);
        levelDone.setVolume(0.5);
        switchButton.setVolume(0.1);
    }

    public void playTakeItem() {
        takeItem.setVolume(soundVolume);
        takeItem.play();
    }

    public void playJustDie() {
        justDie.setVolume(soundVolume);
        justDie.play();
    }

    public void playLevelDone() {
        levelDone.setVolume(soundVolume);
        levelDone.play();
    }

    public void playEnding() {
        ending.setVolume(musicVolume);
        ending.play();
    }

    public void playExplosion() {
        explosion.setVolume(soundVolume);
        explosion.play();
    }

    public void playNextLevel() {
        nextLevel.setVolume(soundVolume);
        nextLevel.play();
    }

    public void playPlaceBomb() {
        placeBomb.setVolume(soundVolume);
        placeBomb.play();
    }

    public void playPlayerDie() {
        playerDie.setVolume(soundVolume);
        playerDie.play();
    }

    public void playPowerUp() {
        powerUp.setVolume(soundVolume);
        powerUp.play();
    }

    public void playThemeSong() {
        themeSong.setVolume(musicVolume);
        themeSong.play();
    }

    public void playStageStart() {
        stageStart.setVolume(soundVolume);
        stageStart.play();
    }

    public void stopPreviousTheme(int level) {
        if (level >= 2) {
            levelThemeAt[level - 1].setVolume(musicVolume);
            levelThemeAt[level - 1].stop();
        }
    }

    public void stopLevelThemeAt(int level) {
        levelThemeAt[level].stop();
    }

    public void playLevelThemeAt(int level) {
        stopPreviousTheme(level);
        if (playTheme == 0) {
            playTheme = 1;
            levelThemeAt[level].setVolume(musicVolume);
            levelThemeAt[level].setCycleCount(MediaPlayer.INDEFINITE);
            levelThemeAt[level].play();
        }

        stageStart.stop();
    }

    public static void playSwitchButtonSound() {
        switchButton.setVolume(soundVolume * 0.1);
        switchButton.play();
    }

    public void updateSoundtrack(int level) {
        themeSong.setVolume(musicVolume);
        levelThemeAt[level].setVolume(musicVolume);

        stageStart.setVolume(soundVolume);
        ending.setVolume(soundVolume);
        explosion.setVolume(soundVolume);
        nextLevel.setVolume(soundVolume);
        placeBomb.setVolume(soundVolume);
        playerDie.setVolume(soundVolume);
        justDie.setVolume(soundVolume);
        levelDone.setVolume(soundVolume);
        takeItem.setVolume(soundVolume);
        switchButton.setVolume(soundVolume);
    }
    public static void setMusicVolume(double volume) {
        musicVolume = volume;
    }

    public static void setSoundVolume(double volume) {
        soundVolume = volume;
    }
}

