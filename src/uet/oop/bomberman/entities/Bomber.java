package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Character {
    public Bomb bomb = new Bomb(0, 0, Sprite.bomb.getFxImage());
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public void updateInput(Scene scene) {
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

                if (event.getCode() == KeyCode.SPACE) {
                    System.out.println(get_xUnit() + " " + get_yUnit());
                    bomb.start(get_xUnit(), get_yUnit());
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

    }

    @Override
    public void update() {
        updateCoordinate();
        bomb.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        bomb.render(gc);
        super.render(gc);
    }

}
