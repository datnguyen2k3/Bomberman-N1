package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {

    public BombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
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
