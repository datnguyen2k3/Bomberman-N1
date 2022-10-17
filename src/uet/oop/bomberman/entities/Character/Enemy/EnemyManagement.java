package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.entities.Bomb.BombManagement;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyManagement extends Management {
    private List<Enemy> addLater = new ArrayList<>();
    public int getNumEnemies() {
        return list.size();
    }

    public void add(int xUnit, int yUnit, char enemyDiagram, BombermanGame game) {
        Enemy enemy = getEnemy(xUnit, yUnit, enemyDiagram, game);
        if (enemy != null) {
            list.add(enemy);
        }
    }

    private Enemy getEnemy(int xUnit, int yUnit, char enemyDiagram, BombermanGame game) {
        switch (enemyDiagram) {
            case Enemy.balloomDiagram:
                return new Balloom(xUnit, yUnit, Sprite.balloom_left1.getFxImage(), game);
            case Enemy.onealDiagram:
                return new Oneal(xUnit, yUnit, Sprite.oneal_right1.getFxImage(), game);
            case Enemy.dollDiagram:
                return new Doll(xUnit, yUnit, Sprite.doll_right1.getFxImage(), game);
            case Enemy.minvoDiagram:
                return new Minvo(xUnit, yUnit, Sprite.minvo_left1.getFxImage(), game);
            case Enemy.doraDiagram:
                return new Dora(xUnit, yUnit, Sprite.kondoria_left1.getFxImage(), game);
        }

        return null;
    }

    public boolean isEnemyKillCharacter(Character character) {
        for (Entity entity : list) {
            Enemy enemy = (Enemy) entity;
            if (!enemy.isDead() && character.isImpact(enemy)) {
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
    }

    public void updateRemoveEnemy() {
        for (int i = 0; i < list.size(); i++) {
            Enemy enemy = (Enemy) list.get(i);
            if (enemy.isEnd()) {
                list.remove(i);
                i--;
            }
        }
    }

    public void addEnemyLater(int xUnit, int yUnit, char enemyDiagram, BombermanGame game) {
        Enemy enemy = getEnemy(xUnit, yUnit, enemyDiagram, game);
        if (enemy != null) {
            addLater.add(enemy);
        }
    }

    private void updateAddEnemyLater() {
        list.addAll(addLater);
        addLater.clear();

    }

    @Override
    public void update() {
        super.update();
        updateRemoveEnemy();
        updateAddEnemyLater();
    }
}
