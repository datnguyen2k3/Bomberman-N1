package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.UI.Menu.animationMenu.TextGraphics;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.awt.*;

public abstract class Enemy extends Character {


    private boolean addBonustime = false;
    public static final char balloomDiagram = '1';
    public static final char onealDiagram = '2';
    public static final char dollDiagram = '3';
    public static final char minvoDiagram = '4';
    public static final char doraDiagram = '5';
    public static final char catDiagram = '6';
    public static final char coinDiagram = '7';
    public static final char ghostDiagram = '8';
    public static final char redCoinDiagram = '9';
    public static final int lastEnemyDiagram = '9';

    protected int TIME_RANDOM_STATE = 300;
    private int currentTimeRandomState = 0;

    private Bomber bomber = game.getBomberman();

    public Enemy(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.speed = 1;
    }

    @Override
    public void initSolidArea() {
        solidArea = new Rectangle(0 * Sprite.SCALE, 0 * Sprite.SCALE, 15 * Sprite.SCALE, 15 * Sprite.SCALE);
    }


    public static boolean isEnemy(char diagram) {
        return '1' <= diagram && diagram <= lastEnemyDiagram;
    }

    @Override
    protected void initState() {
        setRandomState();
    }

    public void setRandomState() {
        if (isEnd)
            return;

        int choice = rand.nextInt(4);
        this._state = State.values()[choice];
    }

    @Override
    protected void updateCurrentState() {
        if (isImpactWall()) {
            setState();
        }

        updateRandomStateByTime();
    }

    public void updateRandomStateByTime() {
        if (isDead)
            return;
        currentTimeRandomState++;

        if (currentTimeRandomState > TIME_RANDOM_STATE) {
            currentTimeRandomState = 0;
            setRandomState();
        }
    }

    public void setState() {
        setRandomState();
    }

    @Override
    public void update() {
        super.update();
        updateCurrentState();
        if (isDead) {
            currentTimeDead++;
            if (currentTimeDead >= TIME_DEAD) {
                isEnd = true;
            }
        }
        if (_state == State.DEAD) {
            if (!addBonustime) {
                addBonustime = true;
                // update bonus time
                int bonusTime = Integer.parseInt(deadPoint.getText());
                int before = game.getRealCurrentTimeGame() / 60;
                game.updateCurrentTimeGame(game.getRealCurrentTimeGame() + bonusTime * 60);
                System.out.println(game.getRealCurrentTimeGame() / 60 - before);
            }
        }
    }


}
