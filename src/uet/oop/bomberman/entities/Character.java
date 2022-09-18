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

    protected int speed = 1;
    protected boolean running, goNorth, goSouth, goEast, goWest;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void updateCoordinate(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                    case UP:    goNorth = true; break;
                    case S:
                    case DOWN:  goSouth = true; break;
                    case A:
                    case LEFT:  goWest  = true; break;
                    case D:
                    case RIGHT: goEast  = true; break;
                    case SHIFT: running = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                    case UP:    goNorth = false; break;
                    case S:
                    case DOWN:  goSouth = false; break;
                    case A:
                    case LEFT:  goWest  = false; break;
                    case D:
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
                }
            }
        });

        if (goNorth && x % Sprite.SCALED_SIZE == 0 && Grass.isGrass(x, y - speed)) y -= speed;
        if (goSouth && x % Sprite.SCALED_SIZE == 0 && Grass.isGrass(x, y + Sprite.SCALED_SIZE)) y += speed;
        if (goEast && y % Sprite.SCALED_SIZE == 0  && Grass.isGrass(x + Sprite.SCALED_SIZE, y))  x += speed;
        if (goWest && y % Sprite.SCALED_SIZE == 0 && Grass.isGrass(x - speed, y))  x -= speed;



        //System.out.println(x + " " + y);

    }
}
