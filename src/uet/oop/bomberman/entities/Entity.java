package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.awt.*;
import java.util.Random;

public abstract class Entity {

    protected int _animate = 0;
    public static final int MAX_ANIMATION = 7500;
    protected int x; //Tọa độ X tính từ góc trái trên trong Canvas
    protected int y; //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int speed = 1;
    protected Random rand = new Random();
    protected Image img;
    protected Sprite _currentSprite;
    public Rectangle solidArea;

    public boolean isCollisionOn;
    protected BombermanGame game;


    protected State _state;

    public abstract void initSolidArea();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public static int get_xUnit(int x) {
        return x / Sprite.SCALED_SIZE;
    } // toạ độ theo cột (column)

    public static int get_yUnit(int y) {
        return y / Sprite.SCALED_SIZE;
    } // toạ độ theo hàng (row)

    public static boolean isEqualsCoordinate(Entity e1, Entity e2) {
        return e1.get_xUnit() == e2.get_xUnit() && e1.get_yUnit() == e2.get_yUnit();
    }
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas


    public Entity(int xUnit, int yUnit, Image img) {
        this(xUnit, yUnit);
        this.img = img;
    }

    public Entity(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        initSprite();
    }

    public int get_xUnit() {
        return x / Sprite.SCALED_SIZE;
    }

    public int get_yUnit() {
        return y / Sprite.SCALED_SIZE;
    }

    protected abstract void initSprite();

    public void setCoordinate(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public void animate() {
        if (_animate < MAX_ANIMATION) {
            _animate++;
        } else {
            _animate = 0;
        }
    }

    public State get_state() {
        return this._state;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

}
