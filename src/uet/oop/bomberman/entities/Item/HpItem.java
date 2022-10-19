package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class HpItem extends Item{
    public HpItem(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
    }

    @Override
    protected void initSprite() {
        this.img = Sprite.powerup_detonator.getFxImage();
    }

    @Override
    protected void initItemDiagram() {
        itemDiagram = Item.hpItemDiagram;
    }
}
