package uet.oop.bomberman.entities.Items;

import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    @Override
    public void initSolidArea() {

    }

    public SpeedItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initSolidArea();
    }

    @Override
    protected void initSprite() {
        img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void update() {

    }

    @Override
    protected void initItemDiagram() {
        itemDiagram = Item.speedItemDiagram;
    }

    @Override
    public int getVal() {
        return 0;
    }
}
