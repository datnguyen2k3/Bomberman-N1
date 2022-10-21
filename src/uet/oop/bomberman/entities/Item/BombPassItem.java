package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class BombPassItem extends Item {
    public BombPassItem(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
    }

    @Override
    protected void initSprite() {
        this.img = Sprite.powerup_bombpass.getFxImage();
    }

    @Override
    protected void initItemDiagram() {
        this.itemDiagram = bombPassDiagram;
    }
}
