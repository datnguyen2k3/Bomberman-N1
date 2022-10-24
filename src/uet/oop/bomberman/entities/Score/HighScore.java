package uet.oop.bomberman.entities.Score;

import java.io.*;
import java.util.*;

public abstract class HighScore {
    public static final String pathHighScoreDB = "res/highScoreDB.txt";
    public static final int MAX_SCORES = 5;


    public static void addScore(int score) {
        try {
            List<Integer> scores = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathHighScoreDB));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                scores.add(Integer.valueOf(line.substring(3)));
            }
            bufferedReader.close();


            scores.add(score);
            Collections.sort(scores);
            if (scores.size() > MAX_SCORES) {
                scores.remove(0);
            }


            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathHighScoreDB));
            StringBuilder newHighScore = new StringBuilder();
            for (int i = scores.size() - 1; i >= 0; i--) {
                newHighScore.append(scores.size() - i).append(". ").append(scores.get(i)).append("\n");
            }
            System.out.println(newHighScore.toString());
            bufferedWriter.write(newHighScore.toString());
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
