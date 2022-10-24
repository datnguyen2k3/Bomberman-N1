package uet.oop.bomberman.UI.GameUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.Menu.animationMenu.AnimatedGraphic;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphics;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;


public class Board {
    public static final int WIDTH = Game.WIDTH;
    public static final int HEIGHT = Sprite.SCALED_SIZE; //48
    double textSize;
    ArrayList<AnimatedGraphic> symbolList;
    ArrayList<TextGraphics> textList;


    public Board() {
        symbolList = new ArrayList<>();
        symbolList.add(new AnimatedGraphic("file:res/sprites/heart.png", 0, 0));
        symbolList.add(new AnimatedGraphic("file:res/sprites/enemy.png", 0, 0));
        symbolList.add(new AnimatedGraphic("file:res/sprites/powerup_speed.png", 0, 0));
        symbolList.add(new AnimatedGraphic("file:res/sprites/powerup_bombs.png", 0, 0));
        symbolList.add(new AnimatedGraphic("file:res/sprites/powerup_flames.png", 0, 0));
        setSymbol();

        TextGraphics temp = new TextGraphics("M");
        temp.setSize(10);
        textSize = temp.getWidth();
        textList = new ArrayList<>();
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("SCORE: "));
        textList.add(new TextGraphics(""));
        setText();

    }

    private void setSymbol() {
        int spaceWithScreen = 20;
        int space = 30 + (int) textSize + 70;
        int index = 0;
        //symbol (30px) - text - 80 - symbol
        for (AnimatedGraphic symbol: symbolList) {
            symbol.resize(30, 30);
            setCenterVertically(symbol);
            symbol.setX(spaceWithScreen + (space) * index);
            index++;
        }
    }

    private void setText() {
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setSize(10);
            textList.get(i).setColor(Color.WHITE);
            textList.get(i).setOpacity(1);
            setCenterVertically(textList.get(i));
            if (i < symbolList.size()) {
                textList.get(i).setX(symbolList.get(i).getX() + 40);
            }
        }

        //score: ____ - 30px - time left: __:__ - 10px - screen
        //Set timer position
        textList.get(textList.size() - 1).setX(WIDTH - 10 - 16 * textSize);

        //Set score position
        textList.get(textList.size() - 2).setX(WIDTH - 10 - 16 * textSize - 30 - 11 * textSize);
    }

    public void update(int hp, int enemies, int bomb, int flame, int speed, int time, int score) {
        for (AnimatedGraphic symbol: symbolList) {
            symbol.update();
        }
        int[] info = {hp, enemies, speed, bomb, flame};
        for (int i = 0; i < symbolList.size(); i++) {
            textList.get(i).setText("x" + info[i]);
        }
        textList.get(textList.size() - 1).setText("TIME LEFT: " + timeToString(time));
        textList.get(textList.size() - 2).setText("SCORE: " + score);
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.DARKGREY);
        gc.fillRect(0, 0, Game.WIDTH, Board.HEIGHT);
        for (AnimatedGraphic symbol: symbolList) {
            symbol.render(gc);
        }
        for (TextGraphics text: textList) {
            text.render(gc);
        }
    }

    public void setCenterVertically(Object o) {
        if (o.getClass() == AnimatedGraphic.class) {
            AnimatedGraphic symbol = (AnimatedGraphic) o;
            symbol.setY(HEIGHT / 2 - symbol.getHeight() / 2);
        }
        if (o.getClass() == TextGraphics.class) {
            TextGraphics text = (TextGraphics) o;
            text.setY(HEIGHT / 2 + (int) text.getHeight() / 2);
        }

    }

    private String timeToString(int time) {
        int timeLimit = 99 * 60 + 59;
        if (time > timeLimit) {
            time = timeLimit;
        }
        int minute = time / 60;
        int second = time - 60 * minute;
        String result = (minute < 10) ? "0" + minute : String.valueOf(minute);
        result += ":";
        result += (second < 10) ? "0" + second : String.valueOf(second);
        return result;
    }
}

