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

    public static final int REAL_BOMBER_WIDTH = 12 * (Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE);
    public static final int REAL_BOMBER_HEIGHT = 16 * (Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE);

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

    public void updateCoordinate() {
        if (goNorth && Grass.isGrass(x, y - speed) && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon, y - speed)) {
            y -= speed;
        }
        if (goSouth && Grass.isGrass(x, y + REAL_BOMBER_HEIGHT) && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon, y + REAL_BOMBER_HEIGHT)) {
            y += speed;
        }
        if (goEast && Grass.isGrass(x + REAL_BOMBER_WIDTH, y + epsilon) && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon, y + REAL_BOMBER_HEIGHT - 5)) {
            x += speed;
        }
        if (goWest && Grass.isGrass(x - epsilon, y + epsilon) && Grass.isGrass(x - epsilon, y + REAL_BOMBER_HEIGHT - epsilon)) {
            x -= speed;
        }
    }
}
