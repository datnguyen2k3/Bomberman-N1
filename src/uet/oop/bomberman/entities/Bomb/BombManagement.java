package uet.oop.bomberman.entities.Bomb;

import javafx.util.Pair;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.utils.State;

public class BombManagement extends Management {
    private BombermanGame game;
    private int currentTimeRefresh = 0;

    //    private int explodedLength = 1;
    private int explodedLength = 1;
    private int maxBomb = 1;

    public int getMaxBomb() {
        return maxBomb;
    }

    public void setMaxBomb(int maxBomb) {
        this.maxBomb = maxBomb;
    }

    public int getFlame() {
        return explodedLength;
    }

    public void setFlame(int flame) {
        this.explodedLength = flame;
    }
    public BombermanGame getGame() {
        return this.game;
    }

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

    public void add(Bomb b) {
        if (list.size() == maxBomb)
            return;
        list.add(b);
        //BombermanGame.diagramMap[b.get_yUnit()][b.get_xUnit()] = '*';
    }

    public boolean isCanMoveThroughBomb(int xUnit, int yUnit, Character other) {
        if (other.getPassBrick()) {
            return true;
        }

        for (Entity e : list) {
            Bomb b = (Bomb) e;
            if (b.get_xUnit() != xUnit || b.get_yUnit() != yUnit)
                continue;
            if (!b.isCharacterInBomb(other)) {
                return false;
            }
        }

        return true;
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
        if (firstBomb.isEnd()) {
            //BombermanGame.diagramMap[firstBomb.get_yUnit()][firstBomb.get_xUnit()] = ' ';
            list.remove(firstBomb);
        }
    }

    public boolean isDestroyBrick(Brick brick) {
        for (Entity e : list) {
            // Bomb bomb = (Bomb) e;
            for (Pair<Integer, Integer> bombCoordinate : ((Bomb) e).destroyedBricks) {
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
