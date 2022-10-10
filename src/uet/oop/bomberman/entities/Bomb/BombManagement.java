package uet.oop.bomberman.entities.Bomb;

import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Character.Character;

public class BombManagement extends Management {
    private BombermanGame game;
    private int currentTimeRefresh = 0;
    private int explodedLength = 3;
    private int maxBomb = 1;

    public void setGame(BombermanGame game) {
        this.game = game;
    }

    public BombManagement(BombermanGame game) {
        this.game = game;
    }

    public void powerUpFlameBomb() {
        this.explodedLength++;
    }
    public void powerUpMaxBomb() {
        maxBomb++;
    }

    public int getExplodedLength() {
        return explodedLength;
    }

    public void add( Bomb b) {
        if (list.size() == maxBomb)
            return;
        list.add(b);
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

    public boolean isDestroyEnemy(Character enemy) {
        for (Entity e : list) {
            Bomb bomb = (Bomb) e;
            for (Pair<Integer, Integer> bombCoordinate : bomb.explodedCells) {
                int bomb_xUnit = bombCoordinate.getKey();
                int bomb_yUnit = bombCoordinate.getValue();
                if (enemy.isImpact(bomb_xUnit * Sprite.SCALED_SIZE,
                                    bomb_yUnit * Sprite.SCALED_SIZE,
                                    bomb_xUnit * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE,
                                    bomb_yUnit * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE)) {
                    // System.out.println(enemy.getX() + " " + enemy.getY());
                    return true;
                }
            }
        }

        return false;
    }
}
