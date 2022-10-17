package uet.oop.bomberman.animation.MenuList;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.animation.TextGraphicsList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class HighscoreList extends TextGraphicsList {
    private static final String dbPath = "res/highscoreDB.txt";
    private static final String defaultLine = "NO HIGHSCORE RECORDED"; //text rendered when there are no highscores
    int totalHighscore = 5;

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
        public static Highscore parseHighscore(String text) {
            //Text has the follwing form: player name + " " + score
            int spaceIndex = 0;
            while (text.charAt(spaceIndex) != ' ') {
                spaceIndex++;
            }
            if (spaceIndex == 0 || spaceIndex > text.length()) {
                return null;
            }
            String playerName = text.substring(0, spaceIndex);
            int score = Integer.parseInt(text.substring(spaceIndex + 1));
            return new Highscore(playerName, score);
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
        readDB();

        highscore.sort((o1, o2) -> {
            if (o1.score != o2.score) {
                return Integer.compare(o2.score, o1.score);
            }
            return o2.playerName.compareTo(o1.playerName);
        });

        if (highscore.size() == 0) {
            addTextAtEnd(defaultLine);
        } else {
            while (highscore.size() > totalHighscore) {
                highscore.remove(highscore.size() - 1);
            }

            for (Highscore score: highscore) {
                addTextAtEnd(score.toString());
            }
        }
    }

    private void addHighscore(Highscore newScore) {
        removeText(defaultLine);

        if (newScore.score > highscore.get(highscore.size() - 1).score) {
            removeText(highscore.size() - 1);
            findAndDeleteDB(highscore.get(highscore.size() - 1).toString());
            highscore.remove(highscore.size() - 1);
            int index = 0;
            while (newScore.score < highscore.get(index).score) {
                index++;
            }
            highscore.add(index, newScore);
            addText(newScore.toString(), index);

            writeToDB(newScore);
        }
    }

    @Override
    protected void addEventHandlers(KeyEvent keyEvent) {
        if (mainIndex == textGraphicsList.size() - 1 && keyEvent.getCode() == KeyCode.ENTER) {
            //Handle state changes
            exitTo = "MAIN";
        }
    }

    private void readDB() {
        try (BufferedReader br = new BufferedReader(new FileReader(dbPath))) {
            String line = br.readLine();

            while (line != null) {
                highscore.add(Highscore.parseHighscore(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void findAndDeleteDB(String text) {
        File file = new File(dbPath);
        List<String> out;
        try {
            out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(text))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToDB(Highscore newScore) {
      try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(dbPath), StandardOpenOption.APPEND)) {
            writer.write(newScore.toString() + "\n");
        } catch (IOException ioe) {
            System.err.format("IOException: %s%n", ioe);
        }
    }

    public void resetHighscore() {
        File file = new File(dbPath);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        highscore.clear();
        removeAllText();
        setHighscore();
        addTextAtEnd("BACK");
    }
}
