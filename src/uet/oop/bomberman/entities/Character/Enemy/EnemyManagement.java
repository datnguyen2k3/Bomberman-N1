package uet.oop.bomberman.entities.Character.Enemy;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.MiniInfo.PlusScore;
import uet.oop.bomberman.entities.Bomb.BombManagement;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Character.Enemy.BlueEnemy.Dora;
import uet.oop.bomberman.entities.Character.Enemy.BlueEnemy.Oneal;
import uet.oop.bomberman.entities.Character.Enemy.RedEnemy.Doll;
import uet.oop.bomberman.entities.Character.Enemy.RedEnemy.Ghost;
import uet.oop.bomberman.entities.Character.Enemy.RedEnemy.RedCoin;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Balloom;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Cat;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Coin;
import uet.oop.bomberman.entities.Character.Enemy.YellowEnemy.Minvo;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyManagement extends Management<Enemy> {
    BombermanGame game;
    private List<Enemy> addLater = new ArrayList<>();
    public int getNumEnemies() {
        return list.size();
    }
    public List<Enemy> getList() {
        return list;
    }

    public void add(int xUnit, int yUnit, char enemyDiagram, BombermanGame game) {
        Enemy enemy = getEnemy(xUnit, yUnit, enemyDiagram, game);
        this.game = game;
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
            case Enemy.catDiagram:
                return new Cat(xUnit, yUnit, Sprite.cat_left1.getFxImage(), game);
            case Enemy.coinDiagram:
                return new Coin(xUnit, yUnit, Sprite.orange_coin_right.getFxImage(), game);
            case Enemy.ghostDiagram:
                return new Ghost(xUnit, yUnit, Sprite.ghost_right1.getFxImage(), game);
            case Enemy.redCoinDiagram:
                return new RedCoin(xUnit, yUnit, Sprite.red_segment.getFxImage(), game);
        }

        return null;
    }

    public boolean isEnemyKillCharacter(Character character) {
        for (Enemy enemy : list) {
            if (!enemy.isDead() && character.isImpact(enemy)) {
                return true;
            }
        }
        return false;
    }

    public void updateEnemyIsKilledByBomb(BombManagement bombManagement) {
        for (Enemy enemy : list) {
            if (!enemy.isDead() && bombManagement.isDestroyCharacter(enemy)) {
                enemy.setDead();
                game.getMiniInfoManagement().add(
                        new PlusScore(game, enemy)
                );
                game.getBomberScore().addScore(enemy);
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
        updateAddEnemyLater();
    }
}
