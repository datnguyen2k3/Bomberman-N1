package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {

    public BombItem(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
    }

    @Override
    protected void initSprite() {
        img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    protected void initItemDiagram() {
        itemDiagram = Item.bombItemDiagram;
    }

}
