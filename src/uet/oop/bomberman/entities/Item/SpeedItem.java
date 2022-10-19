package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item{
    @Override
    public void initSolidArea() {

    }

    public SpeedItem(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
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


}
