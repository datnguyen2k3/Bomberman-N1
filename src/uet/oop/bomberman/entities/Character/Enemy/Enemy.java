package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.awt.*;

public abstract class Enemy extends Character {
    public static final char balloomDiagram = '1';
    public static final char onealDiagram = '2';

    public String type ;

    public Enemy(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.speed = 1;
        initType();
    }

    @Override
    public void initSolidArea() {
        solidArea = new Rectangle(0 * Sprite.SCALE, 0 * Sprite.SCALE, 15 * Sprite.SCALE , 15 * Sprite.SCALE);
    }

    public abstract void initType();

    public static boolean isEnemy(char diagram) {
        return diagram == balloomDiagram
                || diagram == onealDiagram;
    }

    @Override
    public void setDead() {
        isDead = true;
        _state = State.DEAD;
    }

    public void setRandomState() {
        if(isEnd)
            return;

        int choice = rand.nextInt(4);
        this._state = State.values()[choice];
    }

    @Override
    public void update() {
        super.update();


        if (isImpactWall()) {
            setRandomState();
        }

        if(isDead) {
            currentTimeDead++;
            if(currentTimeDead >= TIME_DEAD) {
                isEnd = true;
            }
        }

    }
}
