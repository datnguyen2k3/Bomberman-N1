package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.animation.TextGraphicsList;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuLists {
    private static final String[] listTypes = {"MAIN", "OPTIONS", "HIGHSCORE", "INFO", "QUESTION", "START", "EXIT"};
    private List<TextGraphicsList> menuLists;
    private int currentIndex;

    public MenuLists(int screenWidth, int screenHeight, Scene scene) {
        menuLists = new ArrayList<>();

        //Add main menu list;
        menuLists.add(new MainMenuList(screenWidth, screenHeight, scene));

        //Add option list
        menuLists.add(new OptionList(screenWidth, screenHeight, scene));

        //Add highscore list
        menuLists.add(new HighscoreList(screenWidth, screenHeight, scene));
        currentIndex = 0;

        //Add info list
        String[] info = {"INFO", "BACK"};
        menuLists.add(new InfoList(info, screenWidth, screenHeight, scene));

        //Add question list
        menuLists.add(new QuestionList(screenWidth, screenHeight, scene));
    }

    public void render(GraphicsContext gc) {
        menuLists.get(currentIndex).render(gc);
    }

    public void update() {
        menuLists.get(currentIndex).update();
        if ((!Objects.equals(menuLists.get(currentIndex).isExiting(), "FALSE"))) {
            int oldIndex = currentIndex;
            currentIndex = getCurrentIndex((menuLists.get(currentIndex).isExiting()));

            handleQuestionList(oldIndex);

            //Delete this after adding start and exit functionalities
            if (currentIndex == 5) {
                currentIndex = 0;
            } else if (currentIndex == 6) {
                currentIndex = 0;
            }

            menuLists.get(oldIndex).exit();
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

    public void handleQuestionList(int oldIndex) {
        if (currentIndex != 4) {
            return;
        }

        if (currentIndex == 4) {
            QuestionList questionList = (QuestionList) menuLists.get(4);
            questionList.setBeforeIndex(oldIndex);
        }

        QuestionList questionList = (QuestionList) menuLists.get(4);

        //Handle reset highscore
        if (oldIndex == 1) {
            HighscoreList highscoreList = (HighscoreList) menuLists.get(2);
            questionList.setCallback(new QuestionList.Callback() {
                @Override
                public void execute() {
                    highscoreList.resetHighscore();
                }
            });
        }

        //Handle exit
        if (oldIndex == 0) {
            questionList.setCallback(new QuestionList.Callback() {
                @Override
                public void execute() {
                    exit();
                }
            });
        }
    }

    public static String getListType(int index) {
        switch (index) {
            case 1:
                return "OPTIONS";
            case 2:
                return "HIGHSCORE";
            case 3:
                return "INFO";
            case 4:
                return "QUESTION";
            case 5:
                return "START";
            case 6:
                return "EXIT";
        }

        return "MAIN";
    }

    public void exit() {
        //Exiting functionalities
    }
}
