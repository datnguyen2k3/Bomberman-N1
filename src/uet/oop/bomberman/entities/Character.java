package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

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

        if (goNorth) y -= 1;
        if (goSouth) y += 1;
        if (goEast)  x += 1;
        if (goWest)  x -= 1;
    }
}
