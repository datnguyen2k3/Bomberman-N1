package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.GameUI.Board;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphics;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.awt.*;
import java.util.Random;
import uet.oop.bomberman.BombermanGame;

import static javafx.scene.paint.Color.WHITE;

public abstract class Entity  {

    protected int _animate = 0;
    protected int animateRight = 0;
    protected int animateLeft = 0;
    protected int animateUp = 0;
    protected int animateDown = 0;
    protected int animateDead = 0;
    public static final int MAX_ANIMATION = 7500;
    protected int x; //Tọa độ X tính từ góc trái trên trong Canvas
    protected int y; //Tọa độ Y tính từ góc trái trên trong Canvas
    protected  int speed = 4;

    protected Image img;

    public Rectangle solidArea;

    public boolean isCollisionOn;
    protected BombermanGame game;
    public  Random rand = new Random();

    public boolean isEnd() {
        return isEnd;
    }

    protected boolean isEnd = false;

    public abstract void initSolidArea();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public  int getSpeed() {
        return speed;
    }

    protected State _state;

    public static int get_xUnit(int x) {
        return x / Sprite.SCALED_SIZE;
    }

    public static int get_yUnit(int y) {
        return y / Sprite.SCALED_SIZE;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public static boolean isEqualsCoordinate(Entity e1, Entity e2) {
        return e1.get_xUnit() == e2.get_xUnit() && e1.get_yUnit() == e2.get_yUnit();
    }
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas


    public Entity(int xUnit, int yUnit, Image img, BombermanGame game) {
        this(xUnit, yUnit, game);
        this.img = img;
    }

    public Entity(int xUnit, int yUnit, BombermanGame game) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        initSprite();
        this.game = game;
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


    public void setImg(Image img) {
        this.img = img;
    }

    public void animate(String state) {
        if(state.equals("up")) {
            if (animateUp < MAX_ANIMATION) {
                animateUp++;
            } else {
                animateUp = 0;
            }
        }
        else if (state.equals("down")) {
            if (animateDown < MAX_ANIMATION) {
                animateDown++;
            } else {
                animateDown = 0;
            }
        }
        else if (state.equals("right")) {
            if (animateRight < MAX_ANIMATION) {
                animateRight++;
            } else {
                animateRight = 0;
            }
        }
        else if (state.equals("left")) {
            if (animateLeft < MAX_ANIMATION) {
                animateLeft++;
            } else {
                animateLeft = 0;
            }
        }
        else if (state.equals("dead")) {
            if (animateDead < MAX_ANIMATION) {
                animateDead++;
            } else {
                animateDead = 0;
            }
        }
    }
    public void animate() {
        if (this._animate < MAX_ANIMATION) {
            this._animate++;
        } else {
            this._animate = 0;
        }
    }

    public int animate(int animation ) {
        if (animation < MAX_ANIMATION ) {
            animation ++;
            return animation;
        }
        else {
            animation = 0;
            return animation;
        }

    }

    public State get_state() {
        return this._state;
    }

    public void render(GraphicsContext gc, Image img, int x, int y) {
        if (y == Game.HEIGHT - Sprite.SCALED_SIZE * 2) {
            // gc.drawImage(img, get_xRender(x), y);
        }
        gc.drawImage(img, get_xRender(x), y);
    }

    public void render(GraphicsContext gc) {
        render(gc, img, x, y);
    }

    public int get_xRender(int x) {
        int xRender = x;

        if (game.getBomberman().getX() > Game.WIDTH / 2) {
            xRender -= Math.min(game.getBomberman().getX() + game.getBomberman().getSolidArea().x - Game.WIDTH / 2,
                    BombermanGame.WIDTH * Sprite.SCALED_SIZE - Game.WIDTH);
        }
        return xRender;
    }

    public void chooseSpriteBrick() {
        if(_state == State.BE_DESTROYING) {
            img = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,_animate,300).getFxImage();
        }
        else if ( _state == State.EXISTING) {
            img = Sprite.brick.getFxImage();
        }
        else {
            img = Sprite.grass.getFxImage();
        }
    }

    public abstract void update();

}
