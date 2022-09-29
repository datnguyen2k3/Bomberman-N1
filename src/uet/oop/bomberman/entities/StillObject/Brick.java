package uet.oop.bomberman.entities.StillObject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public static final char diagramBrick = '*';
    private boolean isDestroyed = false;

    @Override
    public void initSolidArea() {

    }

    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initSolidArea();
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = '*';
    }

    public static boolean isBrick(char diagram) {
        return diagram == Brick.diagramBrick;
    }
    public static boolean isBrick(int xUnit, int yUnit) {
        return isBrick(BombermanGame.diagramMap[yUnit][xUnit]);
    }

    public void setDestroyed() {
        this.isDestroyed = true;
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
        img = Sprite.grass.getFxImage();
    }

    @Override
    public void update() {

    }

    public void update(Bomber bomber) {
        //updateGetDamage(bomber);
    }

    @Override
    protected void initSprite() {
        img = Sprite.brick.getFxImage();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
