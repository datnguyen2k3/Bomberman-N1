package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity{
    private int explodedLength = 1;
    private boolean isActivated = false;
    private boolean isWaitedToExploding = false;
    private boolean isExploded = false;
    public static final int timeWaitToExploding = 60 * 2;
    public static final int timeExploding = 60;
    private int currentTimeWaitToExploding = timeWaitToExploding;
    private int currentTimeExploding = timeExploding;
    public List<Pair<Integer, Integer>> explodedCells = new ArrayList<>();
    public List<Pair<Integer, Integer>> destroyedBricks = new ArrayList<>();


    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean isWaitedToExploding() {
        return isWaitedToExploding;
    }
    public boolean isExploded() {
        return isExploded;
    }
    public boolean isActivated() {
        return isActivated;
    }

    public void start(int xUnit, int yUnit) {
        if (isActivated) {
            return;
        }
        super.setCoordinate(xUnit, yUnit);
        BombermanGame.diagramMap[yUnit][xUnit] = Wall.diagramWall;
        isActivated = true;
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
        if (!isActivated)
            return;

        waitToExploding();
        if (currentTimeWaitToExploding > 0)
            return;

        explode();
        if (currentTimeExploding > 0)
            return;

        reset();
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

        for (int j = get_xUnit() + 1; j <= get_xUnit() + explodedLength; j++) {
            if(!setExplodedCell(j, get_yUnit()))
                break;
        }

        for (int j = get_xUnit() - 1; j >= get_xUnit() - explodedLength; j--) {
            if(!setExplodedCell(j, get_yUnit()))
                break;
        }

        for (int i = get_yUnit() + 1; i <= get_yUnit() + explodedLength; i++) {
            if(!setExplodedCell(get_xUnit(), i))
                break;
        }

        for (int i = get_yUnit() - 1; i >= get_yUnit() - explodedLength; i--) {
            if(!setExplodedCell(get_xUnit(), i))
                break;
        }

    }

    @Override
    public void update() {
        running();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!isActivated)
            return;
        super.render(gc);
    }

    public void reset() {
        isActivated = false;
        isExploded = false;
        isWaitedToExploding = false;
        currentTimeWaitToExploding = timeWaitToExploding;
        currentTimeExploding = timeExploding;
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
        destroyedBricks.clear();
        explodedCells.clear();
    }
}
