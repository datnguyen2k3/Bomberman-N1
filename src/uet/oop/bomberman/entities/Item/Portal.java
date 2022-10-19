package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Item{

    public Portal(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
    }

    @Override
    public void initSolidArea() {

    }

    @Override
    protected void initSprite() {
        this.img = Sprite.portal.getFxImage();
    }

    @Override
    public void update() {

    }

    @Override
    protected void initItemDiagram() {
        this.itemDiagram = Item.portalItemDiagram;
    }

}
