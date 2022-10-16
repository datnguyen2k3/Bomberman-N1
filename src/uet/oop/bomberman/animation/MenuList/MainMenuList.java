package uet.oop.bomberman.animation.MenuList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.animation.TextGraphicsList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.paint.Color.WHITE;

public class MainMenuList extends TextGraphicsList {
    //Starting y position: 210
    //4 lines: START, EXIT, HIGHSCORE, OPTIONS
    //Space between each line: 10px
    //2 sizes: 15 (default) and 13 (small)
    //Total height: 15-height + 3 * 13-height + 3 * space (10px)

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
}
