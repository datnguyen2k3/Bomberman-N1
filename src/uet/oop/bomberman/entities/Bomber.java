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
    public static final int TIME_ANIMATION_RUNNING = 30;
    public static final int TIME_ANIMATION_DEAD = 100;
    private Sprite _sprite;
    private State _state;
    public Bomb bomb = new Bomb(0, 0, Sprite.bomb.getFxImage());

    @Override
    public void initSolidArea() {

    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        _sprite = Sprite.player_right;
        _state = State.GO_EAST;
    }

    @Override
    protected void initSprite() {

    }

    public boolean cantMoveDownRight() {
        if (!Grass.isGrass(x, y + REAL_BOMBER_HEIGHT) ) {
            System.out.println("Cant move down");
            return true;
        }
        return false;
    }

    public boolean cantMoveDownLeft() {
        if (!Grass.isGrass(x +REAL_BOMBER_WIDTH, y + REAL_BOMBER_HEIGHT) ) {
            System.out.println("Cant move down");
            return true;
        }
        return false;
    }


    public void autoMoveRightWhenStandingOnEdge() {
        if (cantMoveDownRight() && Grass.isGrass(x + epsilon * Sprite.SCALE, y + REAL_BOMBER_HEIGHT)) {
            x += epsilon * Sprite.SCALE;
        }
    }

    public void autoMoveLeftWhenStandingOnEdge() {
        if (cantMoveDownLeft() && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon * Sprite.SCALE, y + REAL_BOMBER_HEIGHT)) {
            x -= epsilon * Sprite.SCALE;
        }
    }


    public void updateCoordinate() {
        if (goNorth && Grass.isGrass(x, y - speed) && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon, y - speed)) {
            y -= speed;
        }
        if (goSouth && Grass.isGrass(x, y + REAL_BOMBER_HEIGHT) && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon, y + REAL_BOMBER_HEIGHT)) {
            y += speed;
        }
        if (goEast && Grass.isGrass(x + REAL_BOMBER_WIDTH, y + epsilon) && Grass.isGrass(x + REAL_BOMBER_WIDTH - epsilon, y + REAL_BOMBER_HEIGHT - epsilon)) {
            x += speed;
        }
        if (goWest && Grass.isGrass(x - epsilon, y + epsilon) && Grass.isGrass(x - epsilon, y + REAL_BOMBER_HEIGHT - epsilon)) {
            x -= speed;
        }
    }

    public void updateInput(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                    case UP:
                        goNorth = true;
                        break;
                    case S:
                    case DOWN:
                        goSouth = true;
                        
                    case A:
                    case LEFT:
                        goWest = true;
                        break;
                    case D:
                    case RIGHT:
                        goEast = true;
                        break;
                    case SHIFT:
                        running = true;
                        break;
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
                    case UP:
                        goNorth = false;
                        previousState = State.GO_NORTH;
                        break;
                    case S:
                    case DOWN:
                        previousState = State.GO_SOUTH;
                        goSouth = false;
                        break;
                    case A:
                    case LEFT:
                        previousState = State.GO_WEST;
                        goWest = false;
                        break;
                    case D:
                    case RIGHT:
                        previousState = State.GO_EAST;
                        goEast = false;
                        break;
                    case SHIFT:
                        running = false;
                        break;
                }
            }
        });

    }

    private void updateCurrentState() {
        if (goNorth) _state = State.GO_NORTH;
        else if (goEast) _state = State.GO_EAST;
        else if (goSouth) _state = State.GO_SOUTH;
        else if (goWest) _state = State.GO_WEST;
        else _state = State.IDLE;

    }

    private void choosingSprite() {
        updateCurrentState();
        switch (_state) {
            case GO_NORTH: {
                _sprite = Sprite.player_up;
                _sprite = Sprite.movingSprite(Sprite.player_up_1,
                        Sprite.player_up_2, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_SOUTH: {
                _sprite = Sprite.player_down;
                _sprite = Sprite.movingSprite(Sprite.player_down_1,
                        Sprite.player_down_2, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_EAST: {
                _sprite = Sprite.player_right;
                _sprite = Sprite.movingSprite(Sprite.player_right_1,
                        Sprite.player_right_2, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_WEST: {
                _sprite = Sprite.player_left;
                _sprite = Sprite.movingSprite(Sprite.player_left_1,
                        Sprite.player_left_2, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case DEAD: {
                _sprite = Sprite.player_dead1;
                _sprite = Sprite.movingSprite(Sprite.player_dead1,
                        Sprite.player_dead2, Sprite.player_dead3, _animate, TIME_ANIMATION_DEAD);
                break;
            }
            case IDLE: {
                if (previousState == State.GO_NORTH) {
                    _sprite = Sprite.player_up;
                } else if (previousState == State.GO_SOUTH) {
                    _sprite = Sprite.player_down;
                } else if (previousState == State.GO_EAST) {
                    _sprite = Sprite.player_right;
                } else if (previousState == State.GO_WEST) {
                    _sprite = Sprite.player_left;
                }
            }
        }
    }

    @Override
    public void update() {
        animate();
        updateCoordinate();
        bomb.update();
    }

    public boolean pointIsOnEntityArea(Point p) {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        choosingSprite();
        bomb.render(gc);
        gc.drawImage(_sprite.getFxImage(), x, y);
    }

    @Override
    public int getVal() {
        return 0;
    }
}
