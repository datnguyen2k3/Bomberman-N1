package uet.oop.bomberman.UI.MiniInfo;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.Score.Score;

public class PlusScore extends MiniInfo{

    protected int score = 0;

    public PlusScore(BombermanGame game, Enemy enemy) {
        super(enemy.get_xUnitCenter(), enemy.get_yUnitCenter(), game);
        score = Score.getScore(enemy);
        title += score;
        text.setText("+" + score);
    }


}
