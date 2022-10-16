package uet.oop.bomberman.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static uet.oop.bomberman.animation.MenuList.MainMenuList.defaultSize;

public class TextGraphics {
    private Color color;
    private Text textGraphics;

    public TextGraphics(String text) {
        textGraphics = new Text(0, 0, text);
        textGraphics.setFont(Font.loadFont("file:res/Font/game_font.ttf", defaultSize));
    }

    public void setColor(Color color) {
        this.color = color;
        textGraphics.setFill(color);
    }

    public void setSize(double size) {
        textGraphics.setFont(Font.loadFont("file:res/Font/game_font.ttf", size));
    }

    public void setPos(double x, double y) {
        textGraphics.setX(x);
        textGraphics.setY(y);
    }

    public void setX(double x) {
        textGraphics.setX(x);
    }

    public void setY(double y) {
        textGraphics.setY(y);
    }

    public void setCenterHorizontal(double screenWidth) {
        textGraphics.setX(screenWidth / 2 - this.getWidth() / 2);
    }

    public void setOpacity(double opacity) {
        textGraphics.setOpacity(opacity);
    }
    public double getWidth() {
        return textGraphics.getLayoutBounds().getWidth();
    }

    public double getHeight() {
        return textGraphics.getLayoutBounds().getHeight();
    }

    public double getY() {
        return textGraphics.getY();
    }

    public String getText() {
        return textGraphics.getText();
    }

    public void render(GraphicsContext gc) {
        //Set Color
        gc.setFill(color);

        //Set Font
        gc.setFont(textGraphics.getFont());

        //Set Opacity
        double preAlpha = gc.getGlobalAlpha();
        gc.setGlobalAlpha(textGraphics.getOpacity());

        //Draw Text
        gc.fillText(textGraphics.getText(), textGraphics.getX(), textGraphics.getY());

        //Set to old opacity after draw
        gc.setGlobalAlpha(preAlpha);
    }
}
