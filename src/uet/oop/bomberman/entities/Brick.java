package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.lang.Character;
import java.util.HashMap;

public class Brick extends Entity {
    public static final char diagramBrick = '*';
    private char item = '*';
    private boolean isDestroyed = false;
    private static final char[] items = new char[]{'*', 'x', 'b', 'f', 's'};
    private HashMap<Character, Image> getItemImg = new HashMap<Character, Image>();

    public Brick(int xUnit, int yUnit, Image img, char item) {
        super(xUnit, yUnit, img);
        createHashMapItemImg();
        setItem(item);
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = '*';
    }

    public void setItem(char item) {
        if (item == '*') {
            this.item = ' ';
            return;
        }
        this.item = item;
    }

    public static boolean isBrick(char diagram) {
        for (char item : items) {
            if (diagram == item)
                return true;
        }
        return false;
    }

    public static boolean isBrick(int xUnit, int yUnit) {
        return isBrick(BombermanGame.diagramMap[yUnit][xUnit]);
    }

    private void createHashMapItemImg() {
        getItemImg.put(' ', Sprite.grass.getFxImage());
        getItemImg.put('x', Sprite.portal.getFxImage());
        getItemImg.put('b', Sprite.powerup_bombs.getFxImage());
        getItemImg.put('f', Sprite.powerup_flames.getFxImage());
        getItemImg.put('s', Sprite.powerup_speed.getFxImage());
    }

    public void setDestroyed() {
        this.isDestroyed = true;
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = item;
    }

    public void updateGetDamage(Bomber bomber) {
        if (isDestroyed)
            return;
        for (Pair<Integer, Integer> coordinate : bomber.bomb.destroyedBricks) {
            if (coordinate.equals(new Pair<>(get_xUnit(), get_yUnit()))) {
                setDestroyed();
                break;
            }
        }
    }

    @Override
    public void update() {

    }

    public boolean pointIsOnEntityArea(Point p) {
        return false;
    }

    public void update(Bomber bomber) {
        updateGetDamage(bomber);
    }

    @Override
    public void initSolidArea() {

    }

    @Override
    protected void initSprite() {

    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDestroyed) {
            gc.drawImage(Sprite.grass.getFxImage(), x, y);
            gc.drawImage(getItemImg.get(item), x, y);
            return;
        }

        super.render(gc);
    }

    @Override
    public int getVal() {
        return 0;
    }
}
