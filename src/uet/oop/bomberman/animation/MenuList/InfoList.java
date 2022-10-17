package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.animation.TextGraphicsList;

public class InfoList extends TextGraphicsList {
    public static final String[] infoList = {"INFO", "BACK"};
    public InfoList(int screenWidth, int screenHeight, Scene scene) {
        super(infoList, screenWidth, screenHeight, scene);
    }


    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (mainIndex == textGraphicsList.size() - 1 && keyEvent.getCode() == KeyCode.ENTER) {
            exitTo = "MAIN";
        }
    }
}
