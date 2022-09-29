package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    private BombManagement bombManagement;
    private boolean isEnd = false;
    private boolean isWaitedToExploding = false;
    private boolean isExploded = false;
    public static final int timeWaitToExploding = 60 * 2;
    public static final int timeExploding = 30;
    public static final int timeRefresh = Bomb.timeExploding + Bomb.timeWaitToExploding;
    private int currentTimeWaitToExploding = timeWaitToExploding;
    private int currentTimeExploding = timeExploding;
    public List<Pair<Integer, Integer>> explodedCells = new ArrayList<>();
    public List<Pair<Integer, Integer>> destroyedBricks = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, BombManagement bombManagement) {
        super(xUnit, yUnit);
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
            img = Sprite.bomb.getFxImage();
            isWaitedToExploding = true;
        }
        currentTimeWaitToExploding--;
    }

    private void explode() {
        if (!isExploded) {
            img = Sprite.bomb_exploded.getFxImage();
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

    @Override
    public void update() {
        running();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isEnd)
            return;
        super.render(gc);
    }


    @Override
    public int getVal() {
        return 0;
    }
}
