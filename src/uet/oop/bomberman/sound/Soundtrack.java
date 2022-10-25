//package uet.oop.bomberman.sound;
//
//import javafx.scene.media.AudioClip;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//
//import java.io.File;
//
//public class Soundtrack {
//    private MediaPlayer ending = null;
//    private AudioClip explosion = null;
//    private AudioClip nextLevel = null;
//    private AudioClip placeBomb = null;
//    private AudioClip playerDie = null;
//    private AudioClip powerUp = null;
//    private AudioClip justDie = null;
//    private AudioClip levelDone = null;
//    private AudioClip takeItem = null;
//    private static AudioClip switchButton = null;
//    private MediaPlayer themeSong = null;
//    private MediaPlayer stageStart = null;
//    private MediaPlayer[] levelThemeAt = new MediaPlayer[5];
//    private int playTheme = 0;
//
//    public Soundtrack() {
//        initializeSoundtrack();
//    }
//
//    public void initializeSoundtrack() {
//        ending = new MediaPlayer(new Media(new File("res/Sounds/ending.wav").toURI().toString()));
//        explosion = new AudioClip(new File("res/Sounds/explosion.wav").toURI().toString());
//        nextLevel = new AudioClip(new File("res/Sounds/next_level.wav").toURI().toString());
//        placeBomb = new AudioClip(new File("res/Sounds/place_bomb.wav").toURI().toString());
//        playerDie = new AudioClip(new File("res/Sounds/player_die.wav").toURI().toString());
//        powerUp = new AudioClip(new File("res/Sounds/powerup.wav").toURI().toString());
//        themeSong = new MediaPlayer(new Media(new File("res/newSound/theme_song.mp3").toURI().toString()));
//        stageStart = new MediaPlayer(new Media(new File("res/Sounds/stage_start.wav").toURI().toString()));
//        levelThemeAt[3] = new MediaPlayer(new Media(new File("res/Sounds/level_theme1.mp3").toURI().toString()));
//        levelThemeAt[2] = new MediaPlayer(new Media(new File("res/Sounds/level_theme2.mp3").toURI().toString()));
//        levelThemeAt[1] = new MediaPlayer(new Media(new File("res/newSound/wibu.mp3").toURI().toString()));
//        levelThemeAt[4] = new MediaPlayer(new Media(new File("res/Sounds/level_theme1.mp3").toURI().toString()));
//        justDie = new AudioClip(new File("res/Sounds/died.mp3").toURI().toString());
//        levelDone = new AudioClip(new File("res/Sounds/level_done.mp3").toURI().toString());
//        takeItem = new AudioClip(new File("res/Sounds/cddata_00325.wav").toURI().toString());
//        switchButton = new AudioClip(new File("res/Sounds/button.mp3").toURI().toString());
//
//        explosion.setVolume(0.1);
//        placeBomb.setVolume(0.3);
//        for (int i = 1; i <= 4; i++) {
//            levelThemeAt[i].setVolume(0.1);
//        }
//        justDie.setVolume(0.4);
//        levelDone.setVolume(0.5);
//        switchButton.setVolume(0.1);
//    }
//
//    public void playTakeItem() {
//        takeItem.play();
//    }
//
//    public void playJustDie() {
//        justDie.play();
//    }
//
//    public void playLevelDone() {
//        levelDone.play();
//    }
//
//    public void playEnding() {
//        ending.play();
//    }
//
//    public void playExplosion() {
//        explosion.play();
//    }
//
//    public void playNextLevel() {
//        nextLevel.play();
//    }
//
//    public void playPlaceBomb() {
//        placeBomb.setVolume(0.3);
//        placeBomb.play();
//    }
//
//    public void playPlayerDie() {
//        playerDie.play();
//    }
//
//    public void playPowerUp() {
//        powerUp.play();
//    }
//
//    public void playThemeSong() {
//        themeSong.play();
//    }
//
//    public void playStageStart() {
//        stageStart.play();
//    }
//
//    public void stopPreviousTheme(int level) {
//        if (level >= 2) {
//            levelThemeAt[level - 1].stop();
//        }
//    }
//
//    public void stopLevelThemeAt(int level) {
//        levelThemeAt[level].stop();
//    }
//
//    public void playLevelThemeAt(int level) {
//        stopPreviousTheme(level);
//        if (playTheme == 0) {
//            playTheme = 1;
//            levelThemeAt[level].play();
//        }
//
//        stageStart.stop();
//    }
//
//    public static void playSwitchButtonSound() {
//        switchButton.play();
//    }
//
//    public void setSoundVolume(double volume) {
//        explosion.setVolume(0.1 * volume);
//        placeBomb.setVolume(0.3 * volume);
//        justDie.setVolume(0.4 * volume);
//        levelDone.setVolume(0.5 * volume);
//        switchButton.setVolume(0.1 * volume);
//        nextLevel.setVolume(volume);
//        playerDie.setVolume(volume);
//        powerUp.setVolume(volume);
//        takeItem.setVolume(volume);
//    }
//
//    public void setMusicVolume(double volume) {
//        ending.setVolume(volume);
//        for (int i = 1; i <= 4; i++) {
//            levelThemeAt[i].setVolume(0.1 * volume);
//        }
//        themeSong.setVolume(volume);
//        stageStart.setVolume(volume);
//    }
//}
//
