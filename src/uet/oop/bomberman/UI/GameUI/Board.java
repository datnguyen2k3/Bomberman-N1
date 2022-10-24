package uet.oop.bomberman.UI.GameUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.Menu.animationMenu.AnimatedGraphic;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphics;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;


public class Board {
    public static int HEIGHT = Sprite.SCALED_SIZE; //48
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


        textSize = new TextGraphics("MMM").getWidth();
        textList = new ArrayList<>();
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        textList.add(new TextGraphics("x"));
        setText();

    }

    private void setSymbol() {
        int spaceWithScreen = 100;
        int space = 30 + 30 + (int) textSize + 120;
        int index = 0;
        //symbol (30px) - 30px - text - 120px - symbol
        for (AnimatedGraphic symbol: symbolList) {
            symbol.resize(30, 30);
            setCenterVertically(symbol);
            symbol.setX(spaceWithScreen + (space) * index);
            index++;
        }
    }

    private void setText() {
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setSize(15);
            textList.get(i).setColor(Color.WHITE);
            textList.get(i).setX(symbolList.get(i).getX() + 40);
            textList.get(i).setOpacity(1);
            setCenterVertically(textList.get(i));
        }
    }

    public void update(int hp, int enemies, int bomb, int flame, int speed, int time, int score) {
        for (AnimatedGraphic symbol: symbolList) {
            symbol.update();
        }
        int[] info = {hp, enemies, speed, bomb, flame};
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setText("x" + info[i]);
        }
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

    public void clear() {

    }
}

