package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item{
    public FlameItem(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
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
