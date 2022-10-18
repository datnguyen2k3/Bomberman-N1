package uet.oop.bomberman.UI.Menu.animationMenu.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphicsList;

public class QuestionList extends TextGraphicsList {
    private static final String[] questionTexts = {"ARE YOU SURE?", "YES", "NO"};
    private Callback callback;
    private int beforeIndex;

    public QuestionList(int screenWidth, int screenHeight, Scene scene) {
        super(questionTexts, screenWidth, screenHeight, scene);
        beforeIndex = 0;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setBeforeIndex(int beforeIndex) {
        this.beforeIndex = beforeIndex;
    }
    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (mainIndex != 0) {
                if (mainIndex == 1) {
                    callback.execute();
                }

                exitTo = MenuLists.getListType(beforeIndex);
            }
        }
    }

    public interface Callback {
        default void execute() {
        }
    }
}
