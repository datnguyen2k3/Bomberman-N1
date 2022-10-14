package uet.oop.bomberman.animation;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextGraphics {
    private Text textGraphics;

    public TextGraphics(String text) {
        textGraphics = new Text(0, 0, text);
        textGraphics.setFont(Font.loadFont("file:res/Font/game_font.ttf", 15));
    }
    public TextGraphics(double x, double y, String text) {
        textGraphics = new Text(x, y, text);
        textGraphics.setFont(Font.loadFont("file:res/Font/game_font.ttf", 15));
    }

    public void setFont(Font font) {
        textGraphics.setFont(font);
    }

    public void setColor(Color color) {
        textGraphics.setFill(color);
    }

    public void setPos(double x, double y) {
        textGraphics.setX(x);
        textGraphics.setY(y);
    }

    public Text getText() {
        return textGraphics;
    }

    public double getWidth() {
        return textGraphics.getLayoutBounds().getWidth();
    }

    public double getHeight() {
        return textGraphics.getLayoutBounds().getHeight();
    }

    public void setOpacity(double opacity) {
        textGraphics.setOpacity(opacity);
    }

    public void create(Group root) {
        root.getChildren().add(textGraphics);
    }
}
