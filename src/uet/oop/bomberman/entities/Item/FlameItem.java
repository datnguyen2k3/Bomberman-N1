package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item{
    @Override
    public void initSolidArea() {

    }

    public FlameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initSolidArea();
    }

    @Override
    protected void initItemDiagram() {
        itemDiagram = Item.flameItemDiagram;
    }

    @Override
    protected void initSprite() {
        img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void update() {

    }

}
