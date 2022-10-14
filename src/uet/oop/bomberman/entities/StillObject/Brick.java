package uet.oop.bomberman.entities.StillObject;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;
import uet.oop.bomberman.BombermanGame;
public class Brick extends Entity {
    public static final char diagramBrick = '*';
    private boolean isDestroyed = false;
    public static int timeDead = 60;
    private int currentTimeDead = 0;
    private Sprite curSprite = Sprite.brick;

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
        _state = State.BE_DESTROYING;
        //BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
        img = Sprite.grass.getFxImage();
    }


    private void updateState() {
        if (_state == State.BE_DESTROYED || _state == State.EXISTING) {
            return;
        }

        if (_state == State.BE_DESTROYING) {
            currentTimeDead++;
        }

        if (currentTimeDead >= timeDead)
            _state = State.BE_DESTROYED;

    }

    private void chooseSprite() {

        if ( _state == State.EXISTING) {
            img = Sprite.brick.getFxImage();
        }
        else if ( _state == State.BE_DESTROYING) {
            img = Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,_animate,120).getFxImage();
        }
        else if (_state == State.BE_DESTROYED){
           img = Sprite.grass.getFxImage();
        }
    }


    private void doExplodingAnimation(GraphicsContext gc) {
        if ( _state == State.BE_DESTROYING) {
            gc.drawImage(Sprite.movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2,_animate,60).getFxImage(),x,y);
        }
        else if ( _state == State.EXISTING) {
            gc.drawImage(Sprite.brick.getFxImage(),x,y);
        }
        else {
            gc.drawImage(Sprite.grass.getFxImage(), x,y);
        }
    }


    @Override
    public void update() {
        updateState();
        animate();
    }

    public void update(Bomber bomber) {
        updateState();
        animate();
        // System.out.println(_animate);
    }

    @Override
    protected void initSprite() {
        img = Sprite.brick.getFxImage();
    }

    @Override
    public void render(GraphicsContext gc) {
        //doExplodingAnimation(gc);
         //chooseSprite();
        //gc.drawImage(curSprite.getFxImage(),x,y);
        super.render(gc);
    }
}
