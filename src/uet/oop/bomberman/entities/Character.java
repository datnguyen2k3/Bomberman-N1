package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Character extends Entity {

    public static final int REAL_BOMBER_WIDTH = 12 * (Sprite.SCALE);
    public static final int REAL_BOMBER_HEIGHT = 16 * (Sprite.SCALE);

    public static final int epsilon = 5;


    enum State {
        GO_NORTH,
        GO_SOUTH,
        GO_WEST,
        GO_EAST,
        IDLE,
        DEAD
    }

    protected State previousState;
    protected int speed = 1;
    protected boolean running, goNorth, goSouth, goEast, goWest;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }



}
