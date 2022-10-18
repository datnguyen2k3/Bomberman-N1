package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.graphics.Sprite;

public class PassBrickItem extends Item {
    public PassBrickItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    protected void initSprite() {
        this.img = Sprite.powerup_wallpass.getFxImage();
    }

    @Override
    protected void initItemDiagram() {
        this.itemDiagram = Item.passBrickDiagram;
    }
}
