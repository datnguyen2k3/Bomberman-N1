package uet.oop.bomberman.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;

public class LevelGameUI extends Intro {
    int level = 1;

    public LevelGameUI(int level) {
        super();
        this.level = level;
        initTitle();
        text.setText(title);
    }

    @Override
    protected void initTitle() {
        title = "Stage " + level;
    }
}
