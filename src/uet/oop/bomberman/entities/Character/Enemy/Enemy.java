package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.awt.*;

public abstract class Enemy extends Character {
    public static final char balloomDiagram = '1';
    public static final char onealDiagram = '2';
    public static final char dollDiagram = '3';
    public static final char minvoDiagram = '4';
    public static final char doraDiagram = '5';
    public static char lastEnemy = '5';

    Bomber bomber = game.getBomberman();

    public Enemy(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.speed = 1;
    }

    @Override
    public void initSolidArea() {
        solidArea = new Rectangle(0 * Sprite.SCALE, 0 * Sprite.SCALE, 15 * Sprite.SCALE , 15 * Sprite.SCALE);
    }


    public static boolean isEnemy(char diagram) {
        return '1' <= diagram && diagram <= lastEnemy;
    }

    @Override
    protected void initState() {
        setRandomState();
    }

    public void setRandomState() {
        if(isEnd)
            return;

        int choice = rand.nextInt(4);
        this._state = State.values()[choice];
    }

    public void setState() {
        setRandomState();
    }

    @Override
    public void update() {
        super.update();


        if (isImpactWall()) {
            setState();
        }

        if(isDead) {
            currentTimeDead++;
            if(currentTimeDead >= TIME_DEAD) {
                isEnd = true;
            }
        }

    }
}
