package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.animation.TextGraphicsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuLists {
    private static final String[] listTypes = {"MAIN", "OPTIONS", "HIGHSCORE", "INFO", "START", "EXIT"};
    private List<TextGraphicsList> menuLists;
    private int currentIndex;

    public MenuLists(int screenWidth, int screenHeight, Scene scene) {
        menuLists = new ArrayList<>();

        //Add main menu list;
        String[] mainTexts = {"START", "OPTIONS", "HIGHSCORE", "INFO", "EXIT"};
        menuLists.add(new MainMenuList(mainTexts, screenWidth, screenHeight, scene));

        //Add options list
        String[] options = {"OPTIONS", "BACK"};
        menuLists.add(new TextGraphicsList(options, screenWidth, screenHeight, scene));

        //Add highscore list
        menuLists.add(new HighscoreList(screenWidth, screenHeight, scene));
        currentIndex = 0;

        //Add info
        String[] info = {"INFO", "BACK"};
        menuLists.add(new TextGraphicsList(info, screenWidth, screenHeight, scene));

    }

    public void render(GraphicsContext gc) {
        menuLists.get(currentIndex).render(gc);
    }

    public void update() {
        menuLists.get(currentIndex).update();
        if ((!Objects.equals(menuLists.get(currentIndex).isExiting(), "FALSE"))) {
            int oldIndex = currentIndex;
            currentIndex = getCurrentIndex((menuLists.get(currentIndex).isExiting()));
            if (currentIndex == 4) {
                currentIndex = 0;
            } else if (currentIndex == 5) {
                currentIndex = 0;
            }
            System.out.println(currentIndex);
            menuLists.get(oldIndex).exit();
        }
    }

    public int getCurrentIndex(String type) {
        if (Objects.equals(type, listTypes[1])) {
            return 1;
        }
        if (Objects.equals(type, listTypes[2])) {
            return 2;
        }
        if (Objects.equals(type, listTypes[3])) {
            return 3;
        }
        if (Objects.equals(type, listTypes[4])) {
            return 4;
        }
        if (Objects.equals(type, listTypes[5])) {
            return 5;
        }

        //Return to main menu
        return 0;
    }
}
