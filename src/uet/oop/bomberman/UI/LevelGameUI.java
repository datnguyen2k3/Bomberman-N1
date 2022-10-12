package uet.oop.bomberman.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;

public class LevelGameUI {
    public static final int TIME_RUN = 120;
    protected int currentTime = 0;
    protected int level = 1;
    String title = "Stage ";
    Text text;
    protected boolean isAdd = false;
    protected boolean isRun = true;

    public boolean isRun() {
        return isRun;
    }

    public LevelGameUI() {
        text = new Text(100, 100, title + level);
        text.setFont(Font.loadFont("file:res/Font/game_font.ttf", 60));
        text.setFill(Color.WHITE);
        text.setScaleX(1);
        text.setScaleY(1);
    }

    public LevelGameUI(int level) {
        this();
        this.level = level;
    }

    public Text getText() {
        return text;
    }

    public void run(Group root) {
        if (!isRun) {
            return;
        }

        if (!isAdd) {
            isAdd = true;
            root.getChildren().add(text);
        }

        currentTime++;
        if (currentTime > TIME_RUN) {
            root.getChildren().remove(text);
            isRun = false;
        }
    }

}
