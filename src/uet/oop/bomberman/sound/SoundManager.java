package uet.oop.bomberman.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class SoundManager {
    public static final String mainMusicFilepath = "res/newSound/theme_song.mp3";
    private static SoundManager soundManager;
    private ArrayList<MediaPlayer> musicList;
    private ArrayList<MediaPlayer> soundList;

    public static SoundManager getSoundManager() {
        if (soundManager == null) {
            soundManager = new SoundManager();
        }
        return soundManager;
    }

    private SoundManager() {
        musicList = new ArrayList<>();
        soundList = new ArrayList<>();
    }

    public void addMusic(String filepath, int totalCycles) {
        Media sound = new Media(new File(filepath).toURI().toString());
        musicList.add(new MediaPlayer(sound));
        musicList.get(musicList.size() - 1).setCycleCount(totalCycles);
    }

    public void addMusicInfinite(String filepath) {
        addMusic(filepath, INDEFINITE);
    }

    public void addSound(String filepath) {
        Media sound = new Media(new File(filepath).toURI().toString());
        soundList.add(new MediaPlayer(sound));
    }

    public void play() {
        for (MediaPlayer music : musicList) {
            music.play();
        }
        for (MediaPlayer sound : soundList) {
            sound.play();
        }
    }

    public void update() {
        for (MediaPlayer music : musicList) {
            music.setOnEndOfMedia(() -> {
                music.dispose();
                musicList.remove(music);
            });
        }

        for (MediaPlayer sound : soundList) {
            sound.setOnEndOfMedia(() -> {
                sound.dispose();
                soundList.remove(sound);
            });
        }
    }

    public void stop() {
        for (MediaPlayer music : musicList) {
            music.stop();
        }
    }


    public void setMasterVolume(double volume) {
        for (MediaPlayer music : musicList) {
            music.setVolume(volume);
        }

        for (MediaPlayer sound : soundList) {
            sound.setVolume(volume);
        }
    }

    public void setMusicVolume(double volume) {
        for (MediaPlayer music : musicList) {
            music.setVolume(volume);
        }
    }

    public void setSoundVolume(double volume) {
        for (MediaPlayer sound : soundList) {
            sound.setVolume(volume);
        }
    }
}
