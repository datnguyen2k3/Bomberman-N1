package uet.oop.bomberman.UI.GameUI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;


public class Board {
    public static int WIDTH = 4 * Sprite.SCALED_SIZE;
    Rectangle rectangle = new Rectangle();
    int size = 15;
    Font font = Font.loadFont("file:res/Font/game_font.ttf", size);
    Text hpText;
    Text enemyText;
    Text speedText;
    Text bombText;
    Text flameText;
    Text timeText;
    boolean isAdd = false;

    public Board() {
        rectangle.setX(Game.WIDTH - Board.WIDTH);
        rectangle.setY(0);
        rectangle.setWidth(WIDTH);
        rectangle.setHeight(Game.HEIGHT);
        rectangle.setFill(Color.DARKGREY);

        enemyText = initText(1);
        hpText = initText(2);
        speedText = initText(3);
        bombText = initText(4);
        flameText = initText(5);
        timeText = initText(6);
    }

    private Text initText(int order) {
        Text text = new Text(rectangle.getX() + Sprite.SCALED_SIZE / 2,
                rectangle.getY() + Sprite.SCALED_SIZE * (1 + order), "");
        text.setFont(font);
        text.setFill(Color.BLACK);

        return text;
    }

    public void update(int hp, int enemies, int bomb, int flame, int speed, int time) {
        hpText.setText("HP:" + hp);
        enemyText.setText("Enemy:" + enemies);
        bombText.setText("Bomb:" + bomb);
        flameText.setText("Flame:" + flame);
        speedText.setText("Speed:" + speed);
        timeText.setText("Time:" + time);
    }

    public void pushInRoot(Group root) {
        root.getChildren().add(rectangle);
        root.getChildren().add(hpText);
        root.getChildren().add(enemyText);
        root.getChildren().add(flameText);
        root.getChildren().add(speedText);
        root.getChildren().add(bombText);
        root.getChildren().add(timeText);
    }

    public void popInRoot(Group root) {
        root.getChildren().remove(rectangle);
        root.getChildren().remove(hpText);
        root.getChildren().remove(enemyText);
        root.getChildren().remove(flameText);
        root.getChildren().remove(speedText);
        root.getChildren().remove(bombText);
        root.getChildren().remove(timeText);
    }

}
