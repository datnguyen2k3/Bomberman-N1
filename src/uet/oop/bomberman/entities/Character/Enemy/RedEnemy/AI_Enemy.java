package uet.oop.bomberman.entities.Character.Enemy.RedEnemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Algorithm.CanGo;
import uet.oop.bomberman.entities.Algorithm.MinPath;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

public abstract class AI_Enemy extends Enemy {

    public AI_Enemy(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.x = xUnit * Sprite.SCALED_SIZE + 1;
        this.y = yUnit * Sprite.SCALED_SIZE + 1;
        this.speed = 4;
    }

    @Override
    protected void initState() {
        _state = State.STATIONARY;
    }

    // find min path from doll to bomberman
    @Override
    public void updateCurrentState() {

        if (isEnd || isDead)
            return;

        if (x % Sprite.SCALED_SIZE != 1 || y % Sprite.SCALED_SIZE != 1)
            return;

        int direction = MinPath.findDirection(this, game.getBomberman().get_xUnitCenter(), game.getBomberman().get_yUnitCenter());
        //System.out.println(direction);

        if (direction == CanGo.CANT_MOVE) {
            setRandomState();
            return;
        }

        switch (direction) {
            case 3:
                _state = State.GO_NORTH;
                break;
            case 4:
                _state = State.GO_SOUTH;
                break;
            case 1:
                _state = State.GO_WEST;
                break;
            case 2:
                _state = State.GO_EAST;
                break;
        }
    }
}
