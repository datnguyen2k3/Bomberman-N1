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
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import uet.oop.bomberman.utils.State;
import uet.oop.bomberman.BombermanGame;
public class Bomber extends Character {
    private BombManagement bombManagement;
    private boolean isBombermanKillAllEnemies = false;

    //private static final int EPSILON = 2 * Sprite.SCALE;

    private int entityLeftSideX;
    private int entityRightSideX ;
    private int entityTopY;
    private int entityBottomY;

    public Bomber(int x, int y, Image img, BombermanGame game) {
        this(x, y, img);
        this.game = game;
        bombManagement = new BombManagement(this.game);
    }

    @Override
    public void initSolidArea() {
        solidArea = new Rectangle(4 * Sprite.SCALE, 4 * Sprite.SCALE, 6 * Sprite.SCALE, 9 * Sprite.SCALE);
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

    public BombManagement getBombManagement() {
        return bombManagement;
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
                speed += 2;
                break;
            case Item.flameItemDiagram:
                bombManagement.powerUpFlameBomb();
                break;
        }
    }

    public void setBomberWin() {

    }

    @Override
    public void update() {
        if (isEnd)
            return;

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
