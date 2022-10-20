package uet.oop.bomberman.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Soundtrack {

    private MediaPlayer ending = null;
    private AudioClip explosion = null;
    private AudioClip nextLevel = null;
    private AudioClip placeBomb = null;
    private AudioClip playerDie = null;
    private AudioClip powerUp = null;
    private AudioClip justDie = null;
    private AudioClip levelDone = null;
    private AudioClip takeItem = null;

    private MediaPlayer themeSong = null;
    private MediaPlayer stageStart = null;
    private MediaPlayer firstLevelTheme = null;

    private MediaPlayer secondLevelTheme = null;

    private MediaPlayer[] levelThemeAt = new MediaPlayer[5];
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
        levelThemeAt[1] = new MediaPlayer(new Media(new File("res/Sounds/level_theme1.mp3").toURI().toString()));
        levelThemeAt[2] = new MediaPlayer(new Media(new File("res/Sounds/level_theme2.mp3").toURI().toString()));
        justDie = new AudioClip(new File("res/Sounds/died.mp3").toURI().toString());
        levelDone = new AudioClip(new File("res/Sounds/level_done.mp3").toURI().toString());
         takeItem = new AudioClip(new File("res/Sounds/cddata_00325.wav").toURI().toString());

    }

    public void playTakeItem() {
        takeItem.setVolume(1);
        takeItem.play();
    }

    public void playJustDie() {
        justDie.setVolume(0.4);
        justDie.play();
    }

    public void playLevelDone(){
        levelDone.setVolume(0.5);
        levelDone.play();
    }

    public void playEnding() {
        ending.play();
    }

    public void playExplosion() {
        explosion.setVolume(0.1);
        explosion.play();
    }

    public void playNextLevel() {
        nextLevel.play();
    }

    public void playPlaceBomb() {
        placeBomb.setVolume(0.3);
        placeBomb.play();
    }

    public void playPlayerDie() {
        playerDie.play();
    }

    public void playPowerUp() {
        powerUp.play();
    }

    public void playThemeSong() {
        themeSong.play();
    }

    public void playStageStart() {
        stageStart.setVolume(60);
        stageStart.play();
    }

    public void stopPreviousTheme(int level) {
        if (level >= 2) {
            levelThemeAt[level-1].stop();
        }
    }

    public void stopLevelThemeAt(int level) {
        levelThemeAt[level].stop();
    }

    public void playLevelThemeAt(int level) {
        stopPreviousTheme(level);
        if (playTheme == 0) {
            playTheme =1 ;
            levelThemeAt[level].setVolume(0.5);
            levelThemeAt[level].play();
        }

        stageStart.stop();
    }
}

