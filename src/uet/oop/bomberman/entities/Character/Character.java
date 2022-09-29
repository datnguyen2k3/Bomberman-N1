package uet.oop.bomberman.entities.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillObject.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uet.oop.bomberman.utils.State;

public abstract class Character extends Entity {
    protected int worldX;
    protected int worldY;
    public static final int TIME_ANIMATION_RUNNING = 50;
    public static final int TIME_ANIMATION_DEAD = 100;

    protected State previousState;

    protected Sprite _currentSprite;
    protected Sprite sprite_character_left, sprite_character_left_1, sprite_character_left_2, sprite_character_left_3, last_currentSprite_left;
    protected Sprite sprite_character_right, sprite_character_right_1, sprite_character_right_2, sprite_character_right_3, last_currentSprite_right;
    protected Sprite sprite_character_up, sprite_character_up_1, sprite_character_up_2;
    protected Sprite sprite_character_down, sprite_character_down_1, sprite_character_down_2;
    protected Sprite sprite_character_dead;

    protected boolean running, goNorth, goSouth, goEast, goWest;

    public Character(int xUnit, int yUnit, Image img, BombermanGame game) {
        this(xUnit, yUnit, img);
        this.game = game;
    }

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        initSprite();
        initState();
    }

    public int get_xUnitCenter() {
        return (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }
    public int get_yUnitCenter() {
        return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    protected abstract void initState();

    /**
     * Determine which sprite before render to screen.
     */
    protected void choosingSprite() {
        switch (_state) {
            case GO_NORTH: {
                _currentSprite = Sprite.movingSprite(sprite_character_up, sprite_character_up_1,
                        sprite_character_up_2, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_SOUTH: {
                _currentSprite = Sprite.movingSprite(sprite_character_down, sprite_character_down_1,
                        sprite_character_down_2, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_EAST: {
                _currentSprite = Sprite.movingSprite(sprite_character_right, sprite_character_right_1,
                        last_currentSprite_right, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_WEST: {
                _currentSprite = Sprite.movingSprite(sprite_character_left, sprite_character_left_1,
                        last_currentSprite_left, _animate, TIME_ANIMATION_RUNNING);
                break;
            }
            case DEAD: {
                _currentSprite = Sprite.movingSprite(Sprite.player_dead1,
                        Sprite.player_dead2, Sprite.player_dead3, _animate, TIME_ANIMATION_DEAD);
                break;
            }
            case IDLE: {
                if (previousState == State.GO_NORTH) {
                    _currentSprite = sprite_character_up;
                } else if (previousState == State.GO_SOUTH) {
                    _currentSprite = sprite_character_down;
                } else if (previousState == State.GO_EAST) {
                    _currentSprite = sprite_character_right;
                } else if (previousState == State.GO_WEST) {
                    _currentSprite = sprite_character_left;
                }
            }
        }
    }

    protected void updateCurrentState() {
        if (goNorth) _state = State.GO_NORTH;
        else if (goEast) _state = State.GO_EAST;
        else if (goSouth) _state = State.GO_SOUTH;
        else if (goWest) _state = State.GO_WEST;
        else _state = State.IDLE;
    }


    public boolean isImpact(int startX, int startY) {
        List<Pair<Integer, Integer>> points = new ArrayList<>();
        points.add(new Pair<>(x, y));
        points.add(new Pair<>(x + Sprite.SCALED_SIZE, y));
        points.add(new Pair<>(x, y + Sprite.SCALED_SIZE));
        points.add(new Pair<>(x + Sprite.SCALED_SIZE, y + Sprite.SCALED_SIZE));

        for (Pair<Integer, Integer> point : points) {
            int xPoint = point.getKey();
            int yPoint = point.getValue();

            if (startX <= xPoint && xPoint <= startX + Sprite.SCALED_SIZE
                    && startY <= yPoint && yPoint <= startY + Sprite.SCALED_SIZE) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void update() {
        animate();
    }

    @Override
    public void render(GraphicsContext gc) {
        choosingSprite();
        gc.drawImage(_currentSprite.getFxImage(), x, y);
    }
}
