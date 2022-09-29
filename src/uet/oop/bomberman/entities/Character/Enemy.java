package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

public abstract class Enemy extends Character {

    public String type ;

    public void setRandomState() {
        int choice = rand.nextInt(4);
        this._state = State.values()[choice];
    }

    public  void setRandomSpeed() {
        int randSpeed = rand.nextInt(3);
        speed = randSpeed;
        if (speed == 0 ) {
            speed = 1;
        }
    }

    @Override
    public void update() {
        isCollisionOn = false;
        this.game.collisionChecker.checkTile(this);
        if (isCollisionOn == false) {
            switch (this._state) {
                case GO_NORTH: {
                    y -= speed;
                    break;
                }
                case GO_SOUTH: {
                    y += speed;
                    break;
                }
                case GO_EAST: {
                    x += speed;
                    break;
                }
                case GO_WEST: {
                    x -= speed;
                    break;
                }
            }
            recentDistanceMoving += speed;
            if (recentDistanceMoving >= distanceToChangeSpeed) {
                if ( type.equals("Oneal")) {
                    setRandomSpeed();
                }
                recentDistanceMoving = 0;
                distanceToChangeSpeed = rand.nextInt(200*Sprite.SCALE - 60*Sprite.SCALE) + 60*Sprite.SCALE;
            }
        } else {
            if (type.equals("Balloom")) {
                setRandomState();
            }
            else if (type.equals("Oneal")) {
                setRandomSpeed();
                setRandomState();
            }
        }
        super.update();
    }

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
