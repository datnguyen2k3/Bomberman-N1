package uet.oop.bomberman.entities.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.utils.State;

public abstract class Character extends Entity {

    private int delayTime = 600 ;
    protected boolean isDead = false;
    protected boolean isEnd = false;
    protected int worldX;
    protected int worldY;
    public static final int TIME_ANIMATION_RUNNING = 60;
    public static final int TIME_ANIMATION_DEAD = 80;
    public static final int TIME_DEAD = 80;
    public static final int TIME_RUNNING = 10;
    protected int currentTimeRunning = 0;
    protected int currentTimeDead = 0;
    protected int timeStop = 0;
    protected int MAX_TIME_STOP = 40;
    protected Sprite _sprite;
    protected State previousState;
    protected Sprite sprite_character_left, sprite_character_left_1, sprite_character_left_2, sprite_character_left_3, last_sprite_left;
    protected Sprite sprite_character_right, sprite_character_right_1, sprite_character_right_2, sprite_character_right_3, last_sprite_right;
    protected Sprite sprite_character_up, sprite_character_up_1, sprite_character_up_2;
    protected Sprite sprite_character_down, sprite_character_down_1, sprite_character_down_2;
    protected Sprite sprite_character_dead, sprite_character_dead_1, sprite_character_dead_2, sprite_character_dead_3;

    protected boolean running, goNorth, goSouth, goEast, goWest;
    protected boolean passBrick = false;
    protected boolean passFlame = false;

    public boolean getPassBrick() {
        return passBrick;
    }

    public boolean getPassFlame() {
        return passFlame;
    }

    public void setPassBrick() {
        if (passBrick)
            return;
        passBrick = true;
    }

    public void setPassFlame() {
        if (passFlame)
            return;
        passFlame = true;
    }

    public Character(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        initSprite();
        initState();
        initSolidArea();
    }

    public Character(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
    }

    public int get_xUnitCenter() {
        return (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public int get_yUnitCenter() {
        return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    protected abstract void initState();

    public void setDead() {
        if (isDead || isEnd)
            return;
        _state = State.DEAD;
        isDead = true;
        goEast = goNorth = goSouth = goWest = running = false;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setRandomSpeed() {
        if (isEnd)
            return;

        running = true;
        int maxSpeed = 2;
        speed = rand.nextInt(maxSpeed) % maxSpeed + 1;
        // System.out.println(speed);
    }

    protected void updateRunning() {
        if (isImpactWall()) {
            setRandomSpeed();
        }

        if (!running) {
            return;
        }

        currentTimeRunning++;
        if (currentTimeRunning > TIME_RUNNING) {
            currentTimeRunning = 0;
            running = false;
        }
    }

    /**
     * Determine which sprite before render.
     */
    protected void choosingSprite() {
        if (isEnd)
            return;
        switch (_state) {
            case GO_NORTH: {
                animate("up");
                _sprite = Sprite.movingSprite(sprite_character_up,
                        sprite_character_up_1,
                        sprite_character_up_2,
                        animateUp, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_SOUTH: {
                animate("down");
                _sprite = Sprite.movingSprite(sprite_character_down,
                        sprite_character_down_1,
                        sprite_character_down_2,
                        animateDown, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_EAST: {
                animate("right");
                _sprite = Sprite.movingSprite(sprite_character_right,
                        sprite_character_right_1,
                        last_sprite_right,
                        animateRight, TIME_ANIMATION_RUNNING);
                break;
            }
            case GO_WEST:
            case STATIONARY: {
                animate("left");
                _sprite = Sprite.movingSprite(sprite_character_left,
                        sprite_character_left_1,
                        last_sprite_left,
                        animateLeft, TIME_ANIMATION_RUNNING);

                break;
            }
            case DEAD: {
                if (timeStop < MAX_TIME_STOP) {
                    timeStop++;
                    _sprite = sprite_character_dead;
                }
                if (timeStop >= MAX_TIME_STOP) {
                    _sprite = Sprite.movingSprite(sprite_character_dead_1,
                            sprite_character_dead_2, sprite_character_dead_3,
                            animateDead, TIME_ANIMATION_DEAD);
                    animate("dead");
                    timeStop++;
                }
                break;
            }
            case IDLE: {
                if (previousState == State.GO_NORTH) {
                    _sprite = sprite_character_up;
                } else if (previousState == State.GO_SOUTH) {
                    _sprite = sprite_character_down;
                } else if (previousState == State.GO_EAST) {
                    _sprite = sprite_character_right;
                } else if (previousState == State.GO_WEST) {
                    _sprite = sprite_character_left;
                }
            }
        }
    }

    protected void updateCurrentState() {
        if (isEnd)
            return;

        if (isDead) _state = State.DEAD;
        else if (goNorth) _state = State.GO_NORTH;
        else if (goEast) _state = State.GO_EAST;
        else if (goSouth) _state = State.GO_SOUTH;
        else if (goWest) _state = State.GO_WEST;
        else _state = State.IDLE;

        if (isDead)
            currentTimeDead++;
        if (currentTimeDead >= TIME_DEAD + delayTime)
            isEnd = true;
    }

    public boolean isImpact(Character character) {
        return isImpact(character.getX() + character.getSolidArea().x,
                character.getY() + character.getSolidArea().y,
                character.getX() + character.getSolidArea().x + character.getSolidArea().width,
                character.getY() + character.getSolidArea().y + character.getSolidArea().height);
    }

    public boolean isImpact(int startX, int startY, int endX, int endY) {
        List<Pair<Integer, Integer>> points = pointsOfRectangle();

        for (Pair<Integer, Integer> point : points) {
            int xPoint = point.getKey();
            int yPoint = point.getValue();
            if (isPointInRectangle(xPoint, yPoint, startX, startY, endX, endY)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointInRectangle(int xPoint, int yPoint, int startX, int startY, int endX, int endY) {
        return startX < xPoint && xPoint < endX && startY < yPoint && yPoint < endY;
    }

    protected List<Pair<Integer, Integer>> pointsOfRectangle() {
        List<Pair<Integer, Integer>> points = new ArrayList<>();
        points.add(new Pair<>(solidArea.x + x, solidArea.y + y));
        points.add(new Pair<>(x + solidArea.x + solidArea.width, y + solidArea.y));
        points.add(new Pair<>(x + solidArea.x, y + solidArea.y + solidArea.height));
        points.add(new Pair<>(x + solidArea.x + solidArea.width, y + solidArea.y + solidArea.height));
        return  points;
    }

    public boolean isImpact(int xUnitOfCell, int yUnitOfCell) {
        return isImpact(xUnitOfCell * Sprite.SCALED_SIZE,
                yUnitOfCell * Sprite.SCALED_SIZE,
                xUnitOfCell * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE,
                yUnitOfCell * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE);
    }

    protected void updateCoordinate() {
        if (!isImpactWall()) {
            switch (_state) {
                case GO_NORTH: {

                    y -= speed;

                    break;
                }
                case GO_SOUTH: {

                    y += speed;

                    break;
                }
                case GO_EAST: {

                    x += speed;

                    break;
                }
                case GO_WEST: {

                    x -= speed;

                    break;
                }
            }
        }
    }

    @Override
    public void update() {
        if (isEnd)
            return;
        //animate();
        if (!isDead)
            updateCoordinate();
    }

    protected boolean isImpactWall() {
        isCollisionOn = false;
        this.game.collisionChecker.checkTile(this);
        return isCollisionOn;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isEnd)
            return;
        choosingSprite();
        this.img = _sprite.getFxImage();
        super.render(gc);
    }
}
