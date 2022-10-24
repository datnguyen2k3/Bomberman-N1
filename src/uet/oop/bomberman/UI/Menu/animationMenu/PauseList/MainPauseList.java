package uet.oop.bomberman.UI.Menu.animationMenu.PauseList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphicsList;

public class MainPauseList extends TextGraphicsList {
    private static final String[] mainTexts = {"RESUME", "OPTIONS", "MENU"};

    public MainPauseList(int screenWidth, int screenHeight, Scene scene) {
        super(mainTexts, screenWidth, screenHeight, scene);
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            exitTo = getText(mainIndex);
        }
    }

    @Override
    public void exit() {
        exitTo = "FALSE";
    }
}
