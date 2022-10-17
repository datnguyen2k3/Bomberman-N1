package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.animation.TextGraphicsList;

public class MainMenuList extends TextGraphicsList {
    private static final String[] mainTexts = {"START", "OPTIONS", "HIGHSCORE", "INFO", "EXIT"};

    public MainMenuList(int screenWidth, int screenHeight, Scene scene) {
        super(mainTexts, screenWidth, screenHeight, scene);
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            exitTo = getText(mainIndex);
            if (mainIndex == 4) {
                exitTo = "QUESTION";
            }
        }
    }

    @Override
    public void exit() {
        exitTo = "FALSE";
    }
}
