package uet.oop.bomberman.entities.Character.Enemy.BlueEnemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;

import java.util.Random;

public abstract class Speed_Change_Enemy extends Enemy {

    protected int TimeChangeSpeed = 240;
    protected int currentTimeChangeSpeed = 0;

    public Speed_Change_Enemy(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
    }

    public void setRandomSpeed() {
        Random random = new Random();
        speed = random.nextInt(3) + 1;
    }

    @Override
    public void update() {
        super.update();
        updateSpeed();
    }

    protected void updateSpeed() {
        if (isImpactWall()) {
            setRandomSpeed();
        }

        currentTimeChangeSpeed++;
        if (currentTimeChangeSpeed > TimeChangeSpeed) {
            currentTimeChangeSpeed = 0;
            setRandomSpeed();
        }

    }
}
