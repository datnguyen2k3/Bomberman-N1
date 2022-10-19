package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class FlamePassItem extends Item {
    public FlamePassItem(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
    }

    @Override
    protected void initSprite() {
        this.img = Sprite.powerup_flamepass.getFxImage();
    }

    @Override
    protected void initItemDiagram() {
        this.itemDiagram = Item.flamePassDiagram;
    }
}
