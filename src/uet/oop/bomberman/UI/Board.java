package uet.oop.bomberman.UI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;


public class Board {
    Rectangle rectangle = new Rectangle();
    Text hpText;
    boolean isAdd = false;

    public Board() {
        rectangle.setX(BombermanGame.WIDTH * Sprite.SCALED_SIZE);
        rectangle.setY(0);
        rectangle.setWidth(Game.WIDTH - BombermanGame.WIDTH * Sprite.SCALED_SIZE);
        rectangle.setHeight(Game.HEIGHT);
        rectangle.setFill(Color.GRAY);

        hpText = new Text(rectangle.getX() + Sprite.SCALED_SIZE / 2, rectangle.getY() + Sprite.SCALED_SIZE * 2, "HP: ");
        hpText.setFont(Font.loadFont("file:res/Font/game_font.ttf", 15));
        hpText.setFill(Color.BLACK);
    }

    public void update(int hp) {
        hpText.setText("HP:" + hp);
    }

    public void pushInRoot(Group root) {
        root.getChildren().add(rectangle);
        root.getChildren().add(hpText);
    }

    public void popInRoot(Group root) {
        root.getChildren().remove(rectangle);
        root.getChildren().remove(hpText);
    }

}
