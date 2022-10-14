package uet.oop.bomberman.entities.Character;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.BombManagement;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Grass;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import uet.oop.bomberman.utils.State;
import uet.oop.bomberman.BombermanGame;

public class Bomber extends Character {
    private static final int EPSILON = 10 * Sprite.SCALE;

    private int speedMoveAtEdgeDivideBy = 15;
    private BombManagement bombManagement;
    private boolean isBombermanKillAllEnemies = false;
    private int hp = 3;
    private boolean isWin;

    private int entityLeftSideX;
    private int entityRightSideX;
    private int entityTopY;
    private int entityBottomY;

    public Bomber(int x, int y, Image img, BombermanGame game) {
        this(x, y, img);
        this.game = game;
        bombManagement = new BombManagement(this.game);
    }

    @Override
    public void initSolidArea() {
        solidArea = new Rectangle(0 * Sprite.SCALE, 0 * Sprite.SCALE, 10 * Sprite.SCALE, 14 * Sprite.SCALE);
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        initSolidArea();
        this.worldX = x;
        this.worldY = y;
        initSprite();
        initState();
//        entityLeftSideX = getX() + solidArea.x;
//        entityRightSideX = entityLeftSideX + solidArea.width;
//        entityTopY = getY() + solidArea.y;
//        entityBottomY = entityTopY + solidArea.height;
    }

    private boolean isBrickOrWall(int x, int y) {
        return (Wall.isWall(get_xUnit(x), get_yUnit(y))
                || Brick.isBrick(get_xUnit(x), get_yUnit(y)));
    }

    private void smoothMovement() {
        //  Brick brick = new Brick(0, 0);
        if (isCollisionOn == true) {

            // down
            if (_state == State.GO_SOUTH
                    && Grass.isGrass(entityLeftSideX + EPSILON, entityTopY + Sprite.SCALED_SIZE + EPSILON)
                    && Grass.isGrass(entityLeftSideX + EPSILON, entityTopY + EPSILON)
                    && isBrickOrWall(entityLeftSideX, entityBottomY + EPSILON)) {

                this.x += EPSILON / speedMoveAtEdgeDivideBy;
                return;
            }
            if (_state == State.GO_SOUTH
                    && Grass.isGrass(entityRightSideX - EPSILON, entityTopY + Sprite.SCALED_SIZE + EPSILON)
                    && Grass.isGrass(entityRightSideX - EPSILON, entityTopY + EPSILON)
                    && isBrickOrWall(entityRightSideX, entityBottomY + EPSILON)) {

                this.x -= EPSILON / speedMoveAtEdgeDivideBy;
                return;
            }

            // up
            if (_state == State.GO_NORTH
                    && Grass.isGrass(entityLeftSideX + EPSILON, entityTopY - EPSILON)
                    && Grass.isGrass(entityLeftSideX + EPSILON, entityTopY)
                    && isBrickOrWall(entityLeftSideX, entityTopY - EPSILON)) {

                this.x += EPSILON / speedMoveAtEdgeDivideBy;
                return;
            }

            if (_state == State.GO_NORTH
                    && Grass.isGrass(entityRightSideX - EPSILON, entityTopY - EPSILON)
                    && Grass.isGrass(entityRightSideX - EPSILON, entityTopY)
                    && isBrickOrWall(entityRightSideX, entityTopY - EPSILON)) {

                this.x -= EPSILON / speedMoveAtEdgeDivideBy;
                return;
            }

            // right
            if (_state == State.GO_EAST
                    && Grass.isGrass(entityRightSideX + EPSILON, entityBottomY - EPSILON)
                    && Grass.isGrass(entityRightSideX, entityBottomY - EPSILON)
                    && isBrickOrWall(entityRightSideX + EPSILON, entityBottomY)) {

                this.y -= EPSILON / speedMoveAtEdgeDivideBy;
                return;
            } else if (_state == State.GO_EAST
                    && Grass.isGrass(entityRightSideX + EPSILON, entityTopY + EPSILON)
                    && Grass.isGrass(entityRightSideX, entityTopY + EPSILON)
                    && isBrickOrWall(entityRightSideX + EPSILON, entityTopY)) {

                this.y += EPSILON / speedMoveAtEdgeDivideBy;
                return;
            }

            // left
            if (_state == State.GO_WEST
                    && Grass.isGrass(entityLeftSideX - EPSILON, entityBottomY - EPSILON)
                    && Grass.isGrass(entityLeftSideX, entityBottomY - EPSILON)
                    && isBrickOrWall(entityLeftSideX - EPSILON, entityBottomY)) {
                this.y -= EPSILON / speedMoveAtEdgeDivideBy;
                return;
            } else if (_state == State.GO_WEST
                    && Grass.isGrass(entityLeftSideX - EPSILON, entityTopY + EPSILON)
                    && Grass.isGrass(entityLeftSideX, entityTopY + EPSILON)
                    && isBrickOrWall(entityLeftSideX - EPSILON, entityTopY)) {
                this.y += EPSILON / speedMoveAtEdgeDivideBy;
                return;
            }
        }
    }

