package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item{
    public FlameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    protected void initItemDiagram() {
        itemDiagram = Item.flameItemDiagram;
    }

    @Override
    protected void initSprite() {
        img = Sprite.powerup_flames.getFxImage();
    }


}
