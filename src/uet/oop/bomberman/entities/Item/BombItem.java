package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item{
    @Override
    public void initSolidArea() {

    }

    public BombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initSolidArea();
    }

    @Override
    protected void initSprite() {
        img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public void update() {

    }

    @Override
    protected void initItemDiagram() {
        itemDiagram = Item.bombItemDiagram;
    }

    @Override
    public int getVal() {
        return 0;
    }
}
