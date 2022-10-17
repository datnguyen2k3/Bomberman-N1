package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.animation.TextGraphicsList;

public class OptionList extends TextGraphicsList {
    private static final String[] options = {"VOLUME", "RESET HIGHSCORE", "BACK"};
    private static final int defaultVolume = 100;
    private int currentVolume;

    public OptionList(int screenWidth, int screenHeight, Scene scene) {
        super(options, screenWidth, screenHeight, scene);
        setVolume(defaultVolume);
    }

    private void setVolume(int volume) {
        currentVolume = volume;
        String volumeString = "VOLUME: " + currentVolume + "%";
        removeText(0);
        addText(volumeString, 0);
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        //OPTIONS: VOLUME - RESET HIGHSCORES - BACK
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (mainIndex == 1) {
                exitTo = "QUESTION";
            }

            if (mainIndex == textGraphicsList.size() - 1) {
                exitTo = "MAIN";
            }
        }

        if (mainIndex == 0 ) {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                handleVolumeChange(-1);
            }
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                handleVolumeChange(1);
            }
        }
    }

    public void handleVolumeChange(int sign) {
        int newVolume = Math.max(currentVolume + 5 * sign, 0);
        newVolume = Math.min(newVolume, 100);
        setVolume(newVolume);
    }

    @Override
    public void exit() {
        exitTo = "FALSE";
        if (mainIndex != 1) {
            mainIndex = 0;
        }
    }
}
