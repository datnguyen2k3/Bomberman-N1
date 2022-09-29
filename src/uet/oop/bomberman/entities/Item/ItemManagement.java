package uet.oop.bomberman.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;

public class ItemManagement extends Management {
    public void add(int xUnit, int yUnit, char itemDiagram) {
        switch (itemDiagram) {
            case Item.flameItemDiagram:
                list.add(new FlameItem(xUnit, yUnit));
                break;
            case Item.bombItemDiagram:
                list.add(new BombItem(xUnit, yUnit));
                break;
            case Item.speedItemDiagram:
                list.add(new SpeedItem(xUnit, yUnit));
                break;
        }
    }




    // Combat
    public void removeItem(Bomber bomber) {
        for (Entity e : list) {
            Item item = (Item) e;
            if (!item.isActivate() && Entity.isEqualsCoordinate(e, bomber)) {
                list.remove(item);
                break;
            }
        }
    }

    public void setItemIfBrickIsDestroyed(Brick brick) {
        for (Entity entity : list) {
            Item item = (Item) entity;
            if (Entity.isEqualsCoordinate(item, brick)) {
                item.setActivate();
                return;
            }
        }
    }

    public void updateBomberTakeItem(Bomber bomber) {
        for (Entity e : list) {
            Item item = (Item) e;
            if (!bomber.isImpact(item.get_xUnit() * Sprite.SCALED_SIZE,
                                item.get_yUnit() * Sprite.SCALED_SIZE)) {
                continue;
            }

            if (!item.isActivate()) {
                continue;
            }

            item.setTaken();
            bomber.takeItem(item);
        }
    }

    // render
    @Override
    public void render(GraphicsContext gc) {
        for (Entity entity : list) {
            Item item = (Item) entity;
            if (item.isActivate()) {
                item.render(gc);
            }
        }
    }

}
