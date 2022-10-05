package uet.oop.bomberman.entities.StillObject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

public class Brick extends Entity {
    public static final char diagramBrick = '*';
    private boolean isDestroyed = false;
    Sprite currentSprite = Sprite.brick;

    @Override
    public void initSolidArea() {

    }

    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initSolidArea();
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = '*';
        _state = State.EXISTING;
    }

    public static boolean isBrick(char diagram) {
        return diagram == Brick.diagramBrick;
    }
    public static boolean isBrick(int xUnit, int yUnit) {
        return isBrick(BombermanGame.diagramMap[yUnit][xUnit]);
    }

    public void setDestroyed() {
        this.isDestroyed = true;
        _state = State.BE_DESTROYED;
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
        img = Sprite.grass.getFxImage();
    }


    public void choosingSprite() {
        if(_state == State.BE_DESTROYED) {
            currentSprite = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,_animate,30);
        }
        else {
            currentSprite = Sprite.brick;
        }
    }
    @Override
    public void update() {

    }

    public void update(Bomber bomber) {
        //updateGetDamage(bomber);
        animate();
    }

    @Override
    protected void initSprite() {
        img = Sprite.brick.getFxImage();
    }

    @Override
    public void render(GraphicsContext gc) {
        animate();
        // choosingSprite();
//        gc.drawImage(currentSprite.getFxImage(),x,y);
        super.render(gc);
    }


}
