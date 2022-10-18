package uet.oop.bomberman.UI.Menu.animationMenu.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphicsList;
import uet.oop.bomberman.sound.SoundManager;

public class OptionList extends TextGraphicsList {
    private static final String[] options = {"MASTER VOLUME","MUSIC VOLUME", "SOUND VOLUME" ,"RESET HIGHSCORE", "BACK"};
    private static final int defaultVolume = 100;
    private int masterVolume;
    private int musicVolume;
    private int soundVolume;

    public OptionList(int screenWidth, int screenHeight, Scene scene) {
        super(options, screenWidth, screenHeight, scene);
        setVolume(0, defaultVolume);
        setVolume(1, defaultVolume);
        setVolume(2, defaultVolume);
    }

    private void setVolume(int type, int volume) {
        String volumeString = "";
        if (type == 0) {
            //MASTER VOLUME
            SoundManager.getSoundManager().setMasterVolume((double) volume / 100);
            masterVolume = volume;
            volumeString = "MASTER VOLUME: " + masterVolume + "%";
        }
        if (type == 1) {
            SoundManager.getSoundManager().setMusicVolume((double) volume / 100);
            musicVolume = volume;
            volumeString = "MUSIC VOLUME: " + musicVolume + "%";
        }
        if (type == 2) {
            SoundManager.getSoundManager().setSoundVolume((double) volume / 100);
            soundVolume = volume;
            volumeString = "SOUND VOLUME: " + soundVolume + "%";
        }
        addText(volumeString, type);
        removeText(type + 1);
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        //OPTIONS: VOLUME - RESET HIGHSCORES - BACK
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (mainIndex == 3) {
                exitTo = "QUESTION";
            }

            if (mainIndex == textGraphicsList.size() - 1) {
                exitTo = "MAIN";
            }
        }

        if (mainIndex < 3 ) {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                handleVolumeChange(mainIndex, -1);
                if (mainIndex == 0) {
                    setVolume(mainIndex + 1, masterVolume);
                    setVolume(mainIndex + 2, masterVolume);
                }
            }
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                handleVolumeChange(mainIndex, 1);
                if (mainIndex == 0) {
                    setVolume(mainIndex + 1, masterVolume);
                    setVolume(mainIndex + 2, masterVolume);
                }
            }
        }
    }

    public void handleVolumeChange(int type, int sign) {
        int volume = 0;
        if (type == 0) {
            volume = masterVolume;
        }
        if (type == 1) {
            volume = musicVolume;
        }
        if (type == 2) {
            volume = soundVolume;
        }
        int newVolume = Math.max(volume + 5 * sign, 0);
        newVolume = Math.min(newVolume, 100);
        setVolume(type, newVolume);
    }

    @Override
    public void exit() {
        exitTo = "FALSE";
        if (mainIndex != 1) {
            mainIndex = 0;
        }
    }

    @Override
    protected void restartList() {
        //Check if the list is over screen
        maxSize = calculateMaxSize(screenHeight);
        if (maxSize != textGraphicsList.size()) {
            isOverScreen = true;
        }

        setText();
    }
}
