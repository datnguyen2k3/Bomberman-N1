package uet.oop.bomberman.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;

public class ItemManagement extends Management<Item> {
    public void add(int xUnit, int yUnit, char itemDiagram, BombermanGame game) {
        switch (itemDiagram) {
            case Item.flameItemDiagram:
                list.add(new FlameItem(xUnit, yUnit, game));
                break;
            case Item.bombItemDiagram:
                list.add(new BombItem(xUnit, yUnit, game));
                break;
            case Item.speedItemDiagram:
                list.add(new SpeedItem(xUnit, yUnit, game));
                break;
            case Item.portalItemDiagram:
                //System.out.println(1);
                list.add(new Portal(xUnit, yUnit, game));
                break;
            case Item.hpItemDiagram:
                list.add(new HpItem(xUnit, yUnit, game));
                break;
            case Item.passBrickDiagram:
                list.add(new PassBrickItem(xUnit, yUnit, game));
                break;
            case Item.flamePassDiagram:
                list.add(new FlamePassItem(xUnit, yUnit, game));
                break;
            case Item.bombPassDiagram:
                list.add(new BombPassItem(xUnit, yUnit, game));
        }
    }




    // Combat
    public void removeItem(Bomber bomber) {
        for (Item item : list) {
            if (!item.isTaken()) {
                list.remove(item);
                break;
            }
        }
    }

    public void setItemIfBrickIsDestroyed(Brick brick) {
        for (Item item : list) {
            if (Entity.isEqualsCoordinate(item, brick)) {
                item.setActivate();
                return;
            }
        }
    }

    public void updateBomberTakeItem(Bomber bomber) {
        for (Item item : list) {
            // Item item = (Item) e;
            if (!bomber.isImpact(item.getX(), item.getY(),
                    item.getX() + Sprite.SCALED_SIZE,
                    item.getY() + Sprite.SCALED_SIZE)) {
                continue;
            }

            if (!item.isActivate()) {
                continue;
            }

            if (item instanceof Portal) {
                if (bomber.isBombermanKillAllEnemies() && bomber.isInCell(item.get_xUnit(), item.get_yUnit())) {

                    bomber.setBomberWin();
                }
                continue;
            }

            bomber.takeItem( item);
            item.setTaken();
        }
    }

    // render
    @Override
    public void render(GraphicsContext gc) {
        for (Item item : list) {
            if (item.isActivate()) {
                item.render(gc);
            }
        }
    }

}
