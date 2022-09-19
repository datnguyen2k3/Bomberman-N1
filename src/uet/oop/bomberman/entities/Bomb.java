package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity{
    private int length = 1;
    private boolean isActivated = false;
    private boolean isWaitedToExploding = false;
    private boolean isExploded = false;
    public static final int timeWaitToExploding = 60 * 2;
    public static final int timeExploding = 60 * 2;
    private int currentTimeWaitToExploding = timeWaitToExploding;
    private int currentTimeExploding = timeExploding;

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
        isActivated = true;
    }

    private void running() {
        if (!isActivated)
            return;

        if (!isWaitedToExploding) {
            img = Sprite.bomb.getFxImage();
            isWaitedToExploding = true;
        }

        currentTimeWaitToExploding--;
        if (currentTimeWaitToExploding > 0)
            return;

        if (!isExploded) {
            img = Sprite.bomb_exploded.getFxImage();
            isExploded = true;
        }

        currentTimeExploding--;
        if (currentTimeExploding > 0)
            return;

        reset();
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
    }
}
