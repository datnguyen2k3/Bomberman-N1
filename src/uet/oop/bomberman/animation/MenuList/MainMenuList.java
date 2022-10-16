package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.animation.TextGraphicsList;

public class MainMenuList extends TextGraphicsList {
    public MainMenuList(String[] textList, int screenWidth, int screenHeight, Scene scene) {
        super(textList, screenWidth, screenHeight, scene);
    }


    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            //Handle state changes
            exitTo = getText(mainIndex);
        }
    }

    @Override
    public void exit() {
        exitTo = "FALSE";
    }
}