    public BombManagement getBombManagement() {
        return bombManagement;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

//    private void setCoordinateWhenAtEdge(String dir) {
//        if ( dir.equals("GO SOUTH")) {
//            isCollisionOn = false;
//            this.game.collisionChecker.checkTile(this);
//
//        }
//    }

    @Override
    protected void initSprite() {

        this.last_sprite_left = Sprite.player_left_2;
        this.last_sprite_right = Sprite.player_right_2;

        this.sprite_character_up = Sprite.player_up;
        this.sprite_character_up_1 = Sprite.player_up_1;
        this.sprite_character_up_2 = Sprite.player_up_2;

        this.sprite_character_down = Sprite.player_down;
        this.sprite_character_down_1 = Sprite.player_down_1;
        this.sprite_character_down_2 = Sprite.player_down_2;

        this.sprite_character_left = Sprite.player_left;
        this.sprite_character_left_1 = Sprite.player_left_1;
        this.sprite_character_left_2 = Sprite.player_left_2;

        this.sprite_character_right = Sprite.player_right;
        this.sprite_character_right_1 = Sprite.player_right_1;
        this.sprite_character_right_2 = Sprite.player_right_2;

        this.sprite_character_dead = Sprite.player_dead1;
        this.sprite_character_dead_1 = Sprite.player_dead2;
        this.sprite_character_dead_2 = Sprite.player_dead3;


        this._sprite = this.sprite_character_right;
    }

    @Override
    public void setDead() {
        if (isDead) {
            return;
        }

        super.setDead();
        hp--;
    }

    @Override
    protected void initState() {
        this._state = State.GO_EAST;
    }

    public boolean isBombermanKillAllEnemies() {
        return isBombermanKillAllEnemies;
    }

    public void setBombermanKillAllEnemies() {
        isBombermanKillAllEnemies = true;
    }


    public void updateInput(Scene scene) {
//        System.out.println(speed);
        if (isDead)
            return;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                    case UP:
                        _state = State.GO_NORTH;
                        goNorth = true;
                        break;
                    case S:
                    case DOWN:
                        _state = State.GO_SOUTH;
                      //   System.out.println(_state);
                        goSouth = true;
                        break;
                    case A:
                    case LEFT:
                        _state = State.GO_WEST;
                        goWest = true;
                        break;
                    case D:
                    case RIGHT:
                        _state = State.GO_EAST;
                        goEast = true;
                        break;
                    case SHIFT:
                        running = true;
                        break;
                }

                if (event.getCode() == KeyCode.SPACE) {
                    Bomb b = new Bomb(get_xUnitCenter(), get_yUnitCenter(), bombManagement, game);
                    bombManagement.add(b);
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

    public void takeItem(Item item) {
        if (isDead)
            return;

        switch (item.getDiagramItem()) {
            case Item.bombItemDiagram:
                bombManagement.powerUpMaxBomb();
                break;
            case Item.speedItemDiagram:
                speed += 1;
                break;
            case Item.flameItemDiagram:
                bombManagement.powerUpFlameBomb();
                break;
            case Item.portalItemDiagram:
                if (isBombermanKillAllEnemies()) {
                    setBomberWin();
                }
                break;
        }
    }

    public void setBomberWin() {
        isWin = true;
    }

    public boolean isWin() {
        return isWin;
    }

    @Override
    public void update() {
        if (isEnd)
            return;

        entityLeftSideX = x + solidArea.x;
        entityRightSideX = entityLeftSideX + solidArea.width;
        entityTopY = y + solidArea.y;
        entityBottomY = entityTopY + solidArea.height;

        updateCurrentState();
        isCollisionOn = false;
        game.collisionChecker.checkTile(this);
        if (!isCollisionOn) {
            switch (_state) {
                case GO_NORTH: {
                    y -= speed;
                    break;
                }
                case GO_SOUTH: {
                   // System.out.println("Going south");
                    y += speed;
                   //  System.out.println("Increase y coordinate");
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
        smoothMovement();
        super.update();
        bombManagement.update();

    }

    @Override
    public void render(GraphicsContext gc) {
        updateCurrentState();
        bombManagement.render(gc);
        super.render(gc);
    }


}
