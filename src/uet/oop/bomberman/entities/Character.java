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

    enum State {
        GO_NORTH,
        GO_SOUTH,
        GO_WEST,
        GO_EAST,
        IDLE,
        DEAD
    }

    protected State previousState;
    protected int speed = 2;
    protected boolean running, goNorth, goSouth, goEast, goWest;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void updateCoordinate() {

        if (goNorth && x % Sprite.SCALED_SIZE == 0 && Grass.isGrass(x, y - speed)) y -= speed;
        if (goSouth && x % Sprite.SCALED_SIZE == 0 && Grass.isGrass(x, y + Sprite.SCALED_SIZE)) y += speed;
        if (goEast && y % Sprite.SCALED_SIZE == 0  && Grass.isGrass(x + Sprite.SCALED_SIZE, y))  x += speed;
        if (goWest && y % Sprite.SCALED_SIZE == 0 && Grass.isGrass(x - speed, y))  x -= speed;


    }
}
