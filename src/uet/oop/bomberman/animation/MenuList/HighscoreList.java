package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.animation.TextGraphicsList;

import java.util.*;

public class HighscoreList extends TextGraphicsList {
    static class Highscore {
        private String playerName;
        private int score;

        public Highscore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String toString() {
            return playerName + " " + score;
        }
    }

    List<Highscore> highscore;

    public HighscoreList(int screenWidth, int screenHeight, Scene scene) {
        super(new String[0], screenWidth, screenHeight, scene);
        highscore = new ArrayList<>();
        setHighscore();
        addTextAtEnd("BACK");
    }

    private void setHighscore() {
        //Add a .txt file to store all the highscores
        //Reading file functionalites...
        highscore.add(new Highscore("t", 1));
        highscore.add(new Highscore("t", 2));
        highscore.add(new Highscore("t", 3));
        highscore.add(new Highscore("t", 4));
        highscore.add(new Highscore("t", 5));
        highscore.add(new Highscore("t", 6));
        highscore.add(new Highscore("t", 7));
        highscore.add(new Highscore("t", 8));
        highscore.add(new Highscore("t", 9));
        highscore.add(new Highscore("t", 10));

        highscore.sort((o1, o2) -> {
            if (o1.score != o2.score) {
                return Integer.compare(o1.score, o2.score);
            }
            return o1.playerName.compareTo(o2.playerName);
        });

        while (highscore.size() > 10) {
            highscore.remove(highscore.size() - 1);
        }

        for (Highscore score: highscore) {
            addTextAtEnd(score.toString());
        }
    }

    private void addHighscore(Highscore newScore) {
        if (newScore.score > highscore.get(highscore.size() - 1).score) {
            removeText(highscore.size() - 1);
            highscore.remove(highscore.size() -1);
            int index = 0;
            while (newScore.score < highscore.get(index).score) {
                index++;
            }
            highscore.add(index, newScore);
            addText(newScore.toString(), index);

            //Write to file and delete
            //...
        }
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (mainIndex == textGraphicsList.size() - 1 && keyEvent.getCode() == KeyCode.ENTER) {
            //Handle state changes
            exitTo = "MAIN";
        }
    }
}
