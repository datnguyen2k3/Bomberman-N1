package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    private BombManagement bombManagement;
    private boolean isEnd = false;
    private boolean isWaitedToExploding = false;
    private boolean isExploded = false;
    public static final int TIME_WAIT_TO_EXPLODING = 60 * 2;
    public static final int timeExploding = 30;
    public static final int timeRefresh = Bomb.timeExploding + Bomb.TIME_WAIT_TO_EXPLODING;
    private int currentTimeWaitToExploding = TIME_WAIT_TO_EXPLODING;
    private int currentTimeExploding = timeExploding;
    public List<Pair<Integer, Integer>> explodedCells = new ArrayList<>();
    public List<Pair<Integer, Integer>> destroyedBricks = new ArrayList<>();

    private Sprite currentSprite = Sprite.bomb;


    private Sprite bomb = Sprite.bomb;
    private Sprite bomb1 = Sprite.bomb_1;
    private Sprite bomb2 = Sprite.bomb_2;

    private Sprite bombExploding = Sprite.bomb_exploded;
    private Sprite bombExploding1 = Sprite.bomb_exploded1;
    private Sprite bombExploding2 = Sprite.bomb_exploded2;


    public Bomb(int xUnit, int yUnit, BombManagement bombManagement) {
        super(xUnit, yUnit);
        _state = State.WAITING_EXPLODING;
        initSolidArea();
        this.bombManagement = bombManagement;
    }

    @Override
    public void initSolidArea() {

    }

    @Override
    protected void initSprite() {
        img = Sprite.bomb.getFxImage();
    }

    public boolean isWaitedToExploding() {
        return isWaitedToExploding;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void waitToExploding() {
        if (!isWaitedToExploding) {
            //System.out.println("waiting");
            img = Sprite.bomb.getFxImage();
            isWaitedToExploding = true;
            _state = State.WAITING_EXPLODING;
        }
        currentTimeWaitToExploding--;
    }

    private void explode() {
        if (!isExploded) {
            img = Sprite.bomb_exploded.getFxImage();
            _state = State.EXPLODING;
            isExploded = true;
            setExplodedCells();
        }
        currentTimeExploding--;
    }

    private void running() {
        waitToExploding();
        if (currentTimeWaitToExploding > 0)
            return;

        explode();
        if (currentTimeExploding > 0)
            return;

        isEnd = true;
    }

    private boolean setExplodedCell(int xUnit, int yUnit) {
        if (Wall.isWall(xUnit, yUnit))
            return false;
        if (Brick.isBrick(xUnit, yUnit)) {
            destroyedBricks.add(new Pair<>(xUnit, yUnit));
            return false;
        }
        explodedCells.add(new Pair<>(xUnit, yUnit));
        return true;
    }

    private void setExplodedCells() {
        setExplodedCell(get_xUnit(), get_yUnit());

        for (int j = get_xUnit() + 1; j <= get_xUnit() + bombManagement.getExplodedLength(); j++) {
            if (!setExplodedCell(j, get_yUnit()))
                break;
            else {

            }
        }

        for (int j = get_xUnit() - 1; j >= get_xUnit() - bombManagement.getExplodedLength(); j--) {
            if (!setExplodedCell(j, get_yUnit()))
                break;
        }

        for (int i = get_yUnit() + 1; i <= get_yUnit() + bombManagement.getExplodedLength(); i++) {
            if (!setExplodedCell(get_xUnit(), i))
                break;
        }

        for (int i = get_yUnit() - 1; i >= get_yUnit() - bombManagement.getExplodedLength(); i--) {
            if (!setExplodedCell(get_xUnit(), i))
                break;
        }
    }


    public void choosingSprite() {

        switch (_state) {
            case WAITING_EXPLODING: {
                currentSprite = Sprite.movingSprite(bomb, bomb1, bomb2, _animate, TIME_WAIT_TO_EXPLODING / 2);
                break;
            }
            case EXPLODING: {
                currentSprite = Sprite.movingSprite(bombExploding, bombExploding1, bombExploding2, _animate, timeExploding / 2);
                break;
            }
        }
    }

    public void draw4SideFlame(GraphicsContext gc) {
        Sprite curFlameSprite;
        if (_state == State.EXPLODING) {
            for (int j = get_xUnit() + 1; j <= get_xUnit() + bombManagement.getExplodedLength(); j++) {
                if (!setExplodedCell(j, get_yUnit()))
                    break;
                else {
                    if (j == get_xUnit() + bombManagement.getExplodedLength()) {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last
                                , Sprite.explosion_horizontal_right_last1
                                , Sprite.explosion_horizontal_right_last2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , j * Sprite.SCALED_SIZE
                                , get_yUnit() * Sprite.SCALED_SIZE);
                    } else {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal
                                , Sprite.explosion_horizontal1
                                , Sprite.explosion_horizontal2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , j * Sprite.SCALED_SIZE
                                , get_yUnit() * Sprite.SCALED_SIZE);
                    }
                }
            }

            for (int j = get_xUnit() - 1; j >= get_xUnit() - bombManagement.getExplodedLength(); j--) {
                if (!setExplodedCell(j, get_yUnit()))
                    break;
                else {

                    if (j == get_xUnit() - bombManagement.getExplodedLength()) {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last
                                , Sprite.explosion_horizontal_left_last1
                                , Sprite.explosion_horizontal_left_last2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , j * Sprite.SCALED_SIZE
                                , get_yUnit() * Sprite.SCALED_SIZE);
                    } else {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal
                                , Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , j * Sprite.SCALED_SIZE
                                , get_yUnit() * Sprite.SCALED_SIZE);
                    }
                }
            }

            for (int i = get_yUnit() + 1; i <= get_yUnit() + bombManagement.getExplodedLength(); i++) {
                if (!setExplodedCell(get_xUnit(), i))
                    break;
                else {
                    if (i == get_yUnit() + bombManagement.getExplodedLength()) {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last
                                , Sprite.explosion_vertical_down_last1
                                , Sprite.explosion_vertical_down_last2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , get_xUnit() * Sprite.SCALED_SIZE
                                , i * Sprite.SCALED_SIZE);
                    } else {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical
                                , Sprite.explosion_vertical1
                                , Sprite.explosion_vertical2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , get_xUnit() * Sprite.SCALED_SIZE
                                , i * Sprite.SCALED_SIZE);
                    }
                }
            }

            for (int i = get_yUnit() - 1; i >= get_yUnit() - bombManagement.getExplodedLength(); i--) {
                if (!setExplodedCell(get_xUnit(), i))
                    break;
                else {
                    if (i == get_yUnit() - bombManagement.getExplodedLength()) {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last
                                , Sprite.explosion_vertical_top_last1
                                , Sprite.explosion_vertical_top_last2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , get_xUnit() * Sprite.SCALED_SIZE
                                , i * Sprite.SCALED_SIZE);
                    } else {
                        curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical
                                , Sprite.explosion_vertical1
                                , Sprite.explosion_vertical2, _animate, timeExploding / 2);
                        gc.drawImage(curFlameSprite.getFxImage()
                                , get_xUnit() * Sprite.SCALED_SIZE
                                , i * Sprite.SCALED_SIZE);
                    }
                }
            }
        }

    }

    @Override
    public void update() {
        animate();
        running();
    }

    @Override
    public void render(GraphicsContext gc) {
        choosingSprite();
        if (isEnd)
            return;
        gc.drawImage(currentSprite.getFxImage(), x, y);
        draw4SideFlame(gc);
    }

}
