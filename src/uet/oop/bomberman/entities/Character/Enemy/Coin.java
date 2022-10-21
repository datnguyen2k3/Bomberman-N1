package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Coin extends Enemy{
    public Coin(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        setPassBrick();
    }

    @Override
    protected void initSprite() {

    }
}
