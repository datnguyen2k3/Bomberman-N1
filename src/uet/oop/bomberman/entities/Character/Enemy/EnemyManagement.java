package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb.BombManagement;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;

public class EnemyManagement extends Management {
    public void add(int xUnit, int yUnit, char enemyDiagram, BombermanGame game) {
        if (enemyDiagram == Enemy.balloomDiagram) {
            list.add(new Balloom(xUnit, yUnit, Sprite.balloom_left1.getFxImage(), game));
        } else if (enemyDiagram == Enemy.onealDiagram) {
            list.add(new Oneal(xUnit, yUnit, Sprite.oneal_right1.getFxImage(), game));
        }
    }

    public void updateEnemyIsKilledByBomb(BombManagement bombManagement) {

    }
}
