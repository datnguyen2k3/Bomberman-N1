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


    // xUnit, yUnit is coordinate of bomb.
    public boolean checkPosBombAndBomber(int xUnit, int yUnit) {
        int bomberX = game.getBomberman().getX();
        int bomberY = game.getBomberman().getY();
        int bomberXUnit = game.getBomberman().get_xUnit();
        int bomberYUnit = game.getBomberman().get_yUnit();

        if (game.getBomberman().get_state() == State.GO_EAST) {
            // 2 truong hop
            return (bomberXUnit == xUnit && bomberYUnit == yUnit)
                    || (bomberX + Sprite.SCALED_SIZE - xUnit * Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 3
                    && bomberYUnit == yUnit);
        } else if (game.getBomberman().get_state() == State.GO_WEST) {
            return (bomberXUnit == xUnit && bomberYUnit == yUnit)
                    || ((xUnit + 1) * Sprite.SCALED_SIZE - bomberX > Sprite.SCALED_SIZE / 3
                    && bomberYUnit == yUnit);
        } else if (game.getBomberman().get_state() == State.GO_NORTH) {
            return (bomberXUnit == xUnit && bomberYUnit == yUnit)
                    || ((yUnit + 1) * Sprite.SCALED_SIZE - bomberY > Sprite.SCALED_SIZE / 3
                    && bomberXUnit == xUnit);
        } else if (game.getBomberman().get_state() == State.GO_SOUTH) {
            return (bomberXUnit == xUnit && bomberYUnit == yUnit)
                    || (bomberY + Sprite.SCALED_SIZE - yUnit * Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 3
                    && bomberXUnit == xUnit);
        }
        return false;
    }


    public boolean isBomb(int xUnit, int yUnit, Entity entity) {
        for (Entity b : list) {
            if (b instanceof Bomb) {
                if (b.get_xUnit() == xUnit && b.get_yUnit() == yUnit) {

                    /*
                     * if recent bomb and bomberman is at same position (unit coordinate)
                     * we consider bomberman does not collide with bomb.
                     */
                    if (entity instanceof Enemy) {
                        return true;
                    } else if (entity instanceof Bomber) {
                        if (checkPosBombAndBomber(xUnit, yUnit)) {
                            return false;
                        }
                        return true;
                    }

                    return true;
                }
            }

        }
        return false;
    }

    public void add(Bomb b) {
        if (list.size() == maxBomb)
            return;
        list.add(b);
        //BombermanGame.diagramMap[b.get_yUnit()][b.get_xUnit()] = '*';
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
