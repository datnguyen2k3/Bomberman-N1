package uet.oop.bomberman.UI.Menu.animationMenu.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphicsList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InfoList extends TextGraphicsList {
    private static final String[] emptyList = {};
    private static final String infoTextPath = "res/info.txt";
    public InfoList(int screenWidth, int screenHeight, Scene scene) {
        super(emptyList, screenWidth, screenHeight, scene);
        readInfoText();
        addTextAtEnd("BACK");
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (mainIndex == textGraphicsList.size() - 1 && keyEvent.getCode() == KeyCode.ENTER) {
            exitTo = "MAIN";
        }
    }

    @Override
    protected void setSize() {
        size = 15;
    }

    private void readInfoText() {
        try (BufferedReader br = new BufferedReader(new FileReader(infoTextPath))) {
            String line = br.readLine();

            while (line != null) {
                addTextAtEnd(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
