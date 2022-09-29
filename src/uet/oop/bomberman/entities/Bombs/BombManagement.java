package uet.oop.bomberman.entities.Bombs;

import javafx.util.Pair;
import uet.oop.bomberman.entities.StillObjects.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;

public class BombManagement extends Management {
    private int currentTimeRefresh = 0;
    private int explodedLength = 1;

    public void powerUpFlameBomb() {
        this.explodedLength++;
    }

    public int getExplodedLength() {
        return explodedLength;
    }

    public void add(int xUnit, int yUnit) {
        if (currentTimeRefresh > 0)
            return;

        list.add(new Bomb(xUnit, yUnit, this));
        currentTimeRefresh = Bomb.timeRefresh;
    }

    @Override
    public void update() {
        super.update();
        updateCurrentTimeRefresh();
        updateRemoveBomb();
    }

    private void updateCurrentTimeRefresh() {
        if (currentTimeRefresh > 0)
            currentTimeRefresh--;
    }

    private void updateRemoveBomb() {
        if (list.size() == 0)
            return;
        Bomb firstBomb = (Bomb) list.get(list.size() - 1);
        if (firstBomb.isEnd())
            list.remove(firstBomb);
    }

    public boolean isDestroyBrick(Brick brick) {
        for (Entity e : list) {
            Bomb bomb = (Bomb) e;
            for (Pair<Integer, Integer> bombCoordinate : bomb.destroyedBricks) {
                int bomb_xUnit = bombCoordinate.getKey();
                int bomb_yUnit = bombCoordinate.getValue();
                if (bomb_xUnit == brick.get_xUnit() && bomb_yUnit == brick.get_yUnit())
                    return true;
            }
        }

        return false;
    }
}
