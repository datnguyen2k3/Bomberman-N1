package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.entities.Bomb.BombManagement;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

public class EnemyManagement extends Management {
    public void add(int xUnit, int yUnit, char enemyDiagram, BombermanGame game) {
        if (enemyDiagram == Enemy.balloomDiagram) {
            list.add(new Balloom(xUnit, yUnit, Sprite.balloom_left1.getFxImage(), game));
        } else if (enemyDiagram == Enemy.onealDiagram) {
            list.add(new Oneal(xUnit, yUnit, Sprite.oneal_right1.getFxImage(), game));
        }
    }

    public boolean isEnemyKillCharacter(Character character) {
        for (Entity entity : list) {
            Enemy enemy = (Enemy) entity;
            if (!enemy.isDead() && enemy.isImpact(character)) {
                return true;
            }
        }

        return false;
    }

    public void updateEnemyIsKilledByBomb(BombManagement bombManagement) {
        for (Entity e : list) {
            Enemy enemy = (Enemy) e;
            if (!enemy.isDead() && bombManagement.isDestroyEnemy(enemy)) {
                enemy.setDead();
            }
        }

        for (Entity e : list) {
            Enemy enemy = (Enemy) e;
            if (enemy.isEnd()) {
                enemy.setDead();
            }
        }
    }
}
