package uet.oop.bomberman.UI.GameUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.GameUI.Intro;

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
