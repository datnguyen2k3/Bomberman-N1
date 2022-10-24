package uet.oop.bomberman.UI.Menu.animationMenu.PauseList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.UI.Menu.PauseMenu;
import uet.oop.bomberman.UI.Menu.animationMenu.MenuList.OptionList;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphicsList;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PauseLists {
    private static final String[] listTypes = {"MAIN", "OPTIONS", "RESUME", "MENU"};
    private List<TextGraphicsList> pauseLists;
    private PauseMenu pauseMenu;
    private int currentIndex = 0;

    public PauseLists(int screenWidth, int screenHeight, Scene scene, PauseMenu pauseMenu) {
        pauseLists = new ArrayList<>();
        pauseLists.add(new MainPauseList(screenWidth, screenHeight, scene));
        pauseLists.add(new OptionList(screenWidth, screenHeight, scene));
        this.pauseMenu = pauseMenu;
    }

    public void render(GraphicsContext gc) {
        pauseLists.get(currentIndex).render(gc);
    }

    public void update(Stage stage) {
        pauseLists.get(currentIndex).update();

        //Handle list changes
        if ((!Objects.equals(pauseLists.get(currentIndex).isExiting(), "FALSE"))) {
            int oldIndex = currentIndex;
            currentIndex = getCurrentIndex((pauseLists.get(currentIndex).isExiting()));
            if (currentIndex == 2) { //RESUME TO GAME
                currentIndex = 0;
                pauseMenu.setEnd();
            }
            if (currentIndex == 3) { //BACK TO MENU
                currentIndex = 0;
                pauseMenu.setEnd();
                pauseMenu.getGame().getBombermanGame().setEnd(pauseMenu.getGame().getRoot());
                pauseMenu.getGame().setNewGame();
            }
            //Exit from the old list
            pauseLists.get(oldIndex).exit();
        }
    }

    public int getCurrentIndex(String type) {
        for (int i = 0; i < listTypes.length; i++) {
            if (Objects.equals(type, listTypes[i])) {
                return i;
            }
        }

        return 0;
    }

    public static String getListType(int index) {
        if (index == 1) {
            return "OPTIONS";
        }

        return "MAIN";
    }
}
