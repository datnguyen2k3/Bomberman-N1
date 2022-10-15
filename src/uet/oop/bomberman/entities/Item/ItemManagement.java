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
            case Item.portalItemDiagram:
                //System.out.println(1);
                list.add(new Portal(xUnit, yUnit));
                break;
            case Item.hpItemDiagram:
                list.add(new HpItem(xUnit, yUnit));
        }
    }




    // Combat
    public void removeItem(Bomber bomber) {
        for (Entity e : list) {
            Item item = (Item) e;
            if (!item.isTaken()) {
                list.remove(item);
                break;
            }
        }
    }

    public void setItemIfBrickIsDestroyed(Brick brick) {
        for (Entity entity : list) {
//            Item item = (Item) entity;
            if (Entity.isEqualsCoordinate(((Item)entity), brick)) {
                ((Item)entity).setActivate();
                return;
            }
        }
    }

    public void updateBomberTakeItem(Bomber bomber) {
        for (Entity e : list) {
            // Item item = (Item) e;
            if (!bomber.isImpact(((Item) e).getX(), ((Item) e).getY(),
                    ((Item) e).getX() + Sprite.SCALED_SIZE,
                    ((Item) e).getY() + Sprite.SCALED_SIZE)) {
                continue;
            }

            if (!((Item) e).isActivate()) {
                continue;
            }

            if (((Item) e) instanceof Portal) {
                if (bomber.isBombermanKillAllEnemies()) {
                    bomber.setBomberWin();
                }
                continue;
            }

            ((Item) e).setTaken();
            bomber.takeItem(((Item) e));
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
